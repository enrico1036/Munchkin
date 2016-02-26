package client;

import java.util.ArrayList;
import java.util.Collections;

public class DeckManager {

	private static ArrayList<ClientCard> doorDeck = new ArrayList<ClientCard>();
	private static ArrayList<ClientCard> doorDiscards = new ArrayList<ClientCard>();
	private static ArrayList<ClientCard> treasureDeck = new ArrayList<ClientCard>();
	private static ArrayList<ClientCard> treasureDiscards = new ArrayList<ClientCard>();
	
	public static void loadDecks() {
		// TODO: load all cards in decks (maybe from xml file)
		//this is just for test purpose
		for(Integer i=0; i<50; i++) {
			doorDeck.add(new ClientCard(i.toString(), CardType.Door));
			treasureDeck.add(new ClientCard(i.toString(), CardType.Door));
		}
	}
	
	public static void shuffleDecks() {
		Collections.shuffle(doorDeck);
		Collections.shuffle(treasureDeck);
	}
	
	public static ClientCard getDoorCard() {
		ClientCard card;
		if(doorDeck.size() < 1) {
			doorDeck.addAll(doorDiscards);
			Collections.shuffle(doorDeck);
		}
		card = doorDeck.remove(0);
		return card;
	}
	
	public static ClientCard getTreasureCard() {
		ClientCard card;
		if(treasureDeck.size() < 1) {
			treasureDeck.addAll(treasureDiscards);
			Collections.shuffle(treasureDeck);
		}
		card = treasureDeck.remove(0);
		return card;
	}
	
	public static void discardCard(ClientCard card) {
		if(card.getType() == CardType.Door)
			doorDiscards.add(card);
		else
			treasureDiscards.add(card);
	}
}
