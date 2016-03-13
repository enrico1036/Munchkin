 package network.message.server;

import cards.Card;
import cards.CardType;
import network.message.Message;

/* Message used from server to tell a client to show, draw, or discard a card */

public class PlayCardMessage extends Message{
	private Card card;
	private Action action;
	public static enum Action {SHOW, DRAW, DISCARD, REMOVE};	//If SHOW just paint the card on the main table, if DRAW put that card in client's hand
																//If DISCARD put a card in the garbage stack, if REMOVE remove a card from a player's hand

	
	public PlayCardMessage(Card card, Action action) {
		super(Message.DRAW_CARD);
		this.card = card;
		this.action = action;
	}
	
	public Card getCard() {
		return card;
	}
	public Action getAction(){
		return action;
	}


}
