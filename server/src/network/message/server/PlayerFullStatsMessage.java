package network.message.server;

import cards.EquipSlot;
import cards.Race;
import cards.ClassCard;
import game.GameManager;
import game.Player;
import game.Player.whichClass;
import game.Player.whichRace;
import network.message.Message;

public class PlayerFullStatsMessage extends PlayerStatusRequest {

	private static String Player;
	private String playerName;
	private int level;
	private int power;
	private Race playerRace1;
	private ClassCard playerClass1;
	private Race playerRace2;
	private ClassCard playerClass2;
	private int playerNumCards;
	
	public PlayerFullStatsMessage() {
		super(Player,PlayerStatusRequest.REQUEST_PLAYER_FULL_STATS);
		
		Player player= GameManager.getPlayerByName(Player);
				playerName=player.getUsername();
				level = player.getLevel();
				power = player.getCombatLevel();
				playerRace1 = player.getRace(whichRace.firstRace);
				playerClass1 = player.getClass(whichClass.firstClass);
				playerRace2 = player.getRace(whichRace.secondRace);
				playerClass2 = player.getClass(whichClass.secondClass);
				playerNumCards = player.getHand().size();
			
		
		
		
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
	
	
	
}
