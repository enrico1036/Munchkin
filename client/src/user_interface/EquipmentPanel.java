package user_interface;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import cards.Equipment;
import client.MunchkinClient;
import user_interface.GameUI;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		
		setOpaque(false);
		 
		  PlayerHead = new ClientCard("player_head");
		 
		  PlayerHand1 = new ClientCard("player_hand1");
		 
		  PlayerBody = new ClientCard("player_body");
		 
		  PlayerHand2 = new ClientCard("player_hand2");
		 
		  PlayerFeet = new ClientCard("player_feet");
		  
		  
		 GroupLayout groupLayout = new GroupLayout(this);
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap(40, Short.MAX_VALUE)
		 			.addComponent(PlayerHead, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
		 			.addGap(42))
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap(10, Short.MAX_VALUE)
		 			.addComponent(PlayerHand1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
		 			.addGap(5)
		 			.addComponent(PlayerBody, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
		 			.addGap(5)
		 			.addComponent(PlayerHand2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
		 			.addGap(12))
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap(40, Short.MAX_VALUE)
		 			.addComponent(PlayerFeet, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
		 			.addGap(42))
		 );
		 groupLayout.setVerticalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
		 			.addContainerGap(16, Short.MAX_VALUE)
		 			.addComponent(PlayerHead, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
		 			.addGap(6)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 				.addComponent(PlayerHand1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
		 				.addComponent(PlayerBody, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
		 				.addComponent(PlayerHand2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
		 			.addGap(5)
		 			.addComponent(PlayerFeet, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
		 			.addGap(11))
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
