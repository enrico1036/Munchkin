package client;

import java.util.ArrayList;

import javax.swing.JPanel;

public class HandManager {

	private ArrayList<HandCards> hand;
	private JPanel handPanel;
	
	public HandManager(JPanel Hand){
		
		this.handPanel=Hand;
		
		hand = new ArrayList<HandCards>();
		//First draw 
		for(int i=0; i<2; i++) {
			//drawCard(DeckManager.getDoorCard());
			//drawCard(DeckManager.getTreasureCard());
		}
		
	}
	
	public ArrayList<HandCards> getHand() {
		return hand;
	}
	
	public void drawCard(HandCards cardDrawn){
		hand.add(cardDrawn);
		
		this.handPositioning();
		
		
	}
	
	public HandCards getCard(String Title){	//TODO: forse non serve però in caso mi sa che è meglio passargli un istanza di carta
		HandCards selected = null;
		for(HandCards card : hand){
			if(card.getTitle().equals(Title)){
				selected = card;
			}
		}
		return selected;
	}
	
	public void discard(HandCards cardDiscarded){
		//DeckManager.discardCard(hand.remove(hand.indexOf(cardDiscarded)));
	}
	
	public int getSize() {
		return this.hand.size();
	}
	public void handPositioning(){
		int handSize,initpos,handWidth,handHeight;
		handSize= hand.size();
		handWidth=handPanel.getWidth();
		handHeight=handPanel.getHeight();
		initpos=handWidth/2-((handSize/2)*(handWidth/12));
		
		for(int i=0;i<hand.size();i++){
		
			hand.get(i).setBounds(initpos+i*handWidth/14, handHeight/4, handWidth/7, handHeight/2);
			
		}
	
	}
}
