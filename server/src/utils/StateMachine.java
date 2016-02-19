package utils;

import javafx.util.Pair;

public abstract class StateMachine {
	public static int currentState;
	protected static String[] states;
	
	public StateMachine() {
		currentState = 0;
	}
	
	public static boolean stepOver() {
		if(states.length < currentState) {
			currentState++;
			return true;
		}
		return false;
	}
	
	public static Pair<Integer, String> getState() {
		return new Pair<Integer, String>(currentState, states[currentState]);
	}
	
	public void performStep() {
		stepOver();
	}
}
