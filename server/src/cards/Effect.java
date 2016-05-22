package cards;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.sun.org.apache.xpath.internal.axes.WalkingIterator;

import debug.Debug;
import game.Combat;
import game.Decks;
import game.GameManager;
import game.Player;
import javafx.util.Pair;
import network.message.Message;
import network.message.client.PopUpResultMessage;
import network.message.client.SelectedCardMessage;
import network.message.server.PlayCardMessage;
import network.message.server.PlayCardMessage.Action;
import network.message.server.PopUpMessage;
import utils.StateMachine;

public class Effect {

	public static void runEffect(StateMachine gamePhase, Player owner, HashMap<String, HashMap<String, String>> effects) {
			for(String effectID : effects.keySet()){
				HashMap<String, String> parameters = effects.get(effectID);
				
				Debug.log("Effect" + effectID + " on " + owner.getUsername());
			
				switch (effectID) {
				// call right method with gamePhase and needed parameters
				case "loseLevel":
					loseLevel(Integer.parseInt((parameters.get("numberLevel"))));
					break;
				case "drawDoor":
					drawDoor(Integer.parseInt(parameters.get("number")), Boolean.getBoolean(parameters.get("show")));
					break;
				case "drawTreasure":
					drawTreasure(Integer.parseInt(parameters.get("number")), Boolean.getBoolean(parameters.get("show")));
					break;
				case "combatBonus":
					combatBonus((Combat) gamePhase, owner, Integer.parseInt(parameters.get("playerBonus")), Integer.parseInt(parameters.get("extraTreasure")), Boolean.getBoolean(parameters.get("choosable")));
					break;
				case "dropObject":
					dropObject(EquipSlot.parse(parameters.get("slot")));
					break;
				case "dropRace":
					dropRace();
					break;
				case "dropClass":
					dropClass();
					break;
				case "bonusEscape":
					bonusEscape(owner, Integer.parseInt(parameters.get("bonus")));
					break;
				case "bonusToRace":
					bonusToRace((Combat) gamePhase, parameters.get("raceName"), Integer.parseInt(parameters.get("bonus")));
					break;
				case "bonusToClass":
					bonusToClass((Combat) gamePhase, parameters.get("className"), Integer.parseInt(parameters.get("bonus")));
					break;
				case "dead":
					dead();
					break;
				case "dropCard":
					dropCard(Category.valueOf(parameters.get("cardCategory")), Integer.parseInt(parameters.get("number")), Boolean.parseBoolean(parameters.get("onlyHand")));
					break;
				case "dropAllHand":
					dropAllHand();
					break;
				case "escapeForRace":
					escapeForRace((Combat) gamePhase, parameters.get("raceName"), Integer.parseInt(parameters.get("levels")), Integer.parseInt(parameters.get("treasures")));
					break;
				case "escapeForClass":
					escapeForClass((Combat) gamePhase, parameters.get("className"), Integer.parseInt(parameters.get("levels")), Integer.parseInt(parameters.get("treasures")));
					break;
				case "escapeForObject":
					escapeForObject((Combat) gamePhase, EquipSlot.parse(parameters.get("slot")), parameters.get("cardName"), Integer.parseInt(parameters.get("levels")), Integer.parseInt(parameters.get("treasures")));
					break;
				case "mostroOcchiutoBadThings":
					mostroOcchiutoBadThings((Combat) gamePhase);
					break;
				case "updateLevelAll":
					updateLevelAll();
					break;
				case "cloneMonster":
					cloneMonster((Combat) gamePhase);
					break;
				}
		}
	}

	/**
	 * Remove the specified equipment from the current player
	 * 
	 * @param slot:
	 *            equipment to be removed
	 */
	private static void dropObject(EquipSlot slot) {
		GameManager.getCurrentPlayer().removeEquipment(slot);
	}

	/**
	 * Remove the race from the current player
	 */
	private static void dropRace() {
		GameManager.getCurrentPlayer().setRace(null);
	}

	/**
	 * Remove the class from the current player
	 */
	private static void dropClass() {
		GameManager.getCurrentPlayer().setClass(null);
	}

