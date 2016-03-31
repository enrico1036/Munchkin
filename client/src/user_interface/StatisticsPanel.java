package user_interface;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.MunchkinClient;
import user_interface.GameUI;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StatisticsPanel extends JPanel {

	private int psw,psh;
	
	private JLabel lblPlayerName,lblPlayerLevel,
	lblPlayerNumCards,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue,lblNumCard;
	
	private String playerName;
	
	private ClientCard PlayerRace,PlayerClass;

	private ZoomedPanel zp;
	
	public StatisticsPanel(String pn){
		
		zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;
	
		setOpaque(false);
		
		playerName=pn;
		
		lblPlayerName = new JLabel(playerName);

		lblPlayerLevel = new JLabel("Level");

		 lblPlayerLevelValue = new JLabel(String.valueOf(1));
		
		lblPlayerPower = new JLabel("Power");

		 lblPlayerPowerValue = new JLabel(String.valueOf(0));
	
		 PlayerRace = new ClientCard("Race");
		
		 PlayerClass = new ClientCard("Class");
		
		 lblPlayerNumCards = new JLabel("N Cards");
		 
		 lblNumCard = new JLabel("0");
		 
		 
		 GroupLayout groupLayout = new GroupLayout(this);
		 groupLayout.setHorizontalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 				.addGroup(groupLayout.createSequentialGroup()
		 					.addGap(23)
		 					.addComponent(PlayerRace, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
		 					.addPreferredGap(ComponentPlacement.RELATED)
		 					.addComponent(PlayerClass, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
		 				.addGroup(groupLayout.createSequentialGroup()
		 					.addGap(98)
		 					.addComponent(lblPlayerName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 					.addGap(84)))
		 			.addGap(20))
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addGap(50)
		 			.addComponent(lblPlayerLevel, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(lblPlayerLevelValue, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(lblPlayerPower, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(lblPlayerPowerValue, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addComponent(lblPlayerNumCards, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
		 			.addGap(12)
		 			.addComponent(lblNumCard, GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
		 			.addGap(60))
		 );
		 groupLayout.setVerticalGroup(
		 	groupLayout.createParallelGroup(Alignment.LEADING)
		 		.addGroup(groupLayout.createSequentialGroup()
		 			.addContainerGap()
		 			.addComponent(lblPlayerName, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
		 			.addPreferredGap(ComponentPlacement.RELATED)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(lblPlayerLevel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 				.addComponent(lblPlayerLevelValue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 				.addComponent(lblPlayerPower, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 				.addComponent(lblPlayerPowerValue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 				.addComponent(lblPlayerNumCards, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 				.addComponent(lblNumCard, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE))
		 			.addGap(26)
		 			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		 				.addComponent(PlayerClass, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE)
		 				.addComponent(PlayerRace, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 18, Short.MAX_VALUE))
		 			.addGap(50))
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
		return PlayerRace;
	}

	public ClientCard getPlayerClass() {
		return PlayerClass;
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
