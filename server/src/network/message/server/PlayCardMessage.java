 package network.message.server;

import cards.CardType;
import network.message.Message;

/* Message used from server to tell a client to show, draw, or discard a card */

public class PlayCardMessage extends Message{
	private String cardName;
	private CardType type;
	private Action action;
	public static enum Action {SHOW, DRAW, DISCARD};	//If SHOW just paint the card on the main table, if DRAW put that card in client's hand
														//If DISCARD, ask the player to drop a card in his hand

	
	public PlayCardMessage(String cardName,CardType type, Action action) {
		super(Message.PLAY_CARD);
		this.cardName = cardName;
		this.type=type;
		this.action = action;
	}
	
	public String getCardName() {
		return cardName;
	}
	public Action getShowed(){
		return action;
	}
	public CardType getType(){
		return type;
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
