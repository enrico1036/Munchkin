package network.message.server;

import cards.EquipSlot;
import cards.Equipment;
import game.Player;
import network.message.Message;
import network.message.client.PlayerStatusRequest;

public class PlayerEquipmentMessage extends Message {

	private String playerName;
	private String head;
	private String hand1;
	private String hand2;
	private String body;
	private String feet;

	public PlayerEquipmentMessage(Player player) {
		super(PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT);

		playerName = player.getUsername();
		
		try {
			head = player.getEquipment(EquipSlot.head).getTitle();
		} catch (NullPointerException e) {
			head = "";
		}
		
		try {
			hand1 = player.getEquipment(EquipSlot.hand1).getTitle();
		} catch (NullPointerException e) {
			hand1 = "";
		}
		
		try {
			hand2 = player.getEquipment(EquipSlot.hand2).getTitle();
		} catch (NullPointerException e) {
			hand2 = "";
		}
		
		try {
			body = player.getEquipment(EquipSlot.body).getTitle();
		} catch (NullPointerException e) {
			body = "";
		}
		
		try {
			feet = player.getEquipment(EquipSlot.feet).getTitle();
		} catch (NullPointerException e) {
			feet = "";
		}
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getHead() {
		return head;
	}

	public String getHand1() {
		return hand1;
	}

	public String getHand2() {
		return hand2;
	}

	public String getBody() {
		return body;
	}

	public String getFeet() {
		return feet;
	}

}
