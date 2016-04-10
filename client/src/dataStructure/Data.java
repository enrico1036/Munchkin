package dataStructure;

import java.util.HashMap;

public class Data {

	private static HashMap<String, PlayerData> players = new HashMap<String, PlayerData>();
	private static CardDataSet hand = new CardDataSet();
	private static CardDataSet table = new CardDataSet();
	private static CardDataSet discardDeck = new CardDataSet();
	private static TurnData turn = new TurnData();
	private static ChatData chat = new ChatData();
	
	/**
	 * @return the hand
	 */
	public static CardDataSet getHand() {
		return hand;
	}

	/**
	 * @return the table
	 */
	public static CardDataSet getTable() {
		return table;
	}
	
	/**
	 * @return the discardDeck
	 */
	public static CardDataSet getDiscardDeck() {
		return discardDeck;
	}

	public static PlayerData getPlayer(String username) {
		return players.get(username);
	}
	
	public static void addPlayer(String username) {
		PlayerData player = new PlayerData();
		player.setUsername(username);
		players.put(username, player);
	}

	/**
	 * @return the turn
	 */
	public static TurnData getTurn() {
		return turn;
	}

	/**
	 * @return the chat
	 */
	public static ChatData getChat() {
		return chat;
	}
}