	/**
	 * Set bonuses in combat phase
	 * 
	 * @param gamePhase:
	 *            current combat phase
	 * @param owner
	 * @param playerBonus:
	 *            bonus in favor of the player (if against, this should be negative). If choosable leave it positive
	 * @param extra_treasure:
	 *            set to 0 if no extra treasure are present
	 * @param choosable:
	 *            describe if the player can choose between apply the bonus to the monster or to the player
	 */
	private static void combatBonus(Combat gamePhase, Player owner, int playerBonus, int extra_treasure, boolean choosable) {
		if (choosable) {
			owner.sendMessage(new PopUpMessage("Who you want to give the bonus to? (Monster)", "Monster", "Player", 10000));
			PopUpResultMessage result = (PopUpResultMessage) owner.msgQueue().waitFor(Message.CLT_POPUP_RESULT);
			if (result.isButton1Pressed() || result.isTimedOut()) { // default monster
				gamePhase.addPlayerCombatBonus(-playerBonus);
				gamePhase.addPlayerTreasureBonus(extra_treasure);
			} else {
				gamePhase.addPlayerCombatBonus(playerBonus);
				gamePhase.addPlayerTreasureBonus(extra_treasure);
			}
		} else {
			gamePhase.addPlayerCombatBonus(playerBonus);
			gamePhase.addPlayerTreasureBonus(extra_treasure);
		}
	}

	// clone()

	/**
	 * Decrease current player level by number_level
	 * 
	 * @param number_level
	 */
	private static void loseLevel(int number_level) {
		GameManager.getCurrentPlayer().leveleUp(-number_level);
	}

	/**
	 * Give to current player a door card
	 * 
	 * @param number:
	 *            number of cards to be given
	 * @param show:
	 *            if true draw, show in table for 2 seconds and put it on current player's hand
	 */
	private static void drawDoor(int number, boolean show) {
		for (int i = 0; i < number; i++) {
			Card card = Decks.getDoorCard();
			if (show) {
				GameManager.broadcastMessage(new PlayCardMessage(card, Action.SHOW));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				GameManager.getCurrentPlayer().draw(card);
			}
		}
	}

	/**
	 * Give to current player a treasure card
	 * 
	 * @param number:
	 *            number of cards to be given
	 * @param show:
	 *            if true draw, show in table for 2 seconds and put it on current player's hand
	 */
	private static void drawTreasure(int number, boolean show) {
		for (int i = 0; i < number; i++) {
			Card card = Decks.getTreasureCard();
			if (show) {
				GameManager.broadcastMessage(new PlayCardMessage(card, Action.SHOW));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				GameManager.getCurrentPlayer().draw(card);
			}
		}
	}

	/**
	 * Increase owner's escpaeTreshold by bonus
	 * 
	 * @param owner
	 * @param bonus
	 */
	private static void bonusEscape(Player owner, int bonus) {
		owner.setEscapeTreshold(owner.getEscapeTreshold() + bonus);
	}

	/**
	 * Increase player combat bonus if the current player has the specified race
	 * 
	 * @param gamePhase
	 * @param raceName
	 * @param bonus
	 */
	private static void bonusToRace(Combat gamePhase, String raceName, int bonus) {
		if (GameManager.getCurrentPlayer().getRace().getTitle().equals(raceName)) {
			gamePhase.addPlayerCombatBonus(bonus);
		}
	}

	/**
	 * Increase player combat bonus if the current player has the specified class
	 * 
	 * @param gamePhase
	 * @param className
	 * @param bonus
	 */
	private static void bonusToClass(Combat gamePhase, String className, int bonus) {
		if (GameManager.getCurrentPlayer().getCardClass().getTitle().equals(className)) {
			gamePhase.addPlayerCombatBonus(bonus);
		}
	}

	/**
	 * Cause current player's death
	 */
	private static void dead() {
		GameManager.getCurrentPlayer().die();
	}

