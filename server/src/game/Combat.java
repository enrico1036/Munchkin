package game;

import utils.StateMachine;

public class Combat extends StateMachine {

	public Combat() {
		super();
		states = new String[2];
		states[0] = "AskForHelp";
		states[1] = "InterferInCombat";
	}
	
	@Override
	public boolean performStep() {
		// TODO Insert your code here
		
		return super.performStep();
	}

}
