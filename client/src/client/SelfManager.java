package client;

import java.util.ArrayList;


public class SelfManager extends ClientPlayer {

	public SelfManager(String user) {
		super(user);
	}

	private ArrayList <ClientCard> hand;
	
	

	public ArrayList<ClientCard> getHand() {
		return hand;
	}

	public void setHand(ArrayList<ClientCard> hand) {
		this.hand = hand;
	}
}
