package network.message.server;

import network.message.Message;

public class PlayerStatusRequest extends Message {
	
	public static final String REQUEST_PLAYER_FULL_STATS = "PLAYER_STATS";
	public static final String REQUEST_PLAYER_EQUIPMENT = "PLAYER_EQUIPMENT";
	
	private String player;
	private String plr_request;
	
	public PlayerStatusRequest(String Player,String Request) {
		super(Message.PLAYER_STATUS_REQUEST);
		this.player = Player;
		this.plr_request = Request;
	}

	public String getPlayer() {
		return player;
	}

	public String getPlr_request() {
		return plr_request;
	}


	

	
}
