package network.message.client;

import network.message.Message;

public class ClientGeneralRequest extends Message{
	
	public static final String REQUEST_TREASURE_CARDS = "TREASURE_CARDS";
	public static final String REQUEST_PLAYERS_LIST = "PLAYERS_LIST";
	public static final String REQUEST_DRAWN_CARD ="DRAW_CARD";
	
	private String cltRequest;
	
	public ClientGeneralRequest(final String request)
	{
		super(Message.CLT_GENERAL_REQUEST);
		this.cltRequest = request;
	}
	
	public String getRequestType(){
		return cltRequest;
	}

	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(cltRequest);
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
		
		
	}
}
