package game;

import utils.StateMachine;

public class Turn extends StateMachine {

	public Turn() {
		super();
		states = new String[5];
		states[0] = "Equip";
		states[1] = "Draw";
		states[2] = "Trading";
		states[3] = "Charity";
	}
	
	private void equip() {
		//TODO: wait for an equip action. If draw action detected  call stepOver() and skip this phase
	}
	
	private void draw() {
		//TODO: wait player click on door deck (DoorRequestMessage incoming)
		Draw draw = new Draw();
		while(draw.performStep());
	}
	
	private void trading() {
		//TODO: ask to player what to sell if nothing skip
	}
	
	private void charity() {
		//TODO: if hand card number higher than max card ask what card should be discarded, if lower skip
		if(!GameManager.getCurrentPlayer().cardCheck())
		{
			//TODO: ask to player what cards wants to discard, then give them to other players
		}
	}
	
	@Override
	public boolean performStep() {
		switch(this.states[currentState]) {
		case "Equip":
			equip();
			break;
		case "Draw":
			draw();
			break;
		case "Trading":
			trading();
			break;
		case "Charity":
			charity();
			break;
		}
		return super.performStep();
	}

}
