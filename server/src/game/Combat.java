package game;

import cards.Monster;
import utils.StateMachine;

public class Combat extends StateMachine {
	
	private Monster card;
	private boolean playerWon;
	private Player helperPlayer;	//bind with the one that accept to help the player, if someone do it
	private int promisedTreasure;	//must be set to 0 if no one helps the player
	private int playerBonus;		//positive number if bonus goes to player, negative if goes to monster

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
		//TODO: start help request and communications between client and server
		//TODO: set promisedTreasure
	}
	
	private void interferInCombat() {
		//TODO: ask via client server interface if someone wants to interfere in combat and sets playerBonus
	}
	
	private void end() {
		if(helperPlayer != null) {
			if(helperPlayer.getCombatLevel() + GameManager.getCurrentPlayer().getCombatLevel() + playerBonus > this.card.getLevel()) {
				playerWon = true;				
			}
		}else{
			if(GameManager.getCurrentPlayer().getCombatLevel() + playerBonus > this.card.getLevel()) {
				playerWon = true;
				promisedTreasure = 0;
			}
		}
		
		//if player won against monster give him earned levels and divide treasures between him and helper 
		if(playerWon) {
			GameManager.getCurrentPlayer().leveleUp(card.getEarningLevels());
			if(promisedTreasure <= card.getEarningTreasures()) {
				for (int i = 0; i < promisedTreasure; i++)
					helperPlayer.draw(Decks.getTreasureCard());
				for (int i = 0; i < card.getEarningLevels()-promisedTreasure; i++)
					GameManager.getCurrentPlayer().draw(Decks.getTreasureCard());
			}
		}
	}
	
	@Override
	public boolean performStep() {
		switch(states[currentState]) {
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
