 package network.message.server;

import cards.CardType;
import network.message.Message;

/* Message used from server to tell a client to show, draw, or discard a card */

public class DrawCardMessage extends Message{
	private String cardName;
	private Action action;
	public static enum Action {SHOW, DRAW, DISCARD};	//If SHOW just paint the card on the main table, if DRAW put that card in client's hand
														//If DISCARD, ask the player to drop a card in his hand

	
	public DrawCardMessage(String cardName, Action action) {
		super(Message.DRAW_CARD);
		this.cardName = cardName;
		this.action = action;
	}
	
	public String getCardName() {
		return cardName;
	}
	public Action getShowed(){
		return action;
	}


}
