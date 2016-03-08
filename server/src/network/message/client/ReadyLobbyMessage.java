package network.message.client;

import network.message.Message;

public class ReadyLobbyMessage extends Message {

	public ReadyLobbyMessage() {
		super(Message.CLT_READY_STATUS);
		
	}

	@Override
	public String getFormattedMessage() {
		
		return null;
	}

}
