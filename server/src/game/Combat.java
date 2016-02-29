package game;

import cards.Monster;
import utils.StateMachine;

public class Combat extends StateMachine {
	
	private Monster card;

	public Combat(Monster card) {
		super();
		this.card = card;
		states = new String[2];
		states[0] = "Begin";
		states[1] = "AskForHelp";
		states[2] = "InterferInCombat";
	}
	
	private void begin() {
		card.effect();
	}
	
	private void askForHelp() {
		//start help request and communications between client and server
	}
	
	private void interferInCombat() {
		//ask via client server interface if someone wants to interfer in combat
	}
	
	@Override
	public boolean performStep() {
		// TODO Insert your code here
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
		}
		
		return super.performStep();
	}

}
