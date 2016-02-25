package client;

import java.util.ArrayList;

public class HandHandler {

	private int size=10;
	private ArrayList<ClientCard> Hand;
	private ClientCard card1,card2;
	
	
	public ArrayList<ClientCard> getHand() {
		return Hand;
	}

	public HandHandler(){
		
		Hand = new ArrayList<ClientCard>(size);
		//è da inserire il metodo che crea la mano all'inizio della partita
		card1=new ClientCard("ciao");
		card2=new ClientCard("ciao2");
		
		Hand.add(card1);
		Hand.add(card2);
		
		
		

	}
	public void draw(ClientCard cardDrawn){
		Hand.add(cardDrawn);
	
	}
	public void discard(ClientCard cardDiscarded){
	
		for(ClientCard card : Hand){
			if(card.equals(cardDiscarded)){
				Hand.remove(card);
			}
		}

	}
	public ClientCard getCard(String Title){
	
		ClientCard selected = null;
		for(ClientCard card : Hand){
			if(card.getTitle().equals(Title)){
				selected= card;
			}
		}
		return selected;
		
		
	}
	
}
