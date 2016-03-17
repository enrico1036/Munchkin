package game;

import java.util.ArrayList;

import cards.Card;
import cards.ClassCard;
import cards.EquipSlot;
import cards.Equipment;
import cards.Race;
import network.ClientConnection;
import network.message.Message;
import network.message.server.PlayCardMessage;
import network.message.server.PlayerFullStatsMessage;
import network.message.server.PlayCardMessage.Action;
import utils.PlayerEventListener;

public class Player {
	private ClientConnection connection;
	private PlayerEventListener eventListener;
	private String username;
	private int level;
	private boolean lobby_ready;
	private Race race;
//	private int raceAllowed;
	private ClassCard playerClass;
//	private int classAllowed;
	private ArrayList<Card> hand;
	private ArrayList<Card> table;
	private Equipment head;
	private Equipment hand1;
	private Equipment hand2;
	private Equipment body;
	private Equipment feet;
	private boolean alive;
	private int escapeTreshold;	// you can escape from monster only if rolling a die the result is higher or equal
	private final int handSize = 5;
	
	// Anonymous Player constructor used into ConnectionListener
	public Player(PlayerEventListener listener){
		this.level = 0;
		this.hand = new ArrayList<Card>();
		this.table = new ArrayList<Card>();
		this.race = new Race("Human");
		this.alive = false;
		this.connection = null;
		this.lobby_ready = false;
		this.escapeTreshold = 5;
		this.eventListener = listener;
	}
	
	// Default Player constructor
	public Player(String username, PlayerEventListener listener) {
		this(listener);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
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
		GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
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
	
	public int getEscapeTreshold() {
		return escapeTreshold;
	}
	
	public void setEscapeTreshold(int treshold) {
		this.escapeTreshold = treshold;
	}

	// public boolean setRaceAllowed(int num) {
	// switch (num) {
	// case 1:
	// this.raceAllowed = 1;
	// break;
	// case 2:
	// this.raceAllowed = 2;
	// break;
	// default:
	// this.raceAllowed = 1;
	// return false;
	// }
	// return true;
	// }

	public Race getRace() {
		return this.race;
	}

	// public boolean setClassAllowed(int num) {
	// switch (num) {
	// case 1:
	// this.classAllowed = 1;
	// break;
	// case 2:
	// this.classAllowed = 2;
	// break;
	// default:
	// this.classAllowed = 1;
	// return false;
	// }
	// return true;
	// }

	public ClassCard getCardClass() {
		return this.playerClass;
	}

	public void setRace(Race newRace) {
		this.race = newRace;
		GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
	}

	public void setClass(ClassCard newClass) {
		this.playerClass = newClass;
		GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
	}

	// true if hand is smaller than max, false otherwise;
	public boolean cardCheck() {
		return (this.hand.size() <= this.handSize) ? true : false;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public Card getHandCard(String title) {
		for (Card card : hand) {
			if (card.getTitle() == title)
				return card;
		}
		return null;
	}

	public Card pickCard(String title) {
		for (Card c : hand) {
			if (c.getTitle().equals(title)) {
				hand.remove(c);
				sendMessage(new PlayCardMessage(c, Action.REMOVE));
				return c;
			}
		}
		return null;
	}

	public void draw(Card card) {
		sendMessage(new PlayCardMessage(card, Action.DRAW));
		hand.add(card);
	}

	public boolean discardCard(Card card) {
		sendMessage(new PlayCardMessage(card, Action.REMOVE));
		return hand.remove(card);
	}
	
	public Card getCardByName(String cardName) {
		//first try to get that card from hand
		Card card = getHandCard(cardName);
		
		//then look for the card on table
		if(card == null) {
			if(cardName == race.getTitle()) {
				card = race;
			} else if(cardName == playerClass.getTitle()) {
				card = playerClass;
			} else if(cardName == head.getTitle()) {
				card = head;
			} else if(cardName == hand1.getTitle()) {
				card = hand1;
			} else if(cardName == hand2.getTitle()) {
				card = hand2;
			} else if(cardName == body.getTitle()) {
				card = body;
			} else if(cardName == feet.getTitle()) {
				card = feet;
			}
			GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
		}
		return card;
	}
	
	public Card removeCardByName(String cardName) {
		//first try to remove that card from hand
		Card card = pickCard(cardName);
		
		//then look for the card on table
		if(card == null) {
			if(cardName == race.getTitle()) {
				card = race;
				race = null;
			} else if(cardName == playerClass.getTitle()) {
				card = playerClass;
				playerClass = null;
			} else if(cardName == head.getTitle()) {
				card = head;
				head = null;
			} else if(cardName == hand1.getTitle()) {
				card = hand1;
				hand1 = null;
			} else if(cardName == hand2.getTitle()) {
				card = hand2;
				hand2 = null;
			} else if(cardName == body.getTitle()) {
				card = body;
				body = null;
			} else if(cardName == feet.getTitle()) {
				card = feet;
				feet = null;
			}
			GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
		}
		return card;
	}

	public boolean equip(cards.EquipSlot slot, cards.Equipment card) {
		if (card.getSlot() != slot)
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

		GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
		return true;
	}

	public Equipment getEquipment(EquipSlot slot) {
		Equipment card = null;
		switch (slot) {
		case head:
			card = this.head;
			break;
		case body:
			card = this.body;
			break;
		case hand1:
			card = this.hand1;
			break;
		case hand2:
			card = this.hand2;
			break;
		case feet:
			card = this.feet;
			break;
		}
		return card;
	}
	
	public Equipment removeEquipment(EquipSlot slot) {
		Equipment card = null;
		switch (slot) {
		case head:
			card = this.head;
			break;
		case body:
			card = this.body;
			break;
		case hand1:
			card = this.hand1;
			break;
		case hand2:
			card = this.hand2;
			break;
		case feet:
			card = this.feet;
			break;
		}
		GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
		return card;
	}
	

	public void sendMessage(Message message) {
		connection.write(message);
	}

	public boolean isLobbyReady() {
		return lobby_ready;
	}

	public void setLobbyReady(boolean lobby_ready) {
		this.lobby_ready = lobby_ready;
	}

	public boolean isConnected() {
		return connection != null && connection.isAlive();
	}
	
	public ClientConnection getConnection() {
		return connection;
	}

	public void setConnection(final ClientConnection connection) {
		this.connection = connection;
		this.connection.attachToPlayer(this);
	}

	/**
	 * @return the eventListener
	 */
	public PlayerEventListener getEventListener() {
		return eventListener;
	}

	/**
	 * @param eventListener the eventListener to set
	 */
	public void setEventListener(PlayerEventListener eventListener) {
		this.eventListener = eventListener;
	}

}
