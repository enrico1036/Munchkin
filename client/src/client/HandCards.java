package client;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import network.GameEventHandler;

public class HandCards extends ClientCard {

	public HandCards(String title) {
		super(title);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int dialogResult=JOptionPane.showConfirmDialog(MunchkinClient.getPanel("GameUI"),
                "Do you want to play this card?",
                "Play a card", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE); 
		
		if(dialogResult == JOptionPane.YES_OPTION){
			GameEventHandler.selectCard(((ClientCard)e.getSource()).getName());
		}
	}
}
