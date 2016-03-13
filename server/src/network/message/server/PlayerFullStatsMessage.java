package network.message.server;

import cards.EquipSlot;
import cards.Race;
import cards.ClassCard;
import game.GameManager;
import game.Player;
import network.message.Message;

public class PlayerFullStatsMessage extends Message{

	private Player player;
	
	public PlayerFullStatsMessage(Player player) {
		super(Message.PLAYER_FULL_STATS);	
		this.player = player;
	}

	public String getPlayerName() {
		return player.getUsername();
	}

	public int getLevel() {
		return player.getLevel();
	}

	public int getPower() {
		return player.getCombatLevel();
	}

	public Race getPlayerRace() {
		return player.getRace();
	}

	public ClassCard getPlayerClass() {
		return player.getCardClass();
	}
	
	public int getPlayerNumCards() {
		return player.getHand().size();
	}
	
	
	
}
