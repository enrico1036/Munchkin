package network.message.server;

import java.util.ArrayList;

import game.Player;
import network.message.Message;

public class ReadyLobbyMessage extends Message {

	private String[] readyList;
	
	public ReadyLobbyMessage(final String[] list){
		super(Message.CLT_READY_STATUS);
		readyList = list;
	}
	
	public ReadyLobbyMessage(final ArrayList<Player> players) {
		super(Message.CLT_READY_STATUS);
		readyList = new String[players.size()];
		int k=0;
		for (int i = 0; i < readyList.length; i++) {
			if(players.get(i).isLobby_ready()){
			readyList[k] = players.get(i).getUsername();
			k++;
			}
		}
	}

	public String[] getPlayers() {
		return readyList;
	}
/*
	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		for (int i = 0; i < readyList.length; i++) {
			builder.append(Message.ARG_SEPARATOR);
			builder.append(readyList[i]);
		}
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}*/

}
