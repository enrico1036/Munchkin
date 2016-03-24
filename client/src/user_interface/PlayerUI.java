package user_interface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;

import cards.EquipSlot;
import cards.Equipment;
import client.MunchkinClient;
import game.GameManager;
import network.GameEventHandler;
import prove.InterfaceUI.ClientCard;
import prove.InterfaceUI.GamePanel;
import prove.InterfaceUI.GameWindow;
import prove.InterfaceUI.ImageButton;
import prove.InterfaceUI.ZoomedPanel;

public class PlayerUI extends GamePanel{
	
	private BufferedImage background,head,hand1,body,hand2,feet;;
	//---------Player JComponents------------
	private JLabel lblPlayerName,lblPlayerLevel,
	lblPlayerNumCards,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue;
	
	private ClientCard PlayerHead,PlayerHand1,PlayerBody,PlayerHand2,PlayerFeet,PlayerRace,PlayerClass;
	private ImageButton returnBack;
	
	
	private String playerName;
	
	private int psw,psh;
	private ZoomedPanel zp;

	
	public PlayerUI(GameWindow window, String Player){
		super(window);
		this.background=MunchkinClient.getImage("panel_background");
		this.playerName=Player;
		
		zp=new ZoomedPanel();
		
		GameEventHandler.getPlayerStatistics(playerName);
		GameEventHandler.getPlayerEquipment(playerName);
		
		psw=this.getWidth();
		psh=this.getHeight();
		
		
		

		
		
		zp.setVisible(true);
		zp.setBounds(psw*2/3, 0, psw/3, psh);
		this.add(zp);
		zp.setLayout(null);
		
		this.setComponentZOrder(zp, 0);	
		
		
		
		/*
		 * 
		 * 
		 * 
		 * PLAYERSTATS PANEL AND ITS COMPONENTS
		 * 
		 * 
		 * 
		 */
	
		
	
		
		lblPlayerName = new JLabel(playerName);
		lblPlayerName.setBounds(0, psh/10, psw/3, psh*2/10);
		this.add(lblPlayerName);

		lblPlayerLevel = new JLabel("Level");
		lblPlayerLevel.setBounds(0,psh*4/10,psw/3, psh*2/10);
		this.add(lblPlayerLevel);

		 lblPlayerLevelValue = new JLabel(String.valueOf(1));
		lblPlayerLevelValue.setBounds(psw/3,psh*4/10,psw/3, psh*2/10);
		this.add(lblPlayerLevelValue);
		
		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setBounds(0, psh*7/10, psw/3, psh*2/10);
		this.add(lblPlayerPower);

		 lblPlayerPowerValue = new JLabel(String.valueOf(0));
		 lblPlayerPowerValue.setBounds(psw/3, psh*7/10, psw/3, psh*2/10);
		 this.add(lblPlayerPowerValue);
	
		 PlayerRace = new ClientCard("Race");
		PlayerRace.setBounds(0,psh/2, psw/3, psh/6);
		this.add(PlayerRace);
		
		 PlayerClass = new ClientCard("Class");
		PlayerClass.setBounds(0, psh*4/6,  psw/3, psh/6);
		this.add(PlayerClass);
		
		 lblPlayerNumCards = new JLabel("N Cards");
		 lblPlayerNumCards.setBounds(0, psh*5/6,  psw/3, psh/6);;
		 this.add(lblPlayerNumCards);
		
		 head = MunchkinClient.getImage("player_head");
		 hand1 = MunchkinClient.getImage("player_hand1");
		 body = MunchkinClient.getImage("player_body");
		 hand2 = MunchkinClient.getImage("player_hand2");
		 feet = MunchkinClient.getImage("player_feet");
		
	
		 PlayerHead = new ClientCard("Head");
		 PlayerHead.setBounds(psw*4/9, psh*3/6, psw/9, psh/6);
		 PlayerHead.CreateCard(head, zp);	
		 this.add(PlayerHead);

		 
		 PlayerHand1 = new ClientCard("hand1");
		 PlayerHand1.setBounds(psw*3/9, psh*4/6,psw/9, psh/6);
		 PlayerHand1.CreateCard(hand1, zp);
		 this.add(PlayerHand1);
		
		 
		 PlayerBody = new ClientCard("body");
		 PlayerBody.setBounds(psw*4/9, psh*4/6,psw/9, psh/6);
		 PlayerBody.CreateCard(body, zp);
		 this.add(PlayerBody);
		
		PlayerHand2 = new ClientCard("hand2");
		PlayerHand2.setBounds(psw*5/9, psh*4/6,psw/9, psh/6);
		PlayerHand2.CreateCard(hand2, zp);
		this.add(PlayerHand2);
		
		 PlayerFeet = new ClientCard("feet");
		 PlayerFeet.setBounds(psw*4/9, psh*5/6, psw/9, psh/6);
		 PlayerFeet.CreateCard(feet, zp);
		 this.add(PlayerFeet);
		
			returnBack = new ImageButton(MunchkinClient.getImage("return_back"));
			returnBack.setBounds(psw*7/8,psh*7/8,psw/8,psh/8);
			returnBack.setActionCommand("return");
			returnBack.setVisible(true);
			this.add(returnBack);
		
		

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand()=="return"){
			window.SetActivePanel(MunchkinClient.getPanel("GameUI"));
			
		}
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		
	}
	public void changeStatistics(int levelValue, int powerValue, String newClass,
			String newRace, int numCard){
		
		//update the player level and power value
		lblPlayerLevelValue.setText(String.valueOf(levelValue));
		lblPlayerPowerValue.setText(String.valueOf(powerValue));
		
		//update the player race
		BufferedImage image = MunchkinClient.getImage(newRace);
		
		if(PlayerRace.imageIsNull())
			PlayerRace.CreateCard(image, zp);
		else
			PlayerRace.setImage(image);
		
		//update the player class
		image = MunchkinClient.getImage(newClass);
		
		if(PlayerClass.imageIsNull())
			PlayerClass.CreateCard(image, zp);
		else
			PlayerClass.setImage(image);
		
		//update the size of the player hand
		lblPlayerNumCards.setText(String.valueOf(numCard));
	
		
	}
	
	public void changeEquipment(Equipment head,Equipment hand1,Equipment hand2,
			Equipment body,Equipment feet){
		
			PlayerHead.setImage(MunchkinClient.getImage(head.getTitle()));
			PlayerHand1.setImage(MunchkinClient.getImage(hand1.getTitle()));
			PlayerHand2.setImage(MunchkinClient.getImage(hand2.getTitle()));
			PlayerBody.setImage(MunchkinClient.getImage(body.getTitle()));
			PlayerFeet.setImage(MunchkinClient.getImage(feet.getTitle()));

	}
	
	
}
