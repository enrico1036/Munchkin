package network.message;

import java.util.List;

public abstract class Message {
	/*
	 * 	MESSAGE CODES LIST START
	 */
	public static final String ARG_SEPARATOR = "<>";
	public static final String MSG_TERMINATOR = "\n";
	// Server response to client action
	public static final String SRV_ACTION_RESULT = "ACTION_RESULT";
	public static final String ACTION_ALLOWED = "ACTION_ALLOWED";
	public static final String ACTION_DENIED = "ACTION_DENIED";
	// Client connection 
	public static final String CLT_REQUEST_CONNECTION = "REQUEST_CONNECTION";
	// Lobby chat message
	public static final String CLT_CHAT_MESSAGE = "CHAT_MESSAGE";
	/*
	 * 	MESSAGE CODES LIST END
	 */
	
	protected final String messageCode;
	
	public Message(String code){
		this.messageCode = code;
	}
	
	public String getMessageCode(){
		return messageCode;
	}
	
	public abstract String getFormattedMessage();
	
	public static Message parse(String formattedMessage){
		List<String> tokens = StringUtils.split(formattedMessage, Message.ARG_SEPARATOR);
		
		if(tokens.size() == 0)
			return null;
		
		switch (tokens.get(0)){
		case Message.CLT_REQUEST_CONNECTION:
			return new ConnectionRequestMessage(tokens.get(1));
			
		case Message.SRV_ACTION_RESULT:
			return new ActionResultMessage(tokens.get(1));
		
		case Message.CLT_CHAT_MESSAGE:
			return new ChatMessage(tokens.get(1), tokens.get(2));
			
		default:
			break;
		}
		
		return null;
	}
}
