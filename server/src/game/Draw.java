package game;

import utils.StateMachine;

public class Draw extends StateMachine {

	public Draw() {
		super();
		states = new String[3];
		states[0] = "OpenDoor";
		states[1] = "LookForTrouble";
		states[2] = "LootTheRoom";
	}

	@Override
	public boolean performStep() {
		// TODO Insert your code here
		
		return super.performStep();
	}
}
