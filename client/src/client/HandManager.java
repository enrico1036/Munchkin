package client;

import java.util.ArrayList;

public class HandManager {

	private ArrayList<ClientCard> hand;
	

	public HandManager(){
		hand = new ArrayList<ClientCard>();
		//First draw 
		for(int i=0; i<2; i++) {
			//drawCard(DeckManager.getDoorCard());
			//drawCard(DeckManager.getTreasureCard());
		}
	}
	
	public ArrayList<ClientCard> getHand() {
		return hand;
	}
	
	public void drawCard(ClientCard cardDrawn){
		hand.add(cardDrawn);
	}
	
	public ClientCard getCard(String Title){	//TODO: forse non serve però in caso mi sa che è meglio passargli un istanza di carta
		ClientCard selected = null;
		for(ClientCard card : hand){
			if(card.getTitle().equals(Title)){
				selected = card;
			}
		}
		return selected;
	}
	
	public void discard(ClientCard cardDiscarded){
		//DeckManager.discardCard(hand.remove(hand.indexOf(cardDiscarded)));
	}
	
	public int getSize() {
		return this.hand.size();
	}
}
