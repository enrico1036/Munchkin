package game;

import utils.Debug;
import utils.StateMachine;

public class GameManager extends StateMachine{

	public GameManager() {
		super();
		states = new String[3];
		states[0] = "Begin";
		states[1] = "Turn";
		states[2] = "End";
	}
	
	
	private void begin() {
		Debug.print("GameManager: Begin");
	}
	
	private void turn() {
		Turn turn = new Turn();
		while(turn.performStep());
	}
	
	private void end() {
		Debug.print("GameManager: End");
	}
	
	//TODO: look for a method to make static performStep()
	@Override
	public boolean performStep() {
		switch(states[currentState]) {
		case "Begin":
			begin();
			break;
		case "Turn":
			turn();
			break;
		case "End":
			end();
			break;
		}
		
		return super.performStep();
	}
}
