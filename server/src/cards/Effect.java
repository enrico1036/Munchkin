package cards;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.sun.org.apache.xpath.internal.axes.WalkingIterator;

import game.Combat;
import game.Decks;
import game.GameManager;
import game.Player;
import javafx.util.Pair;
import network.message.Message;
import network.message.client.PopUpResultMessage;
import network.message.server.PlayCardMessage;
import network.message.server.PlayCardMessage.Action;
import network.message.server.PopUpMessage;
import utils.StateMachine;

public class Effect {

	public static void runEffect(StateMachine gamePhase, Player owner, ArrayList<Pair<String, HashMap<String, String>>> effects) {
		String effectID;
		HashMap<String, String> parameters;
		for (Pair<String, HashMap<String, String>> effect : effects) {
			effectID = effect.getKey();
			parameters = effect.getValue();
			switch (effectID) {
			// call right method with gamePhase and needed parameters
			case "loseLevel":
				loseLevel(Integer.parseInt((parameters.get("number_level"))));
				break;
			case "drawDoor":
				drawDoor(Integer.parseInt(parameters.get("number")), Boolean.getBoolean(parameters.get("show")));
				break;
			case "drawTreasure":
				drawTreasure(Integer.parseInt(parameters.get("number")), Boolean.getBoolean(parameters.get("show")));
				break;
			case "combatBonus":
				combatBonus((Combat) gamePhase, owner, Integer.parseInt(parameters.get("playerBonus")), Integer.parseInt(parameters.get("extra_treasure")), Boolean.getBoolean(parameters.get("choosable")));
				break;
			case "dropObject":
				dropObject(EquipSlot.parse(parameters.get("slot")));
				break;
			case "bonusEscape":
				bonusEscape(owner, Integer.parseInt(parameters.get("bonus")));
			}
		}
	}

	// ???? non so se serve
	// runEffect() {
	// switch(effectString){
	// call right method with needed parameters
	// }
	// }

	/**
	 * Remove the specified equipment from the current player
	 * @param slot: equipment to be removed
	 */
	private static void dropObject(EquipSlot slot) {
		GameManager.getCurrentPlayer().removeEquipment(slot);
	}

	// dropRace()
	// dropClass()

	/**
	 * Set bonuses in combat phase 
	 * @param gamePhase: current combat phase
	 * @param owner
	 * @param playerBonus: bonus in favor of the player (if against, this should be negative). If choosable leave it positive
	 * @param extra_treasure: set to 0 if no extra treasure are present
	 * @param choosable: describe if the player can choose between apply the bonus to the monster or to the player 
	 */
	private static void combatBonus(Combat gamePhase, Player owner, int playerBonus, int extra_treasure, boolean choosable) {
		if (choosable) {
			owner.sendMessage(new PopUpMessage("Who you want to give the bonus to? (Monster)", "Monster", "Player", 10000));
			PopUpResultMessage result = (PopUpResultMessage) GameManager.getInQueue().waitForMessage(owner.toString(), Message.CLT_POPUP_RESULT).getValue();
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
	 * @param number_level
	 */
	private static void loseLevel(int number_level) {
		GameManager.getCurrentPlayer().leveleUp(-number_level);
	}

	/**
	 * Give to current player a door card
	 * @param number: number of cards to be given
	 * @param show: if true draw, show in table for 2 seconds and put it on current player's hand
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
	 * @param number: number of cards to be given
	 * @param show: if true draw, show in table for 2 seconds and put it on current player's hand
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
	 * @param owner
	 * @param bonus
	 */
	private static void bonusEscape(Player owner, int bonus) {
		owner.setEscapeTreshold(owner.getEscapeTreshold() + bonus);
	}

	// bonus/malusRace

	// bonus/malusClass

	// dead

	// dropCard(tipe card, number,boolean onlyHand)

	// dropallhand

	// mostroOcchiutoBadThings{cyborg fuggono, altri lose 2 level}

	// upLevelAll() per il grande cthulu

	// uplevel(class)

	// uplevel(race)

	// grande-cthulhu_brutte cose(){uplevel per ogni giocatore che non combatte} me sa che non serve

	// EscapeAutomatic(race,treasure,levelEarned)
	// EscapeAutomatic(class,treasure,levelEarned)
	// EscapeAutomatic(object,treasure,levelEarned)
	// EscapeAutomatic(object,treasure,levelEarned)
	// EscapeAutomatic(treasure,levelEarned) // per le carte tesoro

	// FailEscape

	// tettonaBionica(calzatura) pure questo si potrebbe evitare visti gli escape automatic

	// dieWeighted(int resultsRoll)

	// addHand
}
