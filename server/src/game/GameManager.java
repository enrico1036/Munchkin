package game;

import utils.StateMachine;

public class GameManager extends StateMachine{

	public GameManager() {
		super();
		states = new String[3];
		states[0] = "Begin";
		states[1] = "Turn";
		states[2] = "End";
	}
	
	
	private static void begin() {
		
	}
	
	private static void turn() {
		
	}
	
	private static void end() {
		
	}
	
	//TODO: look for a method to make static performStep()
	@Override
	public void performStep() {
		
		//TODO: Insert your code here
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
		super.performStep();
	}
}
