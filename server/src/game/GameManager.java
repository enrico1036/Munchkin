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
	private static MessageQueue queue = new MessageQueue();
	private static ArrayList<game.Player> players = new ArrayList<>();

	private static void begin() {
		broadcastMessage(new StateUpdateMessage("begin"));
		Debug.print("GameManager: Begin");
		
		playerWon = false;
		Decks.shuffleDecks();
		for (Player player : players) {
			for (int i = 0; i < 2; i++) {
				player.draw(Decks.getDoorCard());
				player.draw(Decks.getTreasureCard());
			}
		}
	}

	private static void turn() {
		Turn turn = new Turn();
		while (turn.performStep())
			;
	}

	private static void end() {
		Debug.print("GameManager: End");
		Debug.print(players.get(0).getUsername() + "Won!!!");
		broadcastMessage(new PopUpMessage(players.get(0).getUsername() + "Won!!!", 30000));
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
		return queue;
	}

	public static Player getPlayerByName(String playerName) {
		Player selectedPlayer = null;

		for (Player player : players) {
			{
				if (player.getUsername() == playerName)
					selectedPlayer = player;

			}
		}
		return selectedPlayer;
	}

	/**
	 * @param players the players to set
	 */
	public static void setPlayers(ArrayList<game.Player> players) {
		GameManager.players = players;
	}
}
