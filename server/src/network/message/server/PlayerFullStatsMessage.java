package network.message.server;

import cards.EquipSlot;
import cards.Race;
import cards.ClassCard;
import game.GameManager;
import game.Player;
import network.message.Message;

public class PlayerFullStatsMessage extends PlayerStatusRequest {

	private static String Player;
	private String playerName;
	private int level;
	private int power;
	private Race playerRace;
	private ClassCard playerClass;
	private int playerNumCards;
	
	public PlayerFullStatsMessage() {
		super(Player,PlayerStatusRequest.REQUEST_PLAYER_FULL_STATS);
		
		Player player= GameManager.getPlayerByName(Player);
				playerName=player.getUsername();
				level = player.getLevel();
				power = player.getCombatLevel();
				playerRace = player.getRace();
				playerClass = player.getCardClass();
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
		return playerRace;
	}

	public ClassCard getPlayerClass1() {
		return playerClass;
	}


	public int getPlayerNumCards() {
		return playerNumCards;
	}
	
	
	
}
