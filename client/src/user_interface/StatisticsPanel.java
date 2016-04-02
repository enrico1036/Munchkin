package user_interface;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.MunchkinClient;
import user_interface.GameUI;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;

public class StatisticsPanel extends JPanel {

	private int psw,psh;
	
	private JLabel lblPlayerName, lblPlayerLevel, 
	lblPlayerNumCards, lblPlayerPower, lblPlayerPowerValue, lblPlayerLevelValue, lblNumCard;
	
	private String playerName;
	
	private ClientCard playerRace, playerClass;

	private ZoomedPanel zp;
	
	public StatisticsPanel(String pn){
		
		zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;
	
		setOpaque(false);
		
		playerName=pn;
		
		lblPlayerName = new JLabel(playerName);
		lblPlayerName.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPlayerName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerName.setAlignmentX(Component.CENTER_ALIGNMENT);

		lblPlayerLevel = new JLabel("Level");
		lblPlayerLevel.setHorizontalAlignment(SwingConstants.TRAILING);

		lblPlayerLevelValue = new JLabel(String.valueOf(1));
		
		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setHorizontalAlignment(SwingConstants.TRAILING);

		lblPlayerPowerValue = new JLabel(String.valueOf(0));
	
		playerRace = new ClientCard("Race");
		
		playerClass = new ClientCard("Class");
		
		lblPlayerNumCards = new JLabel("N Cards");
		lblPlayerNumCards.setHorizontalAlignment(SwingConstants.TRAILING);
		 
		lblNumCard = new JLabel("0");
		 
		 
		 GroupLayout groupLayout = new GroupLayout(this);
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 				.addComponent(lblPlayerName, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
		 				.addGroup(groupLayout.createSequentialGroup()
		 					.addComponent(lblPlayerLevel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.RELATED)
		 					.addComponent(lblPlayerLevelValue, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
		 					.addGap(92)
		 					.addComponent(lblPlayerPower, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
		 					.addPreferredGap(ComponentPlacement.RELATED)
		 					.addComponent(lblPlayerPowerValue, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
		 					.addGap(78)
		 					.addComponent(lblPlayerNumCards)
		 					.addPreferredGap(ComponentPlacement.RELATED)
		 					.addComponent(lblNumCard, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
		 				.addGroup(groupLayout.createSequentialGroup()
		 					.addComponent(playerRace, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
		 					.addPreferredGap(ComponentPlacement.RELATED)
		 					.addComponent(playerClass, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
		 			.addContainerGap())
		 );
		 groupLayout.setVerticalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addComponent(lblPlayerName, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
		 				.addComponent(lblNumCard)
		 				.addComponent(lblPlayerNumCards)
		 				.addComponent(lblPlayerPower)
		 				.addComponent(lblPlayerPowerValue)
		 				.addComponent(lblPlayerLevel)
		 				.addComponent(lblPlayerLevelValue))
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
		 				.addComponent(playerClass, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
		 				.addComponent(playerRace, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
		 			.addContainerGap())
		 );
		 setLayout(groupLayout);
	
	
		
		
		
	}

	public JLabel getLblPlayerName() {
		return lblPlayerName;
	}


	public JLabel getLblNumCards() {
		return lblNumCard;
	}

	public JLabel getLblPlayerPowerValue() {
		return lblPlayerPowerValue;
	}

	public JLabel getLblPlayerLevelValue() {
		return lblPlayerLevelValue;
	}

	public ClientCard getPlayerRace() {
		return playerRace;
	}

	public ClientCard getPlayerClass() {
		return playerClass;
	}
/*
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		psw=width;
		psh=height;
		lblPlayerName.setBounds(0, psh/10, psw/3, psh*2/10);
		lblPlayerLevel.setBounds(0,psh*4/10,psw/3, psh*2/10);
		lblPlayerLevelValue.setBounds(psw/3,psh*4/10,psw/3, psh*2/10);
		lblPlayerPower.setBounds(0, psh*7/10, psw/3, psh*2/10);
		 lblPlayerPowerValue.setBounds(psw/3, psh*7/10, psw/3, psh*2/10);
			PlayerRace.setBounds(0,psh/2, psw/3, psh/6);
			PlayerClass.setBounds(0, psh*4/6,  psw/3, psh/6);
			 lblPlayerNumCards.setBounds(0, psh*5/6,  psw/3, psh/6);
	}*/
}
