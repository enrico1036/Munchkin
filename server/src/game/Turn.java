package game;

import utils.StateMachine;

public class Turn extends StateMachine {

	public Turn() {
		super();
		states = new String[4];
		states[0] = "Draw";
		states[1] = "Combat";
		states[2] = "Trading";
		states[3] = "Charity";
	}
	
	@Override
	public boolean performStep() {
		// TODO Insert your code here
		
		return super.performStep();
	}

}
