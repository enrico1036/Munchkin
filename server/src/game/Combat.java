package game;

import cards.Monster;
import network.message.Message;
import network.message.server.PopUpMessage;
import utils.StateMachine;

public class Combat extends StateMachine {

	private Monster card;
	private boolean playerWon;
	private Player helperPlayer; // bind with the one that accept to help the player, if someone do it
	private int promisedTreasure; // must be set to 0 if no one helps the player
	private int playerBonus; // positive number if bonus goes to player, negative if goes to monster

	public Combat(Monster card) {
		super();
		this.card = card;
		playerWon = false;
		promisedTreasure = 0;
		playerBonus = 0;
		states = new String[2];
		states[0] = "Begin";
		states[1] = "AskForHelp";
		states[2] = "InterferInCombat";
		states[3] = "End";
	}

	private void begin() {
		// TODO: run effect and set initial values
	}

	private void askForHelp() {
		// start help request and communications between client and server
		GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("Do you want to ask for help? If yes, how many treasures do you want to gift? (N)", "No", "Yes", 0, card.getEarningTreasures(), 15000));
		// TODO: add waitfor popup answer (and message to return values)
		Message ret = GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.POPUP).getValue();
		// TODO: if yes, set promisedTreasure and:
		{
			for (Player player : GameManager.getPlayers()) { // ask to all except for the current player
				if (player.getUsername() != GameManager.getCurrentPlayer().getUsername())
					player.sendMessage(new PopUpMessage("Do you want to help " + GameManager.getCurrentPlayer().getUsername() + " for " + Integer.toString(promisedTreasure) + "? (N)", "No", "Yes", 10000));
			}
			// TODO: wait for popup answer, if yes manage it and set helperPlayer
		}
	}

	private void interferInCombat() {
		boolean allNo = true; // true if all players answer "No"
		// TODO: ask via client server interface if someone wants to interfere in combat and sets playerBonus
		do {
			allNo = true;
			for (Player player : GameManager.getPlayers()) { // ask to all except for the current player
				player.sendMessage(new PopUpMessage("Do you want to interfer in combat? (N)", "No", "Yes", 7000));
				// TODO: wait for popup answer
				// TODO: if yes
				{
					allNo = false;
					GameManager.getInQueue().waitForMessage(player.getUsername(), Message.CLT_CARD_SELECTED);
					// TODO: manage returned card (check if card.category is allowed)
				}
			}
		}while(!allNo);
	}

	private void end() {
		if (helperPlayer != null) {
			if (helperPlayer.getCombatLevel() + GameManager.getCurrentPlayer().getCombatLevel() + playerBonus > this.card.getLevel()) {
				playerWon = true;
			}
		} else {
			if (GameManager.getCurrentPlayer().getCombatLevel() + playerBonus > this.card.getLevel()) {
				playerWon = true;
				promisedTreasure = 0;
			}
		}

		// if player won against monster give him earned levels and divide treasures between him and helper
		if (playerWon) {
			GameManager.getCurrentPlayer().leveleUp(card.getEarningLevels());
			if (promisedTreasure <= card.getEarningTreasures()) {
				for (int i = 0; i < promisedTreasure; i++)
					helperPlayer.draw(Decks.getTreasureCard());
				for (int i = 0; i < card.getEarningLevels() - promisedTreasure; i++)
					GameManager.getCurrentPlayer().draw(Decks.getTreasureCard());
			}
		}
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
