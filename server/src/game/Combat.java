package game;

import utils.StateMachine;

public class Combat extends StateMachine {

	public Combat() {
		super();
		states = new String[2];
		states[0] = "AskForHelp";
		states[1] = "InterferInCombat";
	}
	
	private void askForHelp() {
		
	}
	
	private void interferInCombat() {
		
	}
	
	@Override
	public boolean performStep() {
		// TODO Insert your code here
		switch(states[currentState]) {
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
