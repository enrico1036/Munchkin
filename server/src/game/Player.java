package game;

import java.util.ArrayList;

import cards.Card;
import cards.ClassCard;
import cards.EquipSlot;
import cards.Equipment;
import cards.Race;
import network.ClientConnection;
import network.message.Message;

public class Player {
	private ClientConnection connection;
	private String username;
	private int level;
	private boolean lobby_ready;
	private Race race;
	private int raceAllowed;
	private ClassCard playerClass ;
	private int classAllowed;
	private ArrayList <Card> hand;
	private ArrayList <Card> table;
	private Equipment head;
	private Equipment hand1;
	private Equipment hand2;
	private Equipment body;
	private Equipment feet;
	private boolean alive;
	private final int handSize = 5;
	
	public Player(String username) {
		this.username = username;
		this.level = 0;
		this.hand = new ArrayList <Card>();
		this.table = new ArrayList <Card>();
		this.race = new Race("Human");
		this.raceAllowed = 1;
		this.classAllowed = 1;
		this.alive = false;
		this.connection = null;
		this.lobby_ready=false;
	}
	
	public String getUsername() {
		return username;
	}
	
	
	
	
	public boolean isLobby_ready() {
		return lobby_ready;
	}

	public void setLobby_ready(boolean lobby_ready) {
		this.lobby_ready = lobby_ready;
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
	
	public int getLevel() {
		return level;
	}
	
	
	
	public int getCombatLevel() {
		int combatLevel = 0;
		combatLevel += this.level;
		combatLevel += head.getBonus();
		combatLevel += body.getBonus();
		combatLevel += hand1.getBonus();
		combatLevel += hand2.getBonus();
		combatLevel += feet.getBonus();
		return combatLevel;
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
	
	
	public Race getRace() {
		return this.race;
	}
	
	/* setRace(): update the player race. If 2 is allowed update the race selected by which parameter */
	public void setRace(Race newRace) {
	
			this.race= newRace;
		
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
	
	
	public ClassCard getCardClass() {
		return this.playerClass;
	}
	
	
	public void setClass(ClassCard newClass) {
		
			this.playerClass = newClass;
		
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
	
	public Card pickCard(String title) {
		for(Card c : hand){
			if(c.getTitle().equals(title)){
				hand.remove(c);
				return c;
			}
		}
		return null;
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
	
	public void sendMessage(Message message){
		connection.write(message);
	}
	
	public boolean isConnected(){
		return connection != null && connection.isAlive();
	}
	
	public void setConnection(final ClientConnection connection){
		this.connection = connection;
	}
	
	public Equipment getEquipment(EquipSlot slot){
		Equipment card = null;
		switch (slot) {
		case head:
			card = this.head ;
			break;
		case body:
			card=this.body;
			break;
		case hand1:
			card=this.hand1;
			break;
		case hand2:
			card=this.hand2;
			break;
		case feet:
			card=this.feet;
			break;
		}
		return card;
	}

}
