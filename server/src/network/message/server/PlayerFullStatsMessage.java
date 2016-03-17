package network.message.server;

import cards.EquipSlot;
import cards.Race;
import cards.ClassCard;
import game.GameManager;
import game.Player;
import network.message.Message;

public class PlayerFullStatsMessage extends Message{

	private String username;
	private int level;
	private int combatLevel;
	private String raceCard;
	private String classCard;
	private int handSize;
	
	public PlayerFullStatsMessage(Player player) {
		super(Message.PLAYER_FULL_STATS);	
		username = player.getUsername();
		combatLevel = player.getCombatLevel();
		level = player.getLevel();
		handSize = player.getHand().size();
		raceCard = player.getRace().getTitle();
		classCard = player.getCardClass().getTitle();
	}

	public String getPlayerName() {
		return username;
	}

	public int getLevel() {
		return level;
	}

	public int getCombatLevel() {
		return combatLevel;
	}

	public String getRaceCard() {
		return raceCard;
	}

	public String getClassCard() {
		return classCard;
	}
	
	public int getHandSize() {
		return handSize;
	}
	
	
	
}
