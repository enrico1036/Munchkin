package prove.InterfaceUI;

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
	
	public StatisticsPanel(String pn,int ww,int wh){
		
		zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;
		
		psw=ww;
		psh=wh;
		
		setOpaque(false);
		
		playerName=pn;
		
		lblPlayerName = new JLabel(playerName);
		lblPlayerName.setBounds(0, psh/10, psw/3, psh*2/10);
		add(lblPlayerName);

		lblPlayerLevel = new JLabel("Level");
		lblPlayerLevel.setBounds(0,psh*4/10,psw/3, psh*2/10);
		add(lblPlayerLevel);

		 lblPlayerLevelValue = new JLabel(String.valueOf(1));
		lblPlayerLevelValue.setBounds(psw/3,psh*4/10,psw/3, psh*2/10);
		add(lblPlayerLevelValue);
		
		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setBounds(0, psh*7/10, psw/3, psh*2/10);
		add(lblPlayerPower);

		 lblPlayerPowerValue = new JLabel(String.valueOf(0));
		 lblPlayerPowerValue.setBounds(psw/3, psh*7/10, psw/3, psh*2/10);
		 add(lblPlayerPowerValue);
	
		 PlayerRace = new ClientCard("Race");
		PlayerRace.setBounds(0,psh/2, psw/3, psh/6);
		add(PlayerRace);
		
		 PlayerClass = new ClientCard("Class");
		PlayerClass.setBounds(0, psh*4/6,  psw/3, psh/6);
		add(PlayerClass);
		
		 lblPlayerNumCards = new JLabel("N Cards");
		 lblPlayerNumCards.setBounds(0, psh*5/6,  psw/3, psh/6);;
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
	
	
}
