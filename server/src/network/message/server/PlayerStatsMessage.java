package network.message.server;

import game.Player;
import network.message.Message;
import network.message.client.PlayerStatusRequest;

public class PlayerStatsMessage extends PlayerStatusRequest {

	private static String player;
	private Player selected;
	
	public PlayerStatsMessage(String request) {
		super(player,PlayerStatusRequest.REQUEST_PLAYER_FULL_STATS);
	
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
