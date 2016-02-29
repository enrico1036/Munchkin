package game;

import java.util.ArrayList;
import java.util.Collections;

import cards.Card;
import cards.CardType;


public class Decks {

	private static ArrayList<Card> doorDeck = new ArrayList<Card>();
	private static ArrayList<Card> doorDiscards = new ArrayList<Card>();
	private static ArrayList<Card> treasureDeck = new ArrayList<Card>();
	private static ArrayList<Card> treasureDiscards = new ArrayList<Card>();
	
	/* Load the decks with the card in the xml file
	 * it's MANDATORY to call this method only one time at the begin of the game
	 * */
	public static void loadDecks() {
		// TODO: load all cards in decks (maybe from xml file)
		
		/* this is just for test purpose */
//		for(Integer i=0; i<50; i++) {
//			doorDeck.add(new Card(i.toString(), CardType.Door));
//			treasureDeck.add(new Card(i.toString(), CardType.Door));
//		}
	}
	
	public static void shuffleDecks() {
		Collections.shuffle(doorDeck);
		Collections.shuffle(treasureDeck);
	}
	
	/* Used to get a card from door deck
	 * assume that treasure deck is already loaded
	 * returns: the first card if deck has almost one, while fill deck with discard deck and shuffle it if deck was empty*/
	public static Card getDoorCard() {
		Card card;
		if(doorDeck.size() < 1) {
			doorDeck.addAll(doorDiscards);
			Collections.shuffle(doorDeck);
		}
		card = doorDeck.remove(0);
		return card;
	}
	
	/* Used to get a card from treasure deck
	 * assume that treasure deck is already loaded
	 * returns: the first card if deck has almost one, while fill deck with discard deck and shuffle it if deck was empty*/
	public static Card getTreasureCard() {
		Card card;
		if(treasureDeck.size() < 1) {
			treasureDeck.addAll(treasureDiscards);
			Collections.shuffle(treasureDeck);
		}
		card = treasureDeck.remove(0);
		return card;
	}
	
	/* Add the card passed in parameters to the proper discard deck checking the card type
	 * */
	
	public static void discardCard(Card card) {
		if(card.getType() == CardType.Door)
			doorDiscards.add(card);
		else
			treasureDiscards.add(card);
	}
}
