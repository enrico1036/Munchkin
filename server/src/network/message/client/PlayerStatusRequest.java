package network.message.client;

import network.message.Message;

public class PlayerStatusRequest extends Message {
	
	public static final String REQUEST_PLAYER_FULL_STATS = "PLAYER_STATS";
	public static final String REQUEST_PLAYER_EQUIPMENT = "PLAYER_EQUIPMENT";
	
	private String player;
	private String statusRequest;
	
	public PlayerStatusRequest(String Player,String Request) {
		super(Message.PLAYER_STATUS_REQUEST);
		this.player = Player;
		this.statusRequest = Request;
	}

	public String getPlayer() {
		return player;
	}

	public String getStatusRequest() {
		return statusRequest;
	}


	

	
}
