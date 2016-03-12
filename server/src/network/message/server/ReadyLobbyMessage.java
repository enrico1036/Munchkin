package network.message.server;

import java.util.ArrayList;

import game.Player;
import network.message.Message;

public class ReadyLobbyMessage extends Message {

	private String[] readyList;
	private boolean[] readyStatus;
	
	public ReadyLobbyMessage(final String[] list){
		super(Message.CLT_READY_STATUS);
		readyList = list;
	}
	
	public ReadyLobbyMessage(final ArrayList<Player> players) {
		super(Message.CLT_READY_STATUS);
		readyList = new String[players.size()];
		readyStatus = new boolean[players.size()];
		for (int i = 0; i < readyList.length; i++) {
			readyList[i] = players.get(i).getUsername();
			readyStatus[i] = players.get(i).isLobby_ready();
			
			
		}
	}
	public boolean[] getStatus(){
		return readyStatus;
	}
	public String[] getPlayers() {
		return readyList;
	}


}
