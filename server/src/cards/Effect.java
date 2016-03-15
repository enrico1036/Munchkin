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

	public static void runEffect(StateMachine gamePhase, Card card) {
		String effectID;
		HashMap<String, String> parameters;
		for (Pair<String, HashMap<String, String>> effect : card.effects) {
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
			case "combatBonus":
				combatBonus((Combat) gamePhase, card.owner, Integer.parseInt(parameters.get("playerBonus")), Integer.parseInt(parameters.get("extra_treasure")), Boolean.getBoolean(parameters.get("chooseable")));
				break;
			}
		}
	}

	// ???? non so se serve
	// runEffect() {
	// switch(effectString){
	// call right method with needed parameters
	// }
	// }

	// dropobject(tipeObject)

	// dropRace()
	// dropClass()

	private static void combatBonus(Combat gamePhase, Player owner, int playerBonus, int extra_treasure, boolean chooseable) {
		if (chooseable) {
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

	private static void loseLevel(int number_level) {
		GameManager.getCurrentPlayer().leveleUp(-number_level);
	}

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
	// drawTreasure(int number, boolean show)

	// bonusEscape(int bonus)

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
