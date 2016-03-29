package utils;

import game.GameManager;
import javafx.util.Pair;
import network.message.server.StateUpdateMessage;

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
	
	public Pair<Integer, String> getCurrentState() {
		return new Pair<Integer, String>(currentState, states[currentState]);
	}
	
	public boolean performStep(){
		GameManager.broadcastMessage(new StateUpdateMessage(GameManager.getCurrentPlayer().getUsername(), states[currentState]));
		return this.stepOver();
	}
}
