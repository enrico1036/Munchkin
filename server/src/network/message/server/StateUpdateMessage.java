package network.message.server;

import network.message.Message;

public class StateUpdateMessage extends Message{
	private final String Player;
	private final String state;
	
	public StateUpdateMessage(String Player, String state) {
		super(Message.STATE_UPDATE);
		this.Player = Player;
		this.state = state;
	}
	
	public String getPlayer() {
		return Player;
	}
	
	public String getState(){
		return state;
	}
	
}
