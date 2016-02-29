package game;

import java.util.Timer;

import utils.Debug;
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
		
	}
	
	private void draw() {
		Draw draw = new Draw();
		while(draw.performStep());
	}
	
	private void trading() {
		
	}
	
	private void charity() {
		
	}
	
	@Override
	public boolean performStep() {
		// TODO Insert your code here
		switch(states[currentState]) {
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
