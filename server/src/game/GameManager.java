package game;

import java.util.ArrayList;
import java.util.Collections;

import cards.Card;
import network.message.DrawCardMessage;
import utils.Debug;

public class GameManager{
	private static boolean playerWon;
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
		player.sendMessage(new DrawCardMessage(card.getTitle(),false));
		player.draw(card);
	}
	
	public static Player getCurrentPlayer() {
		return players.get(0);
	}
	
	public static final ArrayList<Player> getPlayers(){
		return players;
	}
}
