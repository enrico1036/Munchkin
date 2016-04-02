package server;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.javafx.tk.Toolkit.Task;

import Debug.Debug;
import game.Decks;
import game.GameManager;
import game.Player;
import network.ConnectionListener;
import network.MessageQueue;
import network.message.ActionResultMessage;
import network.message.Message;
import network.message.client.ChatMessage;
import network.message.server.PlayerEquipmentMessage;
import network.message.server.PlayerFullStatsMessage;
import network.message.server.ReadyLobbyMessage;
import network.message.server.ReadyStatusMessage;
import network.message.server.StateUpdateMessage;
import utils.CountdownTask;
import utils.PlayerEventListener;

public class MunchkinServer implements PlayerEventListener {

	// Array containing all the players connected to the server
	private final ArrayList<Player> players;
	// Tcp listener
	private ConnectionListener connListener;
	// Common message queue shared between players
	private MessageQueue queue;
	// Timer used to pass from lobby to main game loop
	private CountdownTask countTask;
	private Timer timer;
	// Minimum number of players to start the game
	private int minPlayers;
	// Simple lock object for wait() calls
	private final Object lock = new Object();

	/**
	 * MunchkinServer's only constructor
	 * 
	 * @param port
	 *            Port number to start TCP listener on
	 * @param maxPlayers
	 *            Max number of players
	 */
	public MunchkinServer(int port, int maxPlayers, int minPlayers) throws Exception{
		// Create player array
		players = new ArrayList<Player>();
		// Create queue
		queue = new MessageQueue();

		GameManager.setPlayers(players);
		GameManager.setInQueue(queue);

		// Create ConnectionListener, specifying port, max connections and
		// PlayerListener
		try {
			connListener = new ConnectionListener(port, maxPlayers);
			connListener.setAcceptTimeout(0);
			connListener.setConnectionTimeout(0);
			// Set this queue to be attached to each connection
			connListener.setCommonMessageQueue(queue);
		} catch (IOException e) {
			// Error during socket binding, exit the program
			Debug.err("could not create ServerSocket on port " + port);
			throw e;
		}

		// Load cards from xml file
		try {
			Decks.loadDecks("Resources/cards.xml");
			Debug.log("Loaded cards from xml file");
		} catch (Exception e) {
			Debug.err("couldn't load cards from xml file");
//			e.printStackTrace();
			System.exit(0);
		}

		// Initialize timer
		timer = new Timer();
		countTask = new CountdownTask(5, null);

		this.minPlayers = minPlayers;
	}
	
	public boolean isRunning(){
		return connListener.isRunning();
	}

	/**
	 * Wait for players to correctly connect to the server and wait for them to be ready.
	 */
	public void populateLobby() {
		// Add this instance as PlayerEventListener
		connListener.setPlayerEventListener(this);
		// Start listening
		connListener.start();
		Debug.log("Started listening on " + connListener.toString());

		// Flag is true if every player is ready
		boolean allReady = false;

		// Loop until there are at least minPlayers and all ready
		while (!allReady || !countTask.hasCompleted()) {

			// Check if someone is still not ready
			allReady = players.size() >= minPlayers;
			for (Player p : players)
				allReady &= p.isLobbyReady();

			// Start timer if everyone is ready
			if (allReady) {
				// Create a new task with specifying its behavior
				countTask = new CountdownTask(5, new CountdownTask.CountdownActions() {

					@Override
					public void onTick(int target, int count) {
						GameManager.broadcastMessage(new ChatMessage("Server", "Game starting in " + (target - count) + " seconds"));
					}

					@Override
					public void onComplete(int target, int count) {
						GameManager.broadcastMessage(new ChatMessage("Server", "Game started"));
						synchronized (lock) {
							lock.notify();
						}
					}

					@Override
					public void onCancel(int target, int count) {
						GameManager.broadcastMessage(new ChatMessage("Server", "Countdown stopped"));

					}
				});

				timer.scheduleAtFixedRate(countTask, 0, 1000);

			} else {
				if (countTask != null)
					countTask.cancel();
			}

			// Block thread for a second until a player event unlocks it
			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException | IllegalMonitorStateException e) {
				}
			}
		}
		timer.cancel();
	}

	public void shutdown() {
		connListener.stop();
		connListener.stopConnections();
	}

	@Override
	public void chatMessage(Message message) {
		GameManager.broadcastMessage(message);
	}

	@Override
	public void lobbyStatusChanged(Player player) {
		// Toggle player status
		player.setLobbyReady(!player.isLobbyReady());
		// Broadcast update
		GameManager.broadcastMessage(new ReadyLobbyMessage(players));
		// Unlock populateLobby()
		synchronized (lock) {
			try {
				lock.notify();
			} catch (IllegalMonitorStateException e) {
			}
		}
	}

	@Override
	public void playerConnected(Player player) {
		boolean playerAlreadyExists = false;

		for (Player p : players) {
			// In case new player has the same name of an existing one
			if (p.getUsername().equals(player.getUsername())) {
				playerAlreadyExists = true;
				if (!p.isConnected()) {
					// Swap old player's connection for the new one
					connListener.stopConnection(p.getConnection());
					p.setConnection(player.getConnection());
					p.sendMessage(new ActionResultMessage(ActionResultMessage.ACTION_ALLOWED, ""));
					// If game has started, send to the client all game info
					if (GameManager.isGameStarted()){
						player.sendMessage(new ReadyLobbyMessage(players));
						for(Player pl : players){
							player.sendMessage(new PlayerFullStatsMessage(pl));
							player.sendMessage(new PlayerEquipmentMessage(pl));
						}
						player.sendMessage(new StateUpdateMessage(GameManager.getCurrentPlayer().getUsername(), "begin"));
					}
				} else {
					// The player is still connected, refuse new connection and
					// return
					player.sendMessage(new ActionResultMessage(ActionResultMessage.ACTION_DENIED, "Username lready in use"));
					connListener.stopConnection(player.getConnection());
					return;
				}
			}
		}

		// If there is no player with that name, add new player to array
		if (!playerAlreadyExists && !GameManager.isGameStarted()) {
			players.add(player);
			player.sendMessage(new ActionResultMessage(ActionResultMessage.ACTION_ALLOWED, ""));
			GameManager.broadcastMessage(new ReadyLobbyMessage(players));
		}


		// Unlock populateLobby()
		synchronized (lock) {
			try {
				lock.notifyAll();
			} catch (IllegalMonitorStateException e) {
			}
		}
	}

	@Override
	public void playerDisconnected(Player player) {
		connListener.stopConnection(player.getConnection());
		if (!GameManager.isGameStarted()) {
			players.remove(player);
			GameManager.broadcastMessage(new ReadyLobbyMessage(players));

			// Unlock populateLobby()
			synchronized (lock) {
				try {
					lock.notifyAll();
				} catch (IllegalMonitorStateException e) {
				}
			}
		}
	}

}
