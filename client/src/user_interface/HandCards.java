package user_interface;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import client.MunchkinClient;
import network.GameEventHandler;
/**
 * 
 * This extension of ClientCard is used to distinguish the card on the client hand
 * and the other card like Equipment, Race or ClassCard.
 * In fact this class has an implementation of MouseClicked event that
 * ask to the player if he want to play that selected card.
 * 
 *
 */
public class HandCards extends ClientCard {

	/**
	 * 
	 * @param title: the title of the card
	 */
	public HandCards(String title) {
		super(title);
		
	}

	/**
	 * Ask to the client if he want to play,or not, the selected card
	 */
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
