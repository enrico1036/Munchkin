package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cards.Card;
import cards.ClassCard;
import cards.EquipSlot;
import cards.Equipment;
import cards.Race;
import network.ClientConnection;
import network.NewMessageQueue;
import network.message.Message;
import network.message.server.PlayCardMessage;
import network.message.server.PlayerFullStatsMessage;
import network.message.server.PlayCardMessage.Action;
import sun.text.normalizer.CharTrie.FriendAgent;
import network.message.server.PlayerEquipmentMessage;
import utils.PlayerEventListener;

public class Player {
	private ClientConnection connection;
	private PlayerEventListener eventListener;
	private String username;
	private int level;
	private boolean lobby_ready;
	private Race race;
	// private int raceAllowed;
	private ClassCard playerClass;
	// private int classAllowed;
	private ArrayList<Card> hand;
	private HashMap<EquipSlot, Equipment> equipments;
	private boolean firstHandOld;
	private boolean alive;
	private int escapeTreshold; // you can escape from monster only if rolling a die the result is higher or equal
	private final int handSize = 5;
	private final NewMessageQueue queue;

	// Anonymous Player constructor used into ConnectionListener
	public Player(PlayerEventListener listener) {
		this.level = 1;
		this.hand = new ArrayList<Card>();
		this.race = new Race("Human");
		this.alive = false;
		this.connection = null;
		this.lobby_ready = false;
		this.escapeTreshold = 5;
		this.eventListener = listener;
		equipments = new HashMap<>();
		this.firstHandOld = true;
		queue = new NewMessageQueue();
	}

	// Default Player constructor
	public Player(String username, PlayerEventListener listener) {
		this(listener);
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void die() {
		for (Card card : hand) {
			sendMessage(new PlayCardMessage(card, Action.REMOVE));
		}
		this.hand.clear();
		this.equipments.clear();
		this.alive = false;
		GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive() {
		this.alive = true;
	}

	public void leveleUp(int x) {
		level += x;
		if(level < 1) {
			level = 1;
		}
		GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
	}

	public int getLevel() {
		return level;
	}

	public int getCombatLevel() {
		int combatLevel = 0;
		combatLevel += level;
		// combatLevel += head.getBonus();
		// combatLevel += body.getBonus();
		// combatLevel += hand1.getBonus();
		// combatLevel += hand2.getBonus();
		// combatLevel += feet.getBonus();
//		for (Equipment equip : equipments.values()) {
//			combatLevel += equip.getBonus();
//		}
		for (Map.Entry<EquipSlot, Equipment> entry : equipments.entrySet())
		{
		    combatLevel += entry.getValue().getBonus();
		}
		return combatLevel;
	}

	public int getEscapeTreshold() {
		return escapeTreshold;
	}

	public void setEscapeTreshold(int treshold) {
		this.escapeTreshold = treshold;
	}

	public Race getRace() {
		return this.race;
	}

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
			if (card.getTitle().equals(title))
				return card;
		}
		return null;
	}

	public Card pickCard(String title) {
		for (Card c : hand) {
			if (c.getTitle().equals(title)) {
				hand.remove(c);
				sendMessage(new PlayCardMessage(c, Action.REMOVE));
				GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
				return c;
			}
		}
		return null;
	}

	public void draw(Card card) {
		setAlive();
		hand.add(card);
		card.setOwner(this);
		sendMessage(new PlayCardMessage(card, Action.DRAW));
		GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
	}

	public boolean discardCard(Card card) {
		boolean isCardRemoved = hand.remove(card);
		if (isCardRemoved) {
			sendMessage(new PlayCardMessage(card, Action.REMOVE));
			GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
		}
		return isCardRemoved;
	}

	public Card getCardByName(String cardName) {
		// first try to get that card from hand
		Card card = getHandCard(cardName);

		// then look for the card on table
		if (card == null) {
			if (cardName.equals(race.getTitle())) {
				card = race;
			} else if (cardName.equals(playerClass.getTitle())) {
				card = playerClass;
			} else {
				for (Equipment equip : equipments.values()) {
					if (equip.getTitle().equals(cardName)) {
						card = equip;
					}
				}
			}
			GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
		}
		return card;
	}
	
	public NewMessageQueue msgQueue(){
		return queue;
	}

	public Card removeCardByName(String cardName) {
		// first try to remove that card from hand
		Card card = pickCard(cardName);

		// then look for the card on table
		if (card == null) {
			if (cardName.equals(race.getTitle())) {
				card = race;
				race = null;
			} else if (cardName.equals(playerClass.getTitle())) {
				card = playerClass;
				playerClass = null;
			} else {
				for (Equipment equip : equipments.values()) {
					if (equip.getTitle().equals(cardName)) {
						card = equip;
						equip = null;
					}
				}
			}
			GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
		}
		return card;
	}

	public boolean equip(cards.EquipSlot slot, cards.Equipment card) {
		if(slot == EquipSlot.hand) {
			slot = firstHandOld ? EquipSlot.hand1 : EquipSlot.hand2;
			firstHandOld = !firstHandOld;
		}

		Decks.discardCard(equipments.put(slot, card));

		GameManager.broadcastMessage(new PlayerEquipmentMessage(this));
		GameManager.broadcastMessage(new PlayerFullStatsMessage(this));
		return true;
	}

	public Equipment getEquipment(EquipSlot slot) {
		return equipments.get(slot);
	}

	public Equipment removeEquipment(EquipSlot slot) {
		Equipment card = null;
		card = equipments.remove(slot);
		GameManager.broadcastMessage(new PlayerEquipmentMessage(this));
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
	 * @param eventListener
	 *            the eventListener to set
	 */
	public void setEventListener(PlayerEventListener eventListener) {
		this.eventListener = eventListener;
	}

	@Override
	public boolean equals(Object obj) {
		return username.equals(((Player) obj).getUsername());
	}

}
