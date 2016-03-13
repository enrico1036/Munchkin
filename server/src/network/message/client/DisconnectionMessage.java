package network.message.client;

import network.message.Message;

public class DisconnectionMessage extends Message {

	public DisconnectionMessage() {
		super(Message.CLT_DISCONNECTION);
		
	}
	
}
