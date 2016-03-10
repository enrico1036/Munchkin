package network.message.server;

import java.util.ArrayList;

import game.Player;
import network.message.Message;

public final class PlayerListMessage extends Message {
	private String[] playerslist;
	//PLAYER_LIST<>nome1<>nome2<>nome3\n
	public PlayerListMessage(final String[] list){
		super(Message.PLAYER_LIST);
		playerslist = list;
	}
	
	public PlayerListMessage(final ArrayList<Player> players) {
		super(Message.PLAYER_LIST);
		playerslist = new String[players.size()];
		for (int i = 0; i < playerslist.length; i++) {
			playerslist[i] = players.get(i).getUsername();
		}
	}

	public String[] getPlayers() {
		return playerslist;
	}
/*
	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		for (int i = 0; i < playerslist.length; i++) {
			builder.append(Message.ARG_SEPARATOR);
			builder.append(playerslist[i]);
		}
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}*/

}
