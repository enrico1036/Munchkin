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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.SwingConstants;

public class DeckPanel extends JPanel implements ActionListener, DataListener {

//	private BufferedImage doorCardsImage, discardsImage, treasureCardsImage;

	// ----------Deck JComponents---------------
	private ImageButton doorCards, discards, treasureCards;
	private JLabel phaseLabel;

//	private int dw, dh;

	public DeckPanel() {

//		dw = getWidth();
//		dh = getHeight();

		Data.getDiscardDeck().addDataListener(this);
		Data.getTurn().addDataListener(this);
		
		doorCards = new ImageButton(MunchkinClient.getImage("door_back"));
		doorCards.setActionCommand("DrawDoor");
		doorCards.addActionListener(this);
		doorCards.setEnabled(false);
		doorCards.setVisible(true);
		
		discards = new ImageButton(MunchkinClient.getImage("door_card"));
		discards.setVisible(true);
		discards.setEnabled(false);
		
		treasureCards = new ImageButton(MunchkinClient.getImage("treasure_back"));
		treasureCards.setEnabled(false);
		treasureCards.setVisible(true);
		
		phaseLabel = new JLabel("Phase");
		phaseLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		phaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(doorCards, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(discards, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(treasureCards, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					//.addGap(450))
					)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(0, Short.MAX_VALUE)
					.addComponent(phaseLabel, 0, 438, Short.MAX_VALUE)
					.addContainerGap(6, 6))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(0, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(doorCards, 100, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(treasureCards, 100, 150, GroupLayout.PREFERRED_SIZE)
						.addComponent(discards, 100, 150, GroupLayout.PREFERRED_SIZE))
					.addGap(0, 139, Short.MAX_VALUE)
					.addComponent(phaseLabel)
					.addContainerGap())
		);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		setLayout(groupLayout);

		
//		doorCardsImage = MunchkinClient.getImage("door_back");
//
//		discardsImage = MunchkinClient.getImage("door_card");
//
//		treasureCardsImage = MunchkinClient.getImage("treasure_back");

//		doorCards = new ImageButton(doorCardsImage);
//		doorCards.setBounds(dw / 25, dh / 10, dw / 5, dh * 8 / 10);
//		doorCards.setActionCommand("DrawDoor");
//		doorCards.addActionListener(this);
//		doorCards.setVisible(true);
//		add(doorCards);
//
//		discards = new ImageButton(discardsImage);
//		discards.setBounds(dw * 7 / 25, dh / 10, dw / 5, dh * 8 / 10);
//		discards.setVisible(true);
//		discards.setEnabled(false);
//		add(discards);
//
//		treasureCards = new ImageButton(treasureCardsImage);
//		treasureCards.setBounds(dw * 13 / 25, dh / 10, dw / 5, dh * 8 / 10);
//		treasureCards.setEnabled(false);
//		treasureCards.setVisible(true);
//		add(treasureCards);
		
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
		try {
		phaseLabel.setText(Data.getTurn().getPhase().toString());
		} catch (NullPointerException e) {
			
		}
		if (Data.getTurn().getCurrentPlayer().equals(GameEventHandler.getConnection().getConnectedPlayerName()) /*&& (Data.getTurn().getPhase() == TurnData.GamePhase.Equip || Data.getTurn().getPhase() == TurnData.GamePhase.LookForTrouble)*/) {
			doorCards.setEnabled(true);
		} else {
			doorCards.setEnabled(false);
		}
	}
}
