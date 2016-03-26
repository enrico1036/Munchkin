package user_interface;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.MunchkinClient;
import user_interface.GameUI;

public class StatisticsPanel extends JPanel {

	private int psw,psh;
	
	private JLabel lblPlayerName,lblPlayerLevel,
	lblPlayerNumCards,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue;
	
	private String playerName;
	
	private ClientCard PlayerRace,PlayerClass;

	private ZoomedPanel zp;
	
	public StatisticsPanel(String pn){
		
		zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;
	
		setOpaque(false);
		
		playerName=pn;
		
		lblPlayerName = new JLabel(playerName);
		add(lblPlayerName);

		lblPlayerLevel = new JLabel("Level");
		add(lblPlayerLevel);

		 lblPlayerLevelValue = new JLabel(String.valueOf(1));
		add(lblPlayerLevelValue);
		
		lblPlayerPower = new JLabel("Power");
		add(lblPlayerPower);

		 lblPlayerPowerValue = new JLabel(String.valueOf(0));
		 add(lblPlayerPowerValue);
	
		 PlayerRace = new ClientCard("Race");
		add(PlayerRace);
		
		 PlayerClass = new ClientCard("Class");
		add(PlayerClass);
		
		 lblPlayerNumCards = new JLabel("N Cards");
		 add(lblPlayerNumCards);
	
	
		
		
		
	}

	public JLabel getLblPlayerName() {
		return lblPlayerName;
	}


	public JLabel getLblPlayerNumCards() {
		return lblPlayerNumCards;
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
	}
	
	
}
