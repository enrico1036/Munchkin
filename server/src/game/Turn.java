package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cards.Card;
import cards.CardType;
import cards.Category;
import cards.Monster;
import cards.Equipment;
import network.message.Message;
import network.message.client.SelectedCardMessage;
import network.message.server.PlayCardMessage;
import network.message.server.PlayCardMessage.Action;
import network.message.server.PopUpMessage;
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
		boolean equipSelected = false;
		do {
		SelectedCardMessage message = (SelectedCardMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_CARD_SELECTED).getValue();
		if (message.getCardName() != SelectedCardMessage.DOOR_DECK) {
			Card card = GameManager.getCurrentPlayer().getHandCard(message.getCardName());
			if (card != null && card.getCategory() == Category.Equipment) {
				equipSelected = true;
				GameManager.getCurrentPlayer().equip(((Equipment) card).getSlot(), (Equipment)card);
				//TODO: do equipment check and action, set combatBonus in player
			}
		} else {
			return;
		}
		}while(!equipSelected);
		
	}
	
	private void draw() {
		Draw draw = new Draw();
		while(draw.performStep());
	}
	
	private void trading() {
		//TODO: ask to player what to sell if nothing skip
	}
	
	private void charity() {
		while(!GameManager.getCurrentPlayer().cardCheck())
		{
			// Tell current player to discard a card
			GameManager.getCurrentPlayer().sendMessage(new PopUpMessage("Choose a card to discard!", "OK", 3000));
			// Wait for a card to be received
			SelectedCardMessage received = (SelectedCardMessage) GameManager.getInQueue().waitForMessage(GameManager.getCurrentPlayer().getUsername(), Message.CLT_CARD_SELECTED).getValue();
			
			// Find the player(s) with the lowest level
			int lowestPlayerNum = 0;
			Player lowestPlayer = GameManager.getPlayers().get(0);
			
			// Search minumum 
			for(Player player : GameManager.getPlayers()){
				if(player.getLevel() < lowestPlayer.getLevel()){
					lowestPlayer = player;
					lowestPlayerNum = 1;
				} else if(player.getLevel() == lowestPlayer.getLevel())
					++lowestPlayerNum;
			}
			
			// In case the player with the lowest level is current player or there are more than one
			// put the card into the appropriate garbage stack
			if(lowestPlayer.equals(GameManager.getCurrentPlayer()) || lowestPlayerNum > 1){
				Decks.discardCard(GameManager.getCurrentPlayer().pickCard(received.getCardName()));
			} else {
				// Otherwise add it to the lowest level player's hand
				lowestPlayer.draw(GameManager.getCurrentPlayer().pickCard(received.getCardName()));
			}
			
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
