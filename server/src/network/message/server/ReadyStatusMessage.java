package network.message.server;

import game.GameManager;
import game.Player;
import network.message.Message;

public class ReadyStatusMessage extends Message{

	
	public ReadyStatusMessage(Player player) {
		super(Message.CLT_SET_LOBBY_STATUS);
		player.setLobby_ready((!player.isLobby_ready()));
		}


	
	

}
