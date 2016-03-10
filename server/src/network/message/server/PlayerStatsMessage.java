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
	private Race playerRace1;
	private ClassCard playerClass1;
	private Race playerRace2;
	private ClassCard playerClass2;
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
				playerRace1 = GameManager.getPlayers().get(i).getRace(whichRace.firstRace);
				playerClass1 = GameManager.getPlayers().get(i).getClass(whichClass.firstClass);
				playerRace2 = GameManager.getPlayers().get(i).getRace(whichRace.secondRace);
				playerClass2 = GameManager.getPlayers().get(i).getClass(whichClass.secondClass);
				playerNumCards = GameManager.getPlayers().get(i).getHand().size();
			}
		}
		
		
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getLevel() {
		return level;
	}

	public int getPower() {
		return power;
	}

	public Race getPlayerRace1() {
		return playerRace1;
	}

	public ClassCard getPlayerClass1() {
		return playerClass1;
	}

	public Race getPlayerRace2() {
		return playerRace2;
	}

	public ClassCard getPlayerClass2() {
		return playerClass2;
	}

	public int getPlayerNumCards() {
		return playerNumCards;
	}
	
	
	/*
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
		builder.append(playerRace1.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(playerRace2.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(playerClass1.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(playerClass2.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(playerNumCards);
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}
*/
}
