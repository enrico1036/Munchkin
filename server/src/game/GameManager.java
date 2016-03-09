package game;

import java.util.ArrayList;
import java.util.Collections;

import cards.Card;
import network.MessageQueue;
import network.message.Message;
import network.message.server.PlayCardMessage;
import utils.Debug;

public class GameManager{
	private static boolean playerWon;
	private static MessageQueue queue = new MessageQueue();
	private static ArrayList<game.Player> players = new ArrayList<>();
	
	
	private static void begin() {
		Debug.print("GameManager: Begin");
		playerWon = false;
		Decks.shuffleDecks();
	}
	
	private static void turn() {
		Turn turn = new Turn();
		while(turn.performStep());
	}
	
	private static void end() {
		Debug.print("GameManager: End");
		Debug.print(players.get(0).getUsername() + "Won!!!");
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
	
	public static void giveCardToPlayer(Card card, Player player) {
		player.sendMessage(new PlayCardMessage(card.getTitle(),false));
		player.draw(card);
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
}
