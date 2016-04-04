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
	
	private ClientCard PlayerHead,PlayerHand1,PlayerHand2,PlayerBody,PlayerFeet;
	
	public EquipmentPanel(){
		zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;

		setOpaque(true);
		setBackground(Color.BLUE);

		PlayerHead = new ClientCard("player_head");
		PlayerHand1 = new ClientCard("player_hand1");
		PlayerBody = new ClientCard("player_body");
		PlayerHand2 = new ClientCard("player_hand2");
		PlayerFeet = new ClientCard("player_feet");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(PlayerHand1, 0, 64, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(PlayerHead, 0, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(PlayerBody, 0, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(PlayerFeet, 0, 64, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(PlayerHand2, 0, 64, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()
				);
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(PlayerHead, 0, 110, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(PlayerHand1, 0, 110, GroupLayout.PREFERRED_SIZE)
						.addComponent(PlayerBody, 0, 110, GroupLayout.PREFERRED_SIZE)
						.addComponent(PlayerHand2, 0, 110, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(PlayerFeet, 0, 110, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()
				);
		setLayout(groupLayout);
	}

	public ClientCard getPlayerHead() {
		return PlayerHead;
	}

	public ClientCard getPlayerHand1() {
		return PlayerHand1;
	}

	public ClientCard getPlayerBody() {
		return PlayerBody;
	}

	public ClientCard getPlayerHand2() {
		return PlayerHand2;
	}

	public ClientCard getPlayerFeet() {
		return PlayerFeet;
	}
}
