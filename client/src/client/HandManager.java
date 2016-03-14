package client;

import java.util.ArrayList;

import javax.swing.JPanel;
/**
 * 
 * 
 * This class manage the hand of the client.
 * With the method draw and discard we can add and remove the card on the client hand.
 * Hand Position is used the draw dinamically the hand of a player
 * 
 */
public class HandManager {

	private ArrayList<HandCards> hand;
	private JPanel handPanel;
	
	/**
	 * Constructor method that created the hand manager
	 * @param Hand: it's the GameUI panel where we will draw all cards of the client hand.
	 */
	public HandManager(JPanel Hand){
		
		/**
		 * Create the arraylist of hand card and set the handpanel
		 */
		handPanel=Hand;
		hand = new ArrayList<HandCards>();
		
		
		/**
		 * Now we must initialize the player hand with the first draw:
		 * because everytime the game start all player must take 4 cards from decks
		 * Take 2 cards from the treasure deck
		 * Take 2 cards from the door deck
		 * 
		 */
		for(int i=0; i<2; i++) {
			//TODO CARICARE LA MANO DEL GIOCATORE
			//drawCard(DeckManager.getDoorCard());
			//drawCard(DeckManager.getTreasureCard());
		}
		
	}
	/**
	 * Get the arraylist of the card in the client hand
	 * @return The arrayList of Hand Cards
	 */
	public ArrayList<HandCards> getHand() {
		return hand;
	}
	/**
	 * It is used when a player draw a card and it add that card in the player hand
	 * @param cardDrawn: the card that has drawn
	 */
	public void drawCard(HandCards cardDrawn){
		hand.add(cardDrawn);
		
		handPositioning();
		
		
	}
	/**
	 * It is used to get a card from the client hand.
	 * 
	 * @param Title: the title of the card that we want to find
	 * @return a handcards that has the same title of the param Title
	 */
	public HandCards getCard(String Title){	
		HandCards selected = null;
		for(HandCards card : hand){
			if(card.getTitle().equals(Title)){
				selected = card;
			}
		}
		return selected;
	}
	/**
	 * It is used to remove a card from the player hand
	 * @param cardDiscarded: the card that we want to remove
	 */
	public void discard(HandCards cardDiscarded){
		//TODO DEVO SCARTARE LA CARTE E METTERLA DOVE??
		//DeckManager.discardCard(hand.remove(hand.indexOf(cardDiscarded)));
	}
	/**
	 * Return the size of the player hand
	 * @return an int that stands for the player hand size
	 */
	public int getSize() {
		return hand.size();
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
