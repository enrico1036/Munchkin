package client;

import java.util.ArrayList;


public class SelfManager extends ClientPlayer {

	private ArrayList <ClientCard> hand;
	
	public SelfManager(){
		
		
	}

	public ArrayList<ClientCard> getHand() {
		return hand;
	}

	public void setHand(ArrayList<ClientCard> hand) {
		this.hand = hand;
	}
}
