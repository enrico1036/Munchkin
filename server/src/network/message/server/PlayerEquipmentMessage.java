package network.message.server;

import java.util.ArrayList;

import cards.EquipSlot;
import cards.Equipment;
import game.GameManager;
import game.Player;
import network.message.Message;
import network.message.client.PlayerStatusRequest;

public class PlayerEquipmentMessage extends PlayerStatusRequest {

	private static String Player;
	private Equipment head;
	private Equipment hand1;
	private Equipment hand2;
	private Equipment body;
	private Equipment feet;
	
	public PlayerEquipmentMessage() {
		super(Player,PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT);
		boolean founded=false;
		int size = GameManager.getPlayers().size();
		for (int i = 0; i < size&&!founded; i++) {
			if(Player == GameManager.getPlayers().get(i).getUsername()){
				founded=true;
				head=GameManager.getPlayers().get(i).getEquipment(EquipSlot.head);
				hand1=GameManager.getPlayers().get(i).getEquipment(EquipSlot.hand1);
				hand2=GameManager.getPlayers().get(i).getEquipment(EquipSlot.hand2);
				body=GameManager.getPlayers().get(i).getEquipment(EquipSlot.body);
				feet=GameManager.getPlayers().get(i).getEquipment(EquipSlot.feet);
			}
		}
		
		
	}
	/*
	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(head.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(hand1.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(hand2.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(body.getTitle());
		builder.append(Message.ARG_SEPARATOR);
		builder.append(feet.getTitle());
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}*/

}
