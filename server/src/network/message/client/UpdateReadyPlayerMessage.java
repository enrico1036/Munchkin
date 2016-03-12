package network.message.client;

import network.message.Message;

public class UpdateReadyPlayerMessage extends ClientGeneralRequest{

	public UpdateReadyPlayerMessage() {
		super(Message.CLT_UPDATE_READY_PLAYER_MESSAGE);
		
	}

}
