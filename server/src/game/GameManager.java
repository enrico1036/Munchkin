package game;

import java.util.ArrayList;

import utils.Debug;

public class GameManager{
	private static boolean playerWon;
	private static ArrayList<game.Player> players;
	
	
	private static void begin() {
		Debug.print("GameManager: Begin");
		playerWon = false;
		//fill player array
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
	
	//TODO: look for a method to make static performStep()

	public static void StartGame() {
			begin();
			while(!playerWon) {
				nextPlayer();
				turn();
			}
			end();		
	}
}
