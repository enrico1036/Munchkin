package user_interface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import client.MunchkinClient;

public class PlayerPanel extends PaintPanel {


	//---------Player JComponents------------
	private JLabel lblPlayerName,lblPlayerLevel,lblPlayerRace,lblPlayerClass,
	lblPlayerCards,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue;
	private JButton btnPlayerCards;
	private int pw,ph;
	private String playerName;
	
	
	private ImageIcon showPlayerCards;
	
	
	public PlayerPanel(BufferedImage image,String playerName) {
		super(image);
		
		showPlayerCards = new ImageIcon();
		showPlayerCards.setImage(MunchkinClient.getImage("cards"));
		
		pw=this.getWidth();
		ph=this.getHeight();
		
		this.playerName=playerName; //Devo fare richiesta al server
		
		/*
		 * 
		 * PLAYER PANEL JCOMPONENTS
		 * 
		 */
				
		lblPlayerName = new JLabel(playerName);
		lblPlayerName.setBounds(pw/16, 0, pw*7/16, ph/2);
		this.add(lblPlayerName);

		lblPlayerLevel = new JLabel("Level");
		lblPlayerLevel.setBounds(pw/2,0, pw/4, ph/4);
		this.add(lblPlayerLevel);

		 lblPlayerLevelValue = new JLabel("Level Value");
		 lblPlayerLevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		 this.add(lblPlayerLevelValue);
		
		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setBounds(pw/2, ph/4, pw/4, ph/4);
		this.add(lblPlayerPower);

		 lblPlayerPowerValue = new JLabel("Power Value");
		 lblPlayerPowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		 this.add(lblPlayerPowerValue);
	
		 lblPlayerRace = new JLabel("Race");
		lblPlayerRace.setBounds(pw/4,ph/2, pw/4, ph/4);
		this.add(lblPlayerRace);
		
		 lblPlayerClass = new JLabel("Class");
		lblPlayerClass.setBounds(pw/2,ph/2, pw/4, ph/4);
		this.add(lblPlayerClass);
		
		 lblPlayerCards = new JLabel("N Cards");
		 lblPlayerCards.setBounds(pw*3/4, ph/2, pw/4, ph/4);
		 this.add(lblPlayerCards);
		
		
		
		btnPlayerCards = new JButton(showPlayerCards);
		btnPlayerCards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);
		btnPlayerCards.setActionCommand("show_player");
		btnPlayerCards.setVisible(true);
		btnPlayerCards.setContentAreaFilled(false);
		btnPlayerCards.setBorderPainted(false);
		this.add(btnPlayerCards);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand()=="show_player"){
			GameWindow window = ((GamePanel)getParent()).window;
			((GamePanel)getParent()).window.SetActivePanel(new ShowPlayers(window,playerName));
		}
	}

	public void updatePlayerComponents(){
	
		pw=this.getWidth();
		ph=this.getHeight();
		
		lblPlayerName.setBounds(pw/16, 0, pw*7/16, ph/2);
		lblPlayerLevel.setBounds(pw/2,0, pw/4, ph/4);
		lblPlayerLevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		lblPlayerPower.setBounds(pw/2, ph/4, pw/4, ph/4);
		lblPlayerPowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		lblPlayerRace.setBounds(pw/4,ph/2, pw/4, ph/4);
		lblPlayerClass.setBounds(pw/2,ph/2, pw/4, ph/4);
		lblPlayerCards.setBounds(pw*3/4, ph/2, pw/4, ph/4);
		btnPlayerCards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);

	}
	

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0,0,this.getWidth(),this.getHeight(),this);
	}

}
