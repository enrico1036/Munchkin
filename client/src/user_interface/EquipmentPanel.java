package user_interface;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import cards.Equipment;
import client.MunchkinClient;
import user_interface.GameUI;

public class EquipmentPanel extends JPanel{

	
	private ClientCard PlayerHead,PlayerHand1,PlayerBody,PlayerHand2,PlayerFeet;
	private int psw,psh;

	private ZoomedPanel zp;
	
	public EquipmentPanel(){
		
		zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;
		
		setOpaque(false);
		setLayout(null);

		 PlayerHead = new ClientCard("player_head");
		 PlayerHead.setZoomedPanel(zp);	
		 add(PlayerHead);

		 
		 PlayerHand1 = new ClientCard("player_hand1");
		 PlayerHand1.setZoomedPanel(zp);
		 add(PlayerHand1);
		
		 
		 PlayerBody = new ClientCard("player_body");
		 PlayerBody.setZoomedPanel(zp);
		 add(PlayerBody);
		
		PlayerHand2 = new ClientCard("player_hand2");
		PlayerHand2.setZoomedPanel(zp);
		add(PlayerHand2);
		
		 PlayerFeet = new ClientCard("player_feet");
		 PlayerFeet.setZoomedPanel(zp);
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

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		psw=width;
		psh=height;
		 PlayerHead.setBounds(psw*4/9, psh*3/6, psw/9, psh/6);
		 PlayerHand1.setBounds(psw*3/9, psh*4/6,psw/9, psh/6);
		 PlayerBody.setBounds(psw*4/9, psh*4/6,psw/9, psh/6);
			PlayerHand2.setBounds(psw*5/9, psh*4/6,psw/9, psh/6);
			 PlayerFeet.setBounds(psw*4/9, psh*5/6, psw/9, psh/6);
		
	}

	
	
	
}
