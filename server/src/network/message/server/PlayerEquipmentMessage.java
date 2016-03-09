package network.message.server;

import java.util.ArrayList;

import game.GameManager;
import game.Player;
import network.message.Message;
import network.message.client.PlayerStatusRequest;

public class PlayerEquipmentMessage extends PlayerStatusRequest {

	private static String Player;
	private Player selected;
	
	public PlayerEquipmentMessage(String request) {
		super(Player,PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT);
		String [] playerslist = new String[GameManager.getPlayers().size()];
		for (int i = 0; i < playerslist.length; i++) {
			playerslist[i] = players.get(i).getUsername();
		}
		}
		
		
	}
	
	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(cardName);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(action.toString());
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}

}
