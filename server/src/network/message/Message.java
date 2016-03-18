package network.message;

import java.io.Serializable;

public abstract class Message implements Serializable {

	protected static final long serialVersionUID = 1L;
	/*
	 * MESSAGE CODES LIST START
	 */
	public static final String ARG_SEPARATOR = "<>";
	public static final String MSG_TERMINATOR = "\n";
	// Server response to client action
	public static final String SRV_ACTION_RESULT = "ACTION_RESULT";

	// Client connection
	public static final String CLT_REQUEST_CONNECTION = "REQUEST_CONNECTION";
	public static final String CLT_DISCONNECTION = "DISCONNECTION";
	// Lobby chat message
	public static final String CLT_CHAT_MESSAGE = "CHAT_MESSAGE";

	// Send a message to the Users' LobbyUIs to advice them that one or more players change their ready status
	public static final String CLT_UPDATE_READY_PLAYER_MESSAGE = "UPDATE_READY_PLAYER";

	// Toggle the ready status of one player
	public static final String CLT_SET_LOBBY_STATUS = "LOBBY_STATUS";

	// Get the player state
	public static final String STATE_UPDATE = "STATE_UPDATE";

	// Send to server that a card in a client was clicked on
	public static final String CLT_CARD_SELECTED = "CARD_SELECTED";

	/*
	 * GENERAL REQUEST
	 */

	// General client request that groups request client message
	public static final String CLT_GENERAL_REQUEST = "GENERAL_REQUEST";

	// Get the entire players list with the value of their ready status
	public static final String CLT_READY_STATUS = "READY_STATUS";

	// Get card from decks
	public static final String PLAY_CARD = "PLAY_CARD";

	// Play a general card
	public static final String PLAY_GENERAL_CARD = "GENERAL_CARD";
	/*
	 * END GENERAL REQUEST
	 */

	/*
	 * PLAYER STATUS REQUEST
	 */

	// Player status request message
	public static final String PLAYER_STATUS_REQUEST = "PLAYER_STATUS";

	// All message to get players statistics
	public static final String PLAYER_FULL_STATS = "PLAYER_STATS";
	public static final String PLAYER_EQUIPMENT = "PLAYER_EQUIPMENT";

	/*
	 * COMMAND MESSAGE
	 */

	// Show on a client view a popup message
	public static final String POPUP = "POPUP";
	// Show the result of a popup message
	public static final String CLT_POPUP_RESULT = "POPUP_RESULT";

	// Tell to a client to show with the dice on the correct face
	public static final String DICE_RESULT = "DICE_RESULT";

	/*
	 * MESSAGE CODES LIST END
	 */

	protected final String messageCode;

	public Message(String code) {
		this.messageCode = code;
	}

	public String getMessageCode() {
		return messageCode;
	}

}
