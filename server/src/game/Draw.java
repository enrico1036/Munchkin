package game;

import cards.Card;
import cards.Category;
import cards.Monster;
import utils.StateMachine;

public class Draw extends StateMachine {

	public Draw() {
		super();
		states = new String[3];
		states[0] = "OpenDoor";
		states[1] = "LookForTrouble";
		states[2] = "LootTheRoom";
		states[3] = "End";
	}

	
	private void openDoor() {
		Card card = Decks.getDoorCard();
		//if curse play it, if monster start a combat and execute it. In all other cases draw it
		switch(card.getCategory()) {
		case Curse:
			// TODO: Run effect
			break;
		case Monster:
			Combat combat = new Combat((Monster) card);
			while(combat.performStep());
			currentState = this.states.length-1; // skip to the end state
			break;
		default:
			GameManager.giveCardToPlayer(card, GameManager.getCurrentPlayer());
			break;
			
		}
	}
	
	private void lookForTrouble() {
		
	}
	
	private void lootTheRoom() {
		
	}
	
	@Override
	public boolean performStep() {
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
		case "End":
			break;
		}
		return super.performStep();
	}
}