	/**
	 * drop <code>number</code> cards, selected by player, of the specified category
	 * 
	 * @param cardCategory
	 *            category
	 * @param number
	 *            number of cards to discard
	 * @param onlyHand
	 *            true if the player can only choose from hand cards
	 */
	private static void dropCard(Category cardCategory, int number, boolean onlyHand) {
		Card selectedCard = null;
		while (number > 0) {
			if (onlyHand) {
				do {
					GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("Please choose a card in your hand to discard", "OK", 7000));
					GameManager.getCurrentPlayer().msgQueue().waitFor(Message.CLT_POPUP_RESULT);
					SelectedCardMessage selectedCardMsg = (SelectedCardMessage) GameManager.getCurrentPlayer().msgQueue().waitFor(Message.CLT_CARD_SELECTED);
					selectedCard = GameManager.getCurrentPlayer().getHandCard(selectedCardMsg.getCardName());
				} while ((cardCategory == Category.Any ? false : selectedCard.getCategory() != cardCategory) && selectedCard != null);
			} else {
				do {
					GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("Please choose a card to discard", "OK", 7000));
					GameManager.getCurrentPlayer().msgQueue().waitFor(Message.CLT_POPUP_RESULT);
					SelectedCardMessage selectedCardMsg = (SelectedCardMessage) GameManager.getCurrentPlayer().msgQueue().waitFor(Message.CLT_CARD_SELECTED);
					selectedCard = GameManager.getCurrentPlayer().getCardByName(selectedCardMsg.getCardName());
				} while ((cardCategory == Category.Any ? false : selectedCard.getCategory() != cardCategory) && selectedCard != null);
			}
			GameManager.getCurrentPlayer().removeCardByName(selectedCard.getTitle());
			Decks.discardCard(selectedCard);
			number--;
		}
	}

	/**
	 * Discard all the cards from current player's hand
	 */
	private static void dropAllHand() {
		for (Card card : GameManager.getCurrentPlayer().getHand()) {
			Decks.discardCard(GameManager.getCurrentPlayer().pickCard(card.getTitle()));
		}
	}

	/** mostroOcchiutoBadThings{cyborg fuggono, altri lose 2 level}
	*/
	private static void mostroOcchiutoBadThings(Combat gamePhase) {
		if(!GameManager.getCurrentPlayer().getRace().getTitle().equals("cyborg")) {
			loseLevel(2);
		}
	}

	/**
	 *  upLevelAll() per il grande cthulu, tutti guadagnano un livello tranne il giocatore che muore
	 */
	private static void updateLevelAll() {
		for (Player player : GameManager.getPlayers()) {
			if(!player.getUsername().equals(GameManager.getCurrentPlayer().getUsername())) {
				player.leveleUp(1);
			} else {
				dead();
			}
		}
	}

	// uplevel(class) me sa che non serve perche c'è bonusToClass e quindi basta settarlo negativo 

	// uplevel(race) vedi uplevel(class)

	/**
	 * Automatically escapes if the player has the specified race
	 * @param gamePhase
	 * @param raceName
	 * @param levels
	 * @param treasures
	 */
	private static void escapeForRace(Combat gamePhase, String raceName, int levels, int treasures) {
		if(GameManager.getCurrentPlayer().getRace().getTitle().equals(raceName)) {
			gamePhase.escape(levels, treasures);
		}
	}
	
	/**
	 * Automatically escapes if the player has the specified class
	 * @param gamePhase
	 * @param className
	 * @param levels
	 * @param treasures
	 */
	private static void escapeForClass(Combat gamePhase, String className, int levels, int treasures) {
		if(GameManager.getCurrentPlayer().getCardClass().getTitle().equals(className)) {
			gamePhase.escape(levels, treasures);
		}
	}
	
	/**
	 * Automatically escapes if the player has the specified equipped object
	 * @param gamePhase
	 * @param slot
	 * @param levels
	 * @param treasures
	 */
	private static void escapeForObject(Combat gamePhase, EquipSlot slot, String cardName, int levels, int treasures) {
		//TODO: look for the right object (slot and name) and escape if present
		if(GameManager.getCurrentPlayer().getEquipment(slot).getTitle().equals(cardName))
			gamePhase.escape(levels, treasures);
	}
	

	// EìscapeAutomatic(treasure,levelEarned) // per le carte tesoro

	// FailEscape

	// addHand
	
	/**
	 * Clone the monster in combat
	 * @param gamePhase
	 */
	private static void cloneMonster(Combat gamePhase){
		gamePhase.cloneMonster();
	}
}
