package user_interface;

import java.util.ArrayList;

import javax.swing.JPanel;

import client.MunchkinClient;
import prove.InterfaceUI.ClientCard;
import prove.InterfaceUI.ZoomedPanel;
/**
 * 
 * 
 * This class manage the hand of the client.
 * With the method draw and discard we can add and remove the card on the client hand.
 * Hand Position is used the draw dinamically the hand of a player
 * 
 */
public class HandManager {

	private ArrayList<ClientCard> hand;
	private JPanel handPanel;
	private ZoomedPanel zp;
	
	/**
	 * Constructor method that created the hand manager
	 * @param Hand: it's the GameUI panel where we will draw all cards of the client hand.
	 */
	public HandManager(JPanel Hand,ZoomedPanel zp){
		
		/**
		 * Create the arraylist of hand card and set the handpanel
		 */
		handPanel=Hand;
		handPanel.setVisible(true);
		this.zp=zp;
		hand = new ArrayList<ClientCard>();
		
	}
	/**
	 * Get the arraylist of the card in the client hand
	 * @return The arrayList of Hand Cards
	 */
	public ArrayList<ClientCard> getHand() {
		return hand;
	}
	/**
	 * It is used when a player draw a card and it add that card in the player hand
	 * @param cardDrawn: the card that has drawn
	 */
	public void drawCard(ClientCard cardDrawn){
		cardDrawn.CreateCard(MunchkinClient.getImage(cardDrawn.getTitle()), zp);
		hand.add(cardDrawn);
		handPositioning();
		
		
	}
	/**
	 * It is used to get a card from the client hand.
	 * 
	 * @param Title: the title of the card that we want to find
	 * @return a handcards that has the same title of the param Title
	 */
	public ClientCard getCard(String Title){	
		ClientCard selected = null;
		for(ClientCard card : hand){
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
	public void remove(ClientCard cardDiscarded){
		hand.remove(cardDiscarded);
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
