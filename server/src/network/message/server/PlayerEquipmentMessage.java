package network.message.server;

import java.util.ArrayList;

import cards.EquipSlot;
import cards.Equipment;
import game.GameManager;
import game.Player;
import network.message.Message;

public class PlayerEquipmentMessage extends PlayerStatusRequest {

	private static String Player;
	private String playerName;
	private Equipment head;
	private Equipment hand1;
	private Equipment hand2;
	private Equipment body;
	private Equipment feet;
	
	public PlayerEquipmentMessage() {
		super(Player,PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT);
		Player player= GameManager.getPlayerByName(Player);
				
				playerName=player.getUsername();
				head=player.getEquipment(EquipSlot.head);
				hand1=player.getEquipment(EquipSlot.hand1);
				hand2=player.getEquipment(EquipSlot.hand2);
				body=player.getEquipment(EquipSlot.body);
				feet=player.getEquipment(EquipSlot.feet);

	}

	public String getPlayerName() {
		return playerName;
	}

	public Equipment getHead() {
		return head;
	}

	public Equipment getHand1() {
		return hand1;
	}

	public Equipment getHand2() {
		return hand2;
	}

	public Equipment getBody() {
		return body;
	}

	public Equipment getFeet() {
		return feet;
	}
	
	
}
