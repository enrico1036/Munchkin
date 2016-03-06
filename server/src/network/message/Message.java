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
	
	// Get card from decks
	public static final String DOOR_CARD_REQUEST = "DOOR_CARD";
	public static final String TREASURE_CARD_REQUEST = "TREASURE_CARD";
	public static final String DRAWN_CARD = "DRAWN_CARD";
	
	
	public static final String PLAYER_LIST = "PLAYER_LIST";
	public static final String CLT_READY_STATUS = "READY_STATUS";
	public static final String CLT_EQUIP_CARD = "EQUIP_CARD";
	public static final String CLT_REQUEST_HELP = "ASK_FOR_HELP";
	public static final String CLT_PROPOSE_HELP = "PROPOSE_HELP";
	public static final String CLT_ACCEPT_HELP = "ACCEPT_HELP";
	public static final String CLT_REJECT_HELP = "REJECT_HELP";
	public static final String CLT_COMBAT_INTERPOSE_CARD = "PLAY_COMBAT_CARD";
	
	
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
			return new ActionResultMessage(tokens.get(1), tokens.get(2));
		
		case Message.CLT_CHAT_MESSAGE:
			return new ChatMessage(tokens.get(1), tokens.get(2));
			
		case Message.DRAWN_CARD:
			return new DrawnCardMessage(tokens.get(1));
		
		default:
			if(tokens.size() == 1)
				return new NoArgMessage(tokens.get(0));
			break;
		}
		
		return null;
		
	}
}
