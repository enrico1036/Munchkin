package game;

import cards.Card;
import cards.CardType;
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
			Decks.discardCard(card);
			currentState = this.states.length-1; // skip to the end state
			break;
		default:
			GameManager.giveCardToPlayer(card, GameManager.getCurrentPlayer());
			break;
		}
	}
	
	private void lookForTrouble() {
		boolean monsters = false; //true if hand contains a monster, false otherwise
		for (Card card : GameManager.getCurrentPlayer().getHand()) {
			if(card.getCategory() == Category.Monster) {
				monsters = true;
				break; //TODO: ask Kappa if correct
			}
		}
		if(monsters) {
			//TODO: if player choose a monster from the hand perform all the lookForTrouble steps, if player click on door deck call stepOver() 
		}

	}
	
	private void lootTheRoom() {
		//just draw a card and add it to the current player's hand
		GameManager.giveCardToPlayer(Decks.getDoorCard(), GameManager.getCurrentPlayer());
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
