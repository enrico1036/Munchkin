package game;

import network.message.Message;
import network.message.client.SelectedCardMessage;
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
		// wait for an equip action. If draw action detected  call stepOver() and skip this phase
		SelectedCardMessage message = (SelectedCardMessage)GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_CARD_SELECTED).getValue();
		if(message.getCardName() != SelectedCardMessage.DOOR_DECK) {
			//TODO: do equipment check and action
		} else {
			stepOver();
		}
			

	}
	
	private void draw() {
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
