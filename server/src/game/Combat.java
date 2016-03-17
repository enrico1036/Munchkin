package game;

import cards.Card;
import cards.Category;
import cards.Monster;
import network.message.Message;
import network.message.client.PopUpResultMessage;
import network.message.client.SelectedCardMessage;
import network.message.server.DiceResultMessage;
import network.message.server.PopUpMessage;
import utils.StateMachine;

public class Combat extends StateMachine {

	private Monster card;
	private boolean playerWon;
	private Player helperPlayer; // bind with the one that accept to help the player, if someone do it
	private int promisedTreasure; // must be set to 0 if no one helps the player
	private int playerCombatBonus; // positive number if bonus goes to player, negative if goes to monster
	private int playerTreasureBonus; // positive number if bonus goes to player, negative if goes to monster

	public Combat(Monster card) {
		super();
		this.card = card;
		playerWon = false;
		promisedTreasure = 0;
		helperPlayer = null;
		playerCombatBonus = 0;
		playerTreasureBonus = 0;
		states = new String[2];
		states[0] = "Begin";
		states[1] = "AskForHelp";
		states[2] = "InterferInCombat";
		states[3] = "End";
	}

	private void begin() {
		// TODO: card effect and set initial values
	}

	private void askForHelp() {
		// start help request and communications between client and server
		GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("Do you want to ask for help? If yes, how many treasures do you want to gift? (N)", "No", "Yes", 0, card.getEarningTreasures(), 15000));
		// waitfor popup answer (and message to return values)
		PopUpResultMessage ret = (PopUpResultMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_POPUP_RESULT).getValue();
		// if yes, set promisedTreasure and:
		if (ret.isButton2Pressed()) {
			for (Player player : GameManager.getPlayers()) { // ask to all except for the current player
				if (!player.getUsername().equals(GameManager.getCurrentPlayer().getUsername()))
					player.sendMessage(new PopUpMessage("Do you want to help " + GameManager.getCurrentPlayer().getUsername() + " for " + Integer.toString(promisedTreasure) + "? (N)", "No", "Yes", 10000));
			}

			for (int i = 0; i < GameManager.getPlayers().size() - 1; i++) {
				// wait for popup answer, if yes set helperPlayer
				ret = (PopUpResultMessage) GameManager.getInQueue().waitForMessage(Message.CLT_POPUP_RESULT).getValue();
				if (ret.isButton2Pressed()) {
					helperPlayer = GameManager.getPlayerByName(ret.getSender());
					break;
				}
			}
		}
	}

	private void interferInCombat() {
		boolean allNo = true; // true if all players answer "No"
		// ask via client server interface if someone wants to interfere in combat and sets playerBonus
		do {
			allNo = true;
			for (Player player : GameManager.getPlayers()) { // ask to all players
				player.sendMessage(new PopUpMessage("Do you want to interfer in combat? (N)", "No", "Yes", 7000));
				// wait for popup answer
				PopUpResultMessage popUpRet = (PopUpResultMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_POPUP_RESULT).getValue();
				// if yes
				if (popUpRet.isButton2Pressed()) {
					allNo = false;
					boolean cardSelected = false;
					do {
						SelectedCardMessage message = (SelectedCardMessage) GameManager.getInQueue().waitForMessage(player.getUsername(), Message.CLT_CARD_SELECTED).getValue();
						// manage returned card (check if card.category is allowed)
						Card card = player.getHandCard(message.getCardName());
						switch (card.getCategory()) {
						case Consumable:
							cardSelected = true;
							// TODO: card effect
							break;
						case Curse:
							cardSelected = true;
							// TODO: card effect
							break;
						default:
							cardSelected = false;
							break;
						}
					} while (!cardSelected);
				}
			}
		} while (!allNo);
	}

	private void end() {
		playerWon = false;
		if (helperPlayer != null) {	// helperPlayer present
			if (helperPlayer.getCombatLevel() + GameManager.getCurrentPlayer().getCombatLevel() + playerCombatBonus > card.getLevel()) {
				playerWon = true;
				GameManager.getCurrentPlayer().leveleUp(card.getEarningLevels());
				for (int i = 0; i < promisedTreasure; i++)
					helperPlayer.draw(Decks.getTreasureCard());
				for (int i = 0; i < playerTreasureBonus + card.getEarningTreasures() - promisedTreasure; i++)
					GameManager.getCurrentPlayer().draw(Decks.getTreasureCard());
			} else {	// player loose
				// roll die and check if escape
				int die = (int) Math.random()*5+1;
//				GameManager.getCurrentPlayer().sendMessage(new DiceResultMessage(die));
				GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("You made " + Integer.toString(die), 5000));
				if(die < GameManager.getCurrentPlayer().getEscapeTreshold()) {
					//run bad things
				}
				die = (int) Math.random()*5+1;
//				helperPlayer.sendMessage(new DiceResultMessage(die));
				helperPlayer.sendMessage(new PopUpMessage("You made " + Integer.toString(die), 5000));
				if(die < helperPlayer.getEscapeTreshold()) {
					//run bad things
				}
			}
		} else { // No one helped player
			if (GameManager.getCurrentPlayer().getCombatLevel() + playerCombatBonus > card.getLevel()) {
				playerWon = true;
				GameManager.getCurrentPlayer().leveleUp(card.getEarningLevels());
				for (int i = 0; i < playerTreasureBonus + card.getEarningTreasures(); i++)
					GameManager.getCurrentPlayer().draw(Decks.getTreasureCard());
			} else {	//player loose
				// roll die and check if escape
				int die = (int) Math.random()*5+1;
//				GameManager.getCurrentPlayer().sendMessage(new DiceResultMessage(die));
				GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("You made " + Integer.toString(die), 5000));
				if(die < GameManager.getCurrentPlayer().getEscapeTreshold()) {
					//run bad things
				}
			}
		}
	}
	
	public void addPlayerCombatBonus(int playerCombatBonus) {
		this.playerCombatBonus += playerCombatBonus;
	}
	
	public void addPlayerTreasureBonus(int playerTreasureBonus) {
		this.playerTreasureBonus += playerTreasureBonus;
	}
	
	public void escape(int levels, int treasures) {
		GameManager.getCurrentPlayer().leveleUp(levels);
		for (int i = 0; i < treasures; i++)
			GameManager.getCurrentPlayer().draw(Decks.getTreasureCard());
		while(stepOver());	// terminate state machine
	}

	@Override
	public boolean performStep() {
		switch (states[currentState]) {
		case "Begin":
			begin();
			break;
		case "AskForHelp":
			askForHelp();
			break;
		case "InterferInCombat":
			interferInCombat();
			break;
		case "End":
			end();
			break;
		}

		return super.performStep();
	}

}
