package user_interface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import cards.Equipment;
import client.MunchkinClient;
import prove.InterfaceUI.ClientCard;
import prove.InterfaceUI.GameWindow;
import prove.InterfaceUI.ImageButton;
import prove.InterfaceUI.PaintPanel;
import prove.InterfaceUI.ZoomedPanel;

public class PlayerPanel extends PaintPanel {


	//---------Player JComponents------------
	private JLabel lblPlayerName,lblPlayerLevel,
	lblPlayerNumCards,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue;
	private JButton btnPlayerCards;
	private ClientCard PlayerRace,PlayerClass;
	
	
	
	private int pw,ph;
	private String playerName;
	private GameWindow wnd;
	
	
	
	private BufferedImage showPlayerCards;
	private PlayerUI detailsPanel;
	private ZoomedPanel zp;
	
	public PlayerPanel(BufferedImage image,String playerName,GameWindow wnd,ZoomedPanel zp) {
		super(image);
		
		showPlayerCards = MunchkinClient.getImage("cards");
		
		pw=this.getWidth();
		ph=this.getHeight();
		
		this.playerName=playerName; 
		this.wnd=wnd;
		this.zp=zp;
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

		 lblPlayerLevelValue = new JLabel(String.valueOf(1));
		 lblPlayerLevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		 this.add(lblPlayerLevelValue);
		
		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setBounds(pw/2, ph/4, pw/4, ph/4);
		this.add(lblPlayerPower);

		 lblPlayerPowerValue = new JLabel(String.valueOf(0));
		 lblPlayerPowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		 this.add(lblPlayerPowerValue);
	     
		 PlayerRace = new ClientCard("Race");
		PlayerRace.setBounds(pw/4,ph/2, pw/4, ph/4);
		this.add(PlayerRace);
		
		 PlayerClass = new ClientCard("Class");
		PlayerClass.setBounds(pw/2,ph/2, pw/4, ph/4);
		this.add(PlayerClass);
		
		 lblPlayerNumCards = new JLabel(String.valueOf(0));
		 lblPlayerNumCards.setBounds(pw*3/4, ph/2, pw/4, ph/4);
		 this.add(lblPlayerNumCards);
		
		
		
		btnPlayerCards = new ImageButton(showPlayerCards);
		btnPlayerCards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);
		btnPlayerCards.setActionCommand("show_player");
		btnPlayerCards.setVisible(true);
		btnPlayerCards.setContentAreaFilled(false);
		btnPlayerCards.setBorderPainted(false);
		this.add(btnPlayerCards);
		
		detailsPanel=new PlayerUI(wnd,playerName);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand()=="show_player"){
			wnd.SetActivePanel(detailsPanel);
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
		PlayerRace.setBounds(pw/4,ph/2, pw/4, ph/4);
		PlayerClass.setBounds(pw/2,ph/2, pw/4, ph/4);
		lblPlayerNumCards.setBounds(pw*3/4, ph/2, pw/4, ph/4);
		btnPlayerCards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);

	}
	

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0,0,this.getWidth(),this.getHeight(),this);
	}

	public PlayerUI getDetailsPanel() {
		return detailsPanel;
	}

	public String getPlayerName() {
		return playerName;
	}

	public ClientCard getLblPlayerRace() {
		return PlayerRace;
	}

	public ClientCard getLblPlayerClass() {
		return PlayerClass;
	}

	public JLabel getLblPlayerPowerValue() {
		return lblPlayerPowerValue;
	}

	public JLabel getLblPlayerLevelValue() {
		return lblPlayerLevelValue;
	}

	public JLabel getLblPlayerNumCards() {
		return lblPlayerNumCards;
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
		
		//update the number of the player hand
		lblPlayerNumCards.setText(String.valueOf(numCard));
	
		
		
		detailsPanel.changeStatistics(levelValue, powerValue, newClass, newRace, numCard);
	}
	
	
	
}
