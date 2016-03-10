package network.message.server;

import network.message.Message;

public class StateUpdateMessage extends Message{
	private final String state; 
	
	public StateUpdateMessage(String state) {
		super(Message.STATE_UPDATE);
		this.state = state;
	}
	
	public String getState(){
		return state;
	}
	

}
