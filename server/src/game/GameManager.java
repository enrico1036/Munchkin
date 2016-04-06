package game;

import java.awt.PopupMenu;
import java.util.ArrayList;
import java.util.Collections;

import cards.Card;
import debug.Debug;
import javafx.stage.Popup;
import network.MessageQueue;
import network.message.Message;
import network.message.server.PlayCardMessage;
import network.message.server.PlayCardMessage.Action;
import network.message.server.PlayerFullStatsMessage;
import network.message.server.PopUpMessage;
import network.message.server.StateUpdateMessage;

public class GameManager {
	private static boolean gameStarted = false;
	private static boolean playerWon;
	private static MessageQueue queue;
	private static ArrayList<Player> players;
	
	public static void init(final ArrayList<Player> players, final MessageQueue queue){
		GameManager.players = players;
		GameManager.queue = queue;
		playerWon = false;
		gameStarted = false;
	}

	private static void begin() {
		gameStarted = true;
		broadcastMessage(new StateUpdateMessage("","begin"));
		for (Player player : GameManager.getPlayers()) {
			broadcastMessage(new PlayerFullStatsMessage(player));
		}
		Debug.log("GameManager: Begin");

		playerWon = false;
		Decks.shuffleDecks();
	}

	private static void turn() {
		Turn turn = new Turn();
		while (turn.performStep())
			;
	}

	private static void end() {
		Debug.log("GameManager: End");
		Debug.log(getCurrentPlayer().getUsername() + " Won!!!");
		broadcastMessage(new StateUpdateMessage(getCurrentPlayer().getUsername(), "end"));
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void nextPlayer() {
		players.add(players.get(0));
		players.remove(0);
		GameManager.broadcastMessage(new StateUpdateMessage(GameManager.getCurrentPlayer().getUsername(), "Equip"));
	}

	public static void win() {
		playerWon = true;
	}

	public static void startGame() {
		begin();
		while (!playerWon) {
			nextPlayer();
			turn();
			if(getCurrentPlayer().getLevel() >= 10)
				win();
		}
		end();
	}

	/**
	 * @return the gameStarted
	 */
	public static boolean isGameStarted() {
		return gameStarted;
	}

	public static Player getCurrentPlayer() {
		return players.get(0);
	}

	public static final ArrayList<Player> getPlayers() {
		return players;
	}

	public static void broadcastMessage(Message message) {
		for (Player player : GameManager.getPlayers()) {
			player.sendMessage(message);
		}
	}

	public static final MessageQueue getInQueue() {
		return GameManager.queue;
	}
	
	public static Player getPlayerByName(String playerName) {
		Player selectedPlayer = null;

		for (Player player : players) {
			if (player.getUsername().equals(playerName))
				selectedPlayer = player;
		}
		return selectedPlayer;
	}
}
