package network.message.client;

import network.message.Message;

public class ClientGeneralRequest extends Message{
	
	public static final String REQUEST_READY_PLAYER_LIST="READY_LIST";

	
	private String cltRequest;
	
	public ClientGeneralRequest(final String request)
	{
		super(Message.CLT_GENERAL_REQUEST);
		this.cltRequest = request;
	}
	
	
	public String getRequestType(){
		return cltRequest;
	}

}
