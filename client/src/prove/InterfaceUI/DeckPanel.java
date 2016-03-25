package prove.InterfaceUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import client.MunchkinClient;
import dataStructure.Data;
import dataStructure.DataListener;
import dataStructure.PlayerData;
import network.GameEventHandler;
import network.message.client.SelectedCardMessage;

public class DeckPanel extends JPanel implements ActionListener,DataListener {
	
	private PlayerData player;
	
	private BufferedImage doorCardsImage, discardsImage, treasureCardsImage;
	
	// ----------Deck JComponents---------------
	private ImageButton doorCards, discards, treasureCards;
	
	private int dw,dh;
	
	
	public DeckPanel(){
		
		dw=getWidth();
		dh=getHeight();
		
		player=Data.getPlayer(GameEventHandler.getConnection().getConnectedPlayerName());
		player.addDataListener(this);
		
		
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
	add(discards);

	treasureCards = new ImageButton(treasureCardsImage);
	treasureCards.setBounds(dw * 13 / 25, dh / 10, dw / 5, dh * 8 / 10);
	treasureCards.addActionListener(this);
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
		discards.setImage(MunchkinClient.getImage(Data.getDiscardDeck().getCards().get(0)));
		
	}
}
