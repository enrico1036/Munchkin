package game;

import java.util.ArrayList;

import cards.Card;
import cards.Equipment;
import cards.Race;

public class Player {
	
	private String username;
	private int level;
	private cards.Race[] race = new cards.Race[2];
	private int raceAllowed;
	private cards.Class[] playerClass = new cards.Class[2];
	private int classAllowed;
	private ArrayList <Card> hand;
	private ArrayList <Card> table;
	private cards.Equipment head;
	private cards.Equipment hand1;
	private cards.Equipment hand2;
	private cards.Equipment body;
	private cards.Equipment feet;
	private boolean alive;
	private final int handSize = 5;
	
	public Player(String username) {
		this.level = 0;
		this.hand = new ArrayList <Card>();
		this.table = new ArrayList <Card>();
		this.race[0] = new Race("Human");
		this.raceAllowed = 1;
		this.classAllowed = 1;
		this.alive = false;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void die() {
		this.hand.clear();
		this.table.clear();
		this.alive = false;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void setAlive() {
		this.alive = true;
	}
	
	public void leveleUp(int x) {
		this.level += x;
	}
	
	public boolean setRaceAllowed(int num) {
		switch (num) {
		case 1:
			this.raceAllowed = 1;
			break;
		case 2:
			this.raceAllowed = 2;
			break;
		default:
			this.raceAllowed = 1;
			return false;
		}
		return true;
	}
	
	enum whichRace {firstRace, secondRace};
	public Race getRace(whichRace which) {
		return this.race[which.ordinal()];
	}
	
	/* setRace(): update the player race. If 2 is allowed update the race selected by which parameter */
	public void setRace(Race newRace, whichRace which) {
		if(this.raceAllowed <= 1) {
			this.race[0] = newRace;
		} else {
			this.race[which.ordinal()] = newRace; 
		}
	}
	
	public boolean setClassAllowed(int num) {
		switch (num) {
		case 1:
			this.classAllowed = 1;
			break;
		case 2:
			this.classAllowed = 2;
			break;
		default:
			this.classAllowed = 1;
			return false;
		}
		return true;
	}
	
	enum whichClass {firstClass, secondClass};
	public cards.Class getClass(whichClass which) {
		return this.playerClass[which.ordinal()];
	}
	
	/* setClass(): update the player class. If 2 is allowed update the class selected by which parameter */
	public void setClass(cards.Class newClass, whichClass which) {
		if(this.classAllowed <= 1) {
			this.playerClass[0] = newClass;
		} else {
			this.playerClass[which.ordinal()] = newClass; 
		}
	}
	
	//true if hand is smaller than max, false otherwise;
	public boolean cardCheck() {
		return (this.hand.size() <= this.handSize) ? true : false;		
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public boolean discardCard(Card card) {
		return this.hand.remove(card);
	}
	
	public void draw(Card card) {
		this.hand.add(card);
	}
	
	public boolean equip(cards.EquipSlot slot, cards.Equipment card) {
		if(card.getSlot() != slot)
			return false;
		
		switch (slot) {
		case head:
			this.head = card;
			break;
		case body:
			this.body = card;
			break;
		case hand1:
			this.hand1 = card;
			break;
		case hand2:
			this.hand2 = card;
			break;
		case feet:
			this.feet = card;
			break;
		}
		
		return true;
	}

}
