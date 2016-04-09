package user_interface;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import cards.Equipment;
import client.MunchkinClient;
import user_interface.GameUI;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EquipmentPanel extends JPanel{
	private int psw,psh;

	private ZoomedPanel zp;
	
	private ClientCard playerHead, playerHand1, playerHand2, playerBody, playerFeet;
	
	public EquipmentPanel(){
		zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;

		setOpaque(true);
		setBackground(Color.BLUE);

		playerHead = new ClientCard("player_head");
		playerHead.setEnabled(false);
		playerHand1 = new ClientCard("player_hand1");
		playerHand1.setEnabled(false);
		playerBody = new ClientCard("player_body");
		playerBody.setEnabled(false);
		playerHand2 = new ClientCard("player_hand2");
		playerHand2.setEnabled(false);
		playerFeet = new ClientCard("player_feet");
		playerFeet.setEnabled(false);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(playerHand1, 0, 64, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(playerHead, 0, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(playerBody, 0, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(playerFeet, 0, 64, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(playerHand2, 0, 64, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()
				);
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(playerHead, 0, 110, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(playerHand1, 0, 110, GroupLayout.PREFERRED_SIZE)
						.addComponent(playerBody, 0, 110, GroupLayout.PREFERRED_SIZE)
						.addComponent(playerHand2, 0, 110, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(playerFeet, 0, 110, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()
				);
		setLayout(groupLayout);
	}

	public ClientCard getPlayerHead() {
		return playerHead;
	}

	public ClientCard getPlayerHand1() {
		return playerHand1;
	}

	public ClientCard getPlayerBody() {
		return playerBody;
	}

	public ClientCard getPlayerHand2() {
		return playerHand2;
	}

	public ClientCard getPlayerFeet() {
		return playerFeet;
	}
}
