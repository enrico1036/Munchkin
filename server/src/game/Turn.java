package game;

import cards.Card;
import cards.Category;
import cards.Equipment;
import network.message.Message;
import network.message.client.PopUpResultMessage;
import network.message.client.SelectedCardMessage;
import network.message.server.PopUpMessage;
import utils.StateMachine;

public class Turn extends StateMachine {

	public Turn() {
		super();
		states = new String[4];
		states[0] = "Equip";
		states[1] = "Draw";
		states[2] = "Trading";
		states[3] = "Charity";
	}

	private void equip() {
		// wait for an equip action. If draw action detected skip this phase
		SelectedCardMessage message = (SelectedCardMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_CARD_SELECTED).getValue();
		while (!message.getCardName().equals(SelectedCardMessage.DOOR_DECK)) {
			Card card = GameManager.getCurrentPlayer().getHandCard(message.getCardName());
			if (card != null && card.getCategory() == Category.Equipment) {
				if (GameManager.getCurrentPlayer().equip(((Equipment) card).getSlot(), (Equipment) card)) {
					GameManager.getCurrentPlayer().discardCard(card);
				} else {
					GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("You cannot equip this card", "OK", 3000));
				}
			}
			message = (SelectedCardMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_CARD_SELECTED).getValue();
		}
	}

	private void draw() {
		Draw draw = new Draw();
		while (draw.performStep());
	}

	private void trading() {
		int equipments = 0; // counts the number of equipment cards present in hand
		for (Card card : GameManager.getCurrentPlayer().getHand()) {
			if (card.getCategory() == Category.Equipment)
				equipments++;
		}
		if (equipments > 0) {
			// ask to player what to sell if nothing skip
			GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("How many cards do you want to sell? (0)", "OK", 0, equipments, 10000));
			PopUpResultMessage answer = (PopUpResultMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_POPUP_RESULT).getValue();
			int value = 0;
			Card card = null;
			for (int i = 0; i < answer.getValue(); i++) {
				SelectedCardMessage selCard = (SelectedCardMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_CARD_SELECTED).getValue();
				card = GameManager.getCurrentPlayer().getHandCard(selCard.getCardName());
				if (card.getCategory() == Category.Equipment) {
					GameManager.getCurrentPlayer().discardCard(card);
					Decks.discardCard(card);

					value += ((Equipment) card).getValue();
				} else {
					i--;
				}
			}
			GameManager.getCurrentPlayer().leveleUp(value % 1000);
		}
	}

	private void charity() {
		while (!GameManager.getCurrentPlayer().cardCheck()) {
			// Tell current player to discard a card
			GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("Choose a card to discard!", "OK", 3000));
			// Wait for a card to be received
			SelectedCardMessage received = (SelectedCardMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_CARD_SELECTED).getValue();

			// Find the player(s) with the lowest level
			int lowestPlayerNum = 0;
			Player lowestPlayer = GameManager.getPlayers().get(0);

			// Search minumum
			for (Player player : GameManager.getPlayers()) {
				if (player.getLevel() < lowestPlayer.getLevel()) {
					lowestPlayer = player;
					lowestPlayerNum = 1;
				} else if (player.getLevel() == lowestPlayer.getLevel())
					++lowestPlayerNum;
			}

			// In case the player with the lowest level is current player or there are more than one
			// put the card into the appropriate garbage stack
			Card card = GameManager.getCurrentPlayer().pickCard(received.getCardName());
			if (lowestPlayer.getUsername().equals(GameManager.getCurrentPlayer().getUsername()) || lowestPlayerNum > 1) {
				Decks.discardCard(card);
			} else {
				// Otherwise add it to the lowest level player's hand
				lowestPlayer.draw(card);
			}

		}
	}

	@Override
	public boolean performStep() {
		switch (this.states[currentState]) {
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
