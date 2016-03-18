package game;

import java.awt.PopupMenu;
import java.util.ArrayList;
import java.util.Collections;

import cards.Card;
import javafx.stage.Popup;
import network.MessageQueue;
import network.message.Message;
import network.message.server.PlayCardMessage;
import network.message.server.PlayCardMessage.Action;
import network.message.server.PopUpMessage;
import network.message.server.StateUpdateMessage;
import utils.Debug;

public class GameManager {
	private static boolean playerWon;
	private static MessageQueue queue;
	private static ArrayList<game.Player> players;

	private static void begin() {
		broadcastMessage(new StateUpdateMessage("begin"));
		Debug.print("GameManager: Begin");

		playerWon = false;
		Decks.shuffleDecks();
	}

	private static void turn() {
		Turn turn = new Turn();
		while (turn.performStep())
			;
	}

	private static void end() {
		Debug.print("GameManager: End");
		Debug.print(getCurrentPlayer().getUsername() + "Won!!!");
		broadcastMessage(new PopUpMessage(getCurrentPlayer().getUsername() + "Won!!!", 30000));
	}

	private static void nextPlayer() {
		players.add(players.get(0));
		players.remove(0);
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
	public static void setInQueue(final MessageQueue queue) {
		GameManager.queue = queue;
	}

	public static Player getPlayerByName(String playerName) {
		Player selectedPlayer = null;

		for (Player player : players) {
			if (player.getUsername().equals(playerName))
				selectedPlayer = player;
		}
		return selectedPlayer;
	}

	/**
	 * @param players
	 *            the players to set
	 */
	public static void setPlayers(ArrayList<game.Player> players) {
		GameManager.players = players;
	}
}
