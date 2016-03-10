 package network.message.server;

import network.message.Message;

/* Message used from server to tell a client to show or draw a card */

public class PlayCardMessage extends Message{
	private String cardName;
	private Action action;
	public static enum Action {SHOW, DRAW};	//If SHOW just paint the card on the main table, if DRAW put that card in client's hand
	
	public PlayCardMessage(String cardName, Action action) {
		super(Message.PLAY_CARD);
		this.cardName = cardName;
		this.action = action;
	}
	
	public String getCardName() {
		return cardName;
	}
	public Action getShowed(){
		return action;
	}
/*
	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(cardName);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(action.toString());
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}*/

}
