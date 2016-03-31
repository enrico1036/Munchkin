package user_interface;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import user_interface.GameUI;

import client.MunchkinClient;
import network.GameEventHandler;

public class SelfPlayerUI extends PlayerUI {

	// --------Player 1 JComponents---------
	private JLabel lblPlayerName, lblPlayerLevel, lblPlayernumcard, lblPlayerPower, lblPlayerPowerValue, lblPlayerLevelValue;

	private ClientCard PlayerHead, PlayerHand1, PlayerBody, PlayerHand2, PlayerFeet, PlayerRace, PlayerClass;

	private int psw, psh;

	private ZoomedPanel zp;

	private BufferedImage background, framePlayerStats, doorCardsImage, DiscardsImage, TreasureCardsImage, head, hand1, body, hand2, feet;

	public SelfPlayerUI(GameWindow window, String playerName, BufferedImage background) {
		super(window, playerName, background);

		zp = ((GameUI) (MunchkinClient.getPanel("GameUI"))).zp;

		psw = getWidth();
		psh = getHeight();

		
		//getStatistics().setBounds(0, 0, 219, 144);
		add(getStatistics());

		//getEquipment().setBounds(219, 0, 219, 144);
		add(getEquipment());

		/*
		 * lblPlayerName = new JLabel(GameEventHandler.getConnection().getConnectedPlayerName()); lblPlayerName.setBounds(psw / 16, 0, psw * 7 / 16, psh / 3); add(lblPlayerName);
		 * 
		 * lblPlayerLevel = new JLabel("Level"); lblPlayerLevel.setBounds(psw / 2, 0, psw / 4, psh / 6); add(lblPlayerLevel);
		 * 
		 * lblPlayerLevelValue = new JLabel(String.valueOf(1)); lblPlayerLevelValue.setBounds(psw * 3 / 4, 0, psw / 4, psh / 6); add(lblPlayerLevelValue);
		 * 
		 * lblPlayerPower = new JLabel("Power"); lblPlayerPower.setBounds(psw / 2, psh / 6, psw / 4, psh / 6); add(lblPlayerPower);
		 * 
		 * lblPlayerPowerValue = new JLabel(String.valueOf(0)); lblPlayerPowerValue.setBounds(psw * 3 / 4, psh / 6, psw / 4, psh / 6); add(lblPlayerPowerValue);
		 * 
		 * PlayerRace = new ClientCard("Race"); PlayerRace.setBounds(psw / 8, psh / 3, psw / 8, psh / 4); add(PlayerRace);
		 * 
		 * PlayerClass = new ClientCard("Class"); PlayerClass.setBounds(psw / 4, psh / 3, psw / 8, psh / 4); add(PlayerClass);
		 * 
		 * lblPlayernumcard = new JLabel(String.valueOf(0)); lblPlayernumcard.setBounds(psw * 3 / 8, psh / 3, psw / 8, psh / 4); add(lblPlayernumcard);
		 * 
		 * head = MunchkinClient.getImage("player_head"); hand1 = MunchkinClient.getImage("player_hand1"); body = MunchkinClient.getImage("player_body"); hand2 = MunchkinClient.getImage("player_hand2"); feet = MunchkinClient.getImage("player_feet");
		 * 
		 * PlayerHead = new ClientCard("player_head"); PlayerHead.setBounds(psw * 2 / 3, psh / 3, psw / 6, psh * 2 / 9); PlayerHead.setZoomedPanel(zp); add(PlayerHead);
		 * 
		 * PlayerHand1 = new ClientCard("player_hand1"); PlayerHand1.setBounds(psw * 7 / 12, psh * 5 / 9, psw / 12, psh * 2 / 9); PlayerHand1.setZoomedPanel(zp); add(PlayerHand1);
		 * 
		 * PlayerBody = new ClientCard("player_body"); PlayerBody.setBounds(psw * 2 / 3, psh * 5 / 9, psw / 6, psh * 2 / 9); PlayerBody.setZoomedPanel(zp); add(PlayerBody);
		 * 
		 * PlayerHand2 = new ClientCard("player_hand2"); PlayerHand2.setBounds(psw * 5 / 6, psh * 5 / 9, psw / 12, psh * 2 / 9); PlayerHand2.setZoomedPanel(zp); add(PlayerHand2);
		 * 
		 * PlayerFeet = new ClientCard("player_feet"); PlayerFeet.setBounds(psw * 2 / 3, psh * 7 / 9, psw / 6, psh * 2 / 9); PlayerFeet.setZoomedPanel(zp); add(PlayerFeet);
		 */
	}

}
