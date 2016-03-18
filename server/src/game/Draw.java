package game;

import cards.Card;
import cards.CardType;
import cards.Category;
import cards.Monster;
import network.message.Message;
import network.message.client.SelectedCardMessage;
import network.message.server.PlayCardMessage;
import network.message.server.PopUpMessage;
import network.message.server.PlayCardMessage.Action;
import utils.StateMachine;

public class Draw extends StateMachine {

	public Draw() {
		super();
		states = new String[4];
		states[0] = "OpenDoor";
		states[1] = "LookForTrouble";
		states[2] = "LootTheRoom";
		states[3] = "End";
	}

	private void openDoor() {
		if(!GameManager.getCurrentPlayer().isAlive()) {
			for (int i = 0; i < 2; i++) {
				GameManager.getCurrentPlayer().draw(Decks.getDoorCard());
				GameManager.getCurrentPlayer().draw(Decks.getTreasureCard());
			}
		}
		Card card = Decks.getDoorCard();
		GameManager.broadcastMessage(new PlayCardMessage(card, Action.SHOW));
		// if curse play it, if monster start a combat and execute it. In all other cases draw it
		switch (card.getCategory()) {
		case Curse:
			// TODO: card effect
			break;
		case Monster:
			Combat combat = new Combat((Monster) card);
			while (combat.performStep());
			
			Decks.discardCard(card);
			currentState = this.states.length - 1; // skip to the end state
			break;
		default:
			GameManager.getCurrentPlayer().draw(card);

			break;
		}
	}

	private void lookForTrouble() {
		boolean monsters = false; // true if hand contains a monster, false otherwise
		for (Card card : GameManager.getCurrentPlayer().getHand()) {
			if (card.getCategory() == Category.Monster) {
				monsters = true;
				break;
			}
		}
		if (monsters) {
			// if player choose a monster from the hand perform all the lookForTrouble steps, if player click on door deck call stepOver()
			boolean monsterSelected = false;
			do {
			SelectedCardMessage message = (SelectedCardMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_CARD_SELECTED).getValue();
			if (!message.getCardName().equals(SelectedCardMessage.DOOR_DECK)) {
				Card card = GameManager.getCurrentPlayer().getHandCard(message.getCardName());
				if (card != null && card.getCategory() == Category.Monster) {
					monsterSelected = true;
					GameManager.getCurrentPlayer().discardCard(card);
					GameManager.broadcastMessage(new PlayCardMessage(card, Action.SHOW));
					Combat combat = new Combat((Monster) card);
					while (combat.performStep());
					Decks.discardCard(card);
					currentState = this.states.length - 1; // skip to the end state
				}
			} else {
				stepOver();
				return;
			}
			}while(!monsterSelected);
		}

	}

	private void lootTheRoom() {
		// just draw a card and add it to the current player's hand
		GameManager.getCurrentPlayer().draw(Decks.getDoorCard());
	}

	@Override
	public boolean performStep() {
		switch (states[currentState]) {
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
