package dataStructure;

import java.util.HashMap;

public class Data {

	private HashMap<String, PlayerData> players;
	private CardDataSet hand;
	private CardDataSet table;
	
	public Data() {
		players = new HashMap<String, PlayerData>();
	}
	
	public PlayerData getPlayer(String username) {
		return players.get(username);
	}
	
	public void addPlayer(String username) {
		PlayerData player = new PlayerData();
		player.setUsername(username);
		players.put(username, player);
	}
	
	public void addHandCard(String card) {
		hand.getCards().add(card);
	}
	
	public void removeHandCard(String card) {
		hand.getCards().remove(card);
	}

	public void addTableCard(String card) {
		table.getCards().add(card);
	}
	
	public void removeTableCard(String card) {
		table.getCards().remove(card);
	}
}
