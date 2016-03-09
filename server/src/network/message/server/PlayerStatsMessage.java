package network.message.server;

import cards.EquipSlot;
import cards.Race;
import cards.ClassCard;
import game.GameManager;
import game.Player;
import game.Player.whichClass;
import game.Player.whichRace;
import network.message.Message;
import network.message.client.PlayerStatusRequest;

public class PlayerStatsMessage extends PlayerStatusRequest {

	private static String Player;
	private String playerName;
	private int level;
	private int power;
	private Race playerRace;
	private ClassCard playerClass;
	private int playerNumCards;
	
	public PlayerStatsMessage(String request) {
		super(Player,PlayerStatusRequest.REQUEST_PLAYER_FULL_STATS);
		boolean founded=false;
		int size = GameManager.getPlayers().size();
		for (int i = 0; i < size&&!founded; i++) {
			if(Player == GameManager.getPlayers().get(i).getUsername()){
				founded=true;
				playerName=GameManager.getPlayers().get(i).getUsername();
				level = GameManager.getPlayers().get(i).getLevel();
				power = GameManager.getPlayers().get(i).getCombatLevel();
				playerRace = GameManager.getPlayers().get(i).getRace(whichRace.firstRace);
				playerClass = GameManager.getPlayers().get(i).getClass(whichClass.firstClass);
				playerNumCards = GameManager.getPlayers().get(i).getHand().size();
			}
		}
		
		
	}
	
	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(playerName);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(level);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(power);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(playerRace.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(playerClass.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(playerNumCards);
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}

}
