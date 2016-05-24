 package network.message.server;

import cards.Card;
import cards.CardType;
import network.message.Message;

/* Message used from server to tell a client to show, draw, or discard a card */

public class PlayCardMessage extends Message{
	private String cardName;
	private Action action;
	public static enum Action {SHOW, DRAW, DISCARD, REMOVE, CLEANTABLE};	//If SHOW just paint the card on the main table, if DRAW put that card in client's hand
																			//If DISCARD put a card in the garbage stack, if REMOVE remove a card from a player's hand
																			//If CLEANTABLE remove the card from table

	
	public PlayCardMessage(Card card, Action action) {
		super(Message.PLAY_CARD);
		this.cardName = card.getTitle();
		this.action = action;
	}
	
	public String getCardName() {
		return cardName;
	}
	public Action getAction(){
		return action;
	}
	
}
