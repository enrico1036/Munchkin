package user_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import client.MunchkinClient;
import dataStructure.CardDataSet;
import dataStructure.Data;
import dataStructure.DataListener;
import dataStructure.PlayerData;
import dataStructure.TurnData;
import network.GameEventHandler;
import network.message.client.SelectedCardMessage;

public class DeckPanel extends JPanel implements ActionListener, DataListener {

	private BufferedImage doorCardsImage, discardsImage, treasureCardsImage;

	// ----------Deck JComponents---------------
	private ImageButton doorCards, discards, treasureCards;

	private int dw, dh;

	public DeckPanel() {

		dw = getWidth();
		dh = getHeight();

		Data.getDiscardDeck().addDataListener(this);
		Data.getTurn().addDataListener(this);

		doorCardsImage = MunchkinClient.getImage("door_back");

		discardsImage = MunchkinClient.getImage("door_card");

		treasureCardsImage = MunchkinClient.getImage("treasure_back");

		doorCards = new ImageButton(doorCardsImage);
		doorCards.setBounds(dw / 25, dh / 10, dw / 5, dh * 8 / 10);
		doorCards.setActionCommand("DrawDoor");
		doorCards.addActionListener(this);
		doorCards.setVisible(true);
		add(doorCards);

		discards = new ImageButton(discardsImage);
		discards.setBounds(dw * 7 / 25, dh / 10, dw / 5, dh * 8 / 10);
		discards.setVisible(true);
		discards.setEnabled(false);
		add(discards);

		treasureCards = new ImageButton(treasureCardsImage);
		treasureCards.setBounds(dw * 13 / 25, dh / 10, dw / 5, dh * 8 / 10);
		treasureCards.setEnabled(false);
		treasureCards.setVisible(true);
		add(treasureCards);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("DrawDoor")) {
			GameEventHandler.selectCard(SelectedCardMessage.DOOR_DECK);
		}
	}

	@Override
	public void dataChanged() {
		if (Data.getDiscardDeck().getCards().size() > 0) {
			discards.setImage(MunchkinClient.getImage(Data.getDiscardDeck().getCards().get(0)));
		}
		if (Data.getTurn().getCurrentPlayer().equals(GameEventHandler.getConnection().getConnectedPlayerName()) && (Data.getTurn().getPhase() == TurnData.GamePhase.Equip || Data.getTurn().getPhase() == TurnData.GamePhase.LookForTrouble)) {
			doorCards.setEnabled(true);
		} else {
			doorCards.setEnabled(false);
		}
	}
}
