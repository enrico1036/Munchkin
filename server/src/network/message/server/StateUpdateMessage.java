package network.message.server;

import network.message.Message;

public class StateUpdateMessage extends Message{
	private final String currentPlayer;
	private final String state;
	
	public StateUpdateMessage(String currentPlayer, String state) {
		super(Message.STATE_UPDATE);
		this.currentPlayer = currentPlayer;
		this.state = state;
	}
	
	public String getCurrentPlayer() {
		return currentPlayer;
	}
	
	public String getState(){
		return state;
	}
	
}
