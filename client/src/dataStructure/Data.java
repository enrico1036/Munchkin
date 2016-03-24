package dataStructure;

import java.util.HashMap;

public class Data {

	private static HashMap<String, PlayerData> players = new HashMap<String, PlayerData>();
	private static CardDataSet hand;
	private static CardDataSet table;
	
	
	public static PlayerData getPlayer(String username) {
		return players.get(username);
	}
	
	public static void addPlayer(String username) {
		PlayerData player = new PlayerData();
		player.setUsername(username);
		players.put(username, player);
	}
	
	public static void addHandCard(String card) {
		hand.getCards().add(card);
	}
	
	public static void removeHandCard(String card) {
		hand.getCards().remove(card);
	}

	public static void addTableCard(String card) {
		table.getCards().add(card);
	}
	
	public static void removeTableCard(String card) {
		table.getCards().remove(card);
	}
}
