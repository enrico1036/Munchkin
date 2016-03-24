package prove.InterfaceUI;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import cards.Equipment;
import client.MunchkinClient;
import user_interface.GameUI;

public class EquipmentPanel extends JPanel{

	private BufferedImage head,hand1,body,hand2,feet;
	
	private ClientCard PlayerHead,PlayerHand1,PlayerBody,PlayerHand2,PlayerFeet;
	private int psw,psh;

	private ZoomedPanel zp;
	
	public EquipmentPanel(int ww,int wh){
		
		zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;
		
		setOpaque(false);
		
		psw=ww;
		psh=wh;
		
		 head = MunchkinClient.getImage("player_head");
		 hand1 = MunchkinClient.getImage("player_hand1");
		 body = MunchkinClient.getImage("player_body");
		 hand2 = MunchkinClient.getImage("player_hand2");
		 feet = MunchkinClient.getImage("player_feet");
		
	
		 PlayerHead = new ClientCard("Head");
		 PlayerHead.setBounds(psw*4/9, psh*3/6, psw/9, psh/6);
		 PlayerHead.CreateCard(head, zp);	
		 add(PlayerHead);

		 
		 PlayerHand1 = new ClientCard("hand1");
		 PlayerHand1.setBounds(psw*3/9, psh*4/6,psw/9, psh/6);
		 PlayerHand1.CreateCard(hand1, zp);
		 add(PlayerHand1);
		
		 
		 PlayerBody = new ClientCard("body");
		 PlayerBody.setBounds(psw*4/9, psh*4/6,psw/9, psh/6);
		 PlayerBody.CreateCard(body, zp);
		 add(PlayerBody);
		
		PlayerHand2 = new ClientCard("hand2");
		PlayerHand2.setBounds(psw*5/9, psh*4/6,psw/9, psh/6);
		PlayerHand2.CreateCard(hand2, zp);
		add(PlayerHand2);
		
		 PlayerFeet = new ClientCard("feet");
		 PlayerFeet.setBounds(psw*4/9, psh*5/6, psw/9, psh/6);
		 PlayerFeet.CreateCard(feet, zp);
		 add(PlayerFeet);
		
	
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
