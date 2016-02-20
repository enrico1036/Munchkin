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

	
	private void openDoor() {
		
	}
	
	private void lookForTrouble() {
		
	}
	
	private void lootTheRoom() {
		
	}
	
	@Override
	public boolean performStep() {
		// TODO Insert your code here
		switch(states[currentState]) {
		case "OpenDoor":
			openDoor();
			break;
		case "LookForToruble":
			lookForTrouble();
			break;
		case "LootTheRoom":
			lootTheRoom();
			break;
		}
		return super.performStep();
	}
}
