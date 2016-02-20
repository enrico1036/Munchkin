package utils;

import javafx.util.Pair;

public abstract class StateMachine {
	public int currentState;
	protected String[] states;
	
	public StateMachine() {
		currentState = 0;
	}
	
	public boolean stepOver() {
		if(this.states.length > ++this.currentState) {
			return true;
		}
		this.currentState = 0;
		return false;
	}
	
	public Pair<Integer, String> getState() {
		return new Pair<Integer, String>(currentState, states[currentState]);
	}
	
	public boolean performStep(){
		return this.stepOver();
	}
}
