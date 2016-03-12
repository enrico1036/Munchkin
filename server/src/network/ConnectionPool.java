package network;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.sun.istack.internal.Pool;

import game.Player;
import javafx.util.Pair;
import network.message.ActionResultMessage;
import network.message.Message;
import network.message.client.UpdateReadyPlayerMessage;

public class ConnectionPool {
	private final ConcurrentHashMap<String, ClientConnection> connections;
	private final ArrayList<Player> players;
	private final ExecutorService executor;
	private final MessageQueue inQueue;

	public ConnectionPool(int capacity, MessageQueue inQueue, ArrayList<Player> players) {
		connections = new ConcurrentHashMap<>(capacity);
		executor = Executors.newFixedThreadPool(capacity);
		this.players = players;
		this.inQueue = inQueue;
	}

	public void add(final ClientConnection connection)  {
		// Prevent the existence of two or more different connections
		// with the same client id (each player corresponds to only one
		// connection)
		for (Player player : players) {
			// Check if a Player with the same name exists
			if (player.getUsername().equals(connection.getClientId())) {
				if (player.isConnected()) {
					// Deny connection and close it if Player is already
					// connected
					ActionResultMessage result = new ActionResultMessage(ActionResultMessage.ACTION_DENIED, "Player name already in use");
					connection.write(result);
					System.out.println("Connection refused: " + connection.toString());
					connection.close();
					return;
				} else {
					// Confirm successful connection and update Player's connection
					ActionResultMessage result = new ActionResultMessage(ActionResultMessage.ACTION_ALLOWED, null);
					connection.write(result);
					player.setConnection(connection);
					// Add to map
					connections.put(connection.getClientId(), connection);
					// Execute worker run() method
					executor.execute(connection);
					System.out.println("Connection resumed: " + connection.toString());
					return;
				}
			}
		}
		
		// Confirm successful connection 
		ActionResultMessage result = new ActionResultMessage(ActionResultMessage.ACTION_ALLOWED, null);
		connection.write(result);
		// If Player with that name doesn't exist, create it and add to array
		Player player = new Player(connection.getClientId());
		player.setConnection(connection);
		players.add(player);
		// Add to map
		connections.put(connection.getClientId(), connection);
		// Execute worker run() method
		executor.execute(connection);

		System.out.println("Connection started: " + connection.toString());
		this.broadcast(new UpdateReadyPlayerMessage());
	}

	public int size() {
		return connections.size();
	}

	public void signalEnd(ClientConnection connection) {
		connections.remove(connection.getClientId());
		System.out.println("Connection ended: " + connection.toString());
	}

	public final MessageQueue getInputQueue() {
		return inQueue;
	}

	public void broadcast(Message message) {
		for (Map.Entry<String, ClientConnection> pair : connections.entrySet()) {
			ClientConnection conn = pair.getValue();
			conn.write(message);
		}
	}

	public void closeAll() {
		// Await termination of the threads still running
		// If not possible, force shutdown
		try {
			if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
				ArrayList<Runnable> list = (ArrayList<Runnable>) executor.shutdownNow();
				for (Runnable r : list) {
					((ClientConnection) r).close();
				}
			}
		} catch (InterruptedException e) {
			executor.shutdown();
		}
	}


	
	
	
}
