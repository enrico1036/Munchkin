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

public class GameManager{
	private static boolean playerWon;
	private static MessageQueue queue = new MessageQueue();
	private static ArrayList<game.Player> players = new ArrayList<>();
	
	
	private static void begin() {
		Debug.print("GameManager: Begin");
		playerWon = false;
		Decks.shuffleDecks();
		broadcastMessage(new StateUpdateMessage("begin"));
	}
	
	private static void turn() {
		Turn turn = new Turn();
		while(turn.performStep());
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
		while(!playerWon) {
			nextPlayer();
			turn();
		}
		end();		
	}
	
	
	public static Player getCurrentPlayer() {
		return players.get(0);
	}
	
	public static final ArrayList<Player> getPlayers(){
		return players;
	}
	
	public static void broadcastMessage(Message message) {
		for (Player player : GameManager.getPlayers()) {
			player.sendMessage(message);
		}
	}

	public static ArrayList<Player> readyPlayers(){
		ArrayList<Player> readyP = new ArrayList<>();
		
		for (Player player : players) {
			{
				if(player.isLobby_ready())
				readyP.add(player);
					
			}
		}
		return readyP;
	}
	
	public static final MessageQueue getInQueue(){
		return queue;
	}
	
	public static Player getPlayerByName(String playerName){
		Player selectedPlayer = null;
		
		for (Player player : players) {
			{
				if(player.getUsername() == playerName)
				selectedPlayer = player;
					
			}
		}
		return selectedPlayer;
	}
}
