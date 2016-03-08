package network.message;

import java.util.List;

import game.GameManager;
import network.message.client.ChatMessage;
import network.message.client.ClientGeneralRequest;
import network.message.client.ConnectionRequestMessage;
import network.message.server.DrawCardMessage;
import network.message.server.PlayerListMessage;

public abstract class Message {
	/*
	 * 	MESSAGE CODES LIST START
	 */
	public static final String ARG_SEPARATOR = "<>";
	public static final String MSG_TERMINATOR = "\n";
	// Server response to client action
	public static final String SRV_ACTION_RESULT = "ACTION_RESULT";

	// Client connection 
	public static final String CLT_REQUEST_CONNECTION = "REQUEST_CONNECTION";
	// Lobby chat message
	public static final String CLT_CHAT_MESSAGE = "CHAT_MESSAGE";
	
	// Get card from treasure deck
	public static final String TREASURE_CARD_REQUEST = "TREASURE_CARD";
	
	//Get card from door deck
	public static final String DRAW_CARD = "DRAW_CARD";
	
	//Get player list and the ready status of that player
	public static final String PLAYER_LIST = "PLAYER_LIST";
	public static final String CLT_READY_STATUS = "READY_STATUS";
	
	//Send the card that player want to use like equipment, class or race
	public static final String CLT_EQUIP_CARD = "EQUIP_CARD";
	public static final String CLT_CHANGE_CLASS = "CHANGE_CLASS";
	public static final String CLT_CHANGE_RACE = "CHANGE_RACE";
	
	//Ask help to other players while the client is on combat phase
	public static final String CLT_REQUEST_HELP = "ASK_FOR_HELP";
	
	//Other players can propose help with how many treasures they want
	public static final String CLT_PROPOSE_HELP = "PROPOSE_HELP";
	
	//Client respond message
	public static final String CLT_ACCEPT_HELP = "ACCEPT_HELP";
	public static final String CLT_REJECT_HELP = "REJECT_HELP";
	
	//Get the card/s that other players can play to interpose on the client combat
	public static final String CLT_COMBAT_INTERPOSE_CARD = "PLAY_COMBAT_CARD";
	
	//Get the player phase
	public static final String CLT_PHASE = "PLAYER_PHASE";
	
	//All message to get players statistics
	public static final String REQUEST_PLAYER_FULL_STATUS = "PLAYER_STATUS";
	public static final String REQUEST_PLAYER_FORCE_VALUE = "PLAYER_FORCE";
	public static final String REQUEST_PLAYER_EQUIPMENT = "PLAYER_EQUIPMENT";
	
	//General client request that groups request client message
	public static final String CLT_GENERAL_REQUEST = "GENERAL_REQUEST";
	
	//Send to server that a card in a client was clicked on
	public static final String CLT_CARD_SELECTED = "CARD_SELECTED";
	
	
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
			
		case Message.DRAW_CARD:
			return new DrawCardMessage(tokens.get(1),Boolean.getBoolean(tokens.get(2)));
			
		case Message.PLAYER_LIST:
			tokens.remove(0);
			return new PlayerListMessage((String[])tokens.toArray());
		case Message.CLT_GENERAL_REQUEST:
			return new ClientGeneralRequest(tokens.get(1));

		default:
			if(tokens.size() == 1)
				return new NoArgMessage(tokens.get(0));
			break;
		}
		
		return null;
		
	}
}
