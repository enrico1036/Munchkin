package prove.InterfaceUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;

import cards.EquipSlot;
import cards.Equipment;
import client.MunchkinClient;
import dataStructure.DataListener;
import dataStructure.SharedData;
import user_interface.GameUI;
import game.GameManager;
import network.GameEventHandler;

public class PlayerUI extends GamePanel{
	
	private BufferedImage background;
	//---------Player JComponents------------
	


	private String playerName;
	
	private int psw,psh;

	private EquipmentPanel equipment;
	
	private StatisticsPanel statistics;
	
	private DataListener listener;
	
	private ZoomedPanel zp;
	
	public PlayerUI(GameWindow window, String Player,BufferedImage bg){
		super(window);
		background=bg;
		playerName=Player;
		
		SharedData.addDataListener(listener);
		
		zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;
		
		psw=getWidth();
		psh=getHeight();
		
		/*
		 * EQUIPMENT PANEL AND ITS COMPONENTS
		 */
		equipment = new EquipmentPanel(psw,psh);
		/*
		 * PLAYERSTATS PANEL AND ITS COMPONENTS
		 */
		statistics = new StatisticsPanel(playerName,psw,psh);

	
	

	}



	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		
	}
	/*
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
	
		
	}*/
	/*
	public void changeEquipment(Equipment head,Equipment hand1,Equipment hand2,
			Equipment body,Equipment feet){
		
			PlayerHead.setImage(MunchkinClient.getImage(head.getTitle()));
			PlayerHand1.setImage(MunchkinClient.getImage(hand1.getTitle()));
			PlayerHand2.setImage(MunchkinClient.getImage(hand2.getTitle()));
			PlayerBody.setImage(MunchkinClient.getImage(body.getTitle()));
			PlayerFeet.setImage(MunchkinClient.getImage(feet.getTitle()));

	}*/


	public int getPsw() {
		return psw;
	}


	public int getPsh() {
		return psh;
	}
	
	public EquipmentPanel getEquipment() {
		return equipment;
	}

	public StatisticsPanel getStatistics() {
		return statistics;
	}


	
}
