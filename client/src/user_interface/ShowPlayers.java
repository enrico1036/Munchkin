package user_interface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;

import client.MunchkinClient;

public class ShowPlayers extends GamePanel{
	
	private BufferedImage background;
	//---------Player JComponents------------
	private JLabel lblPlayerName,lblPlayerLevel,lblPlayerRace,lblPlayerClass,
	lblPlayerSex,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue,
	lblPlayerHead,lblPlayerRiarm,lblPlayerBody,lblPlayerLearm,lblPlayerLegs;
	private JButton returnBack;
	
	private int psw,psh;
	
	public ShowPlayers(GameWindow window, String Player){
		super(window);
		this.background=MunchkinClient.getImage("panel_background");
		
		psw=this.getWidth();
		psh=this.getHeight();
		
		/*
		 * 
		 * 
		 * 
		 * PLAYERSTATS PANEL AND ITS COMPONENTS
		 * 
		 * 
		 * 
		 */
	
		
	
		
		lblPlayerName = new JLabel("PlayerName");
		lblPlayerName.setBounds(psw/16, 0, psw*7/16, psh/3);
		this.add(lblPlayerName);

		lblPlayerLevel = new JLabel("Level");
		lblPlayerLevel.setBounds(psw/2,0, psw/4,psh/6);
		this.add(lblPlayerLevel);

		 lblPlayerLevelValue = new JLabel("Level Value");
		lblPlayerLevelValue.setBounds(psw*3/4,0, psw/4, psh/6);
		this.add(lblPlayerLevelValue);
		
		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setBounds(psw/2, psh/6, psw/4, psh/6);
		this.add(lblPlayerPower);

		 lblPlayerPowerValue = new JLabel("Power Value");
		 lblPlayerPowerValue.setBounds(psw*3/4, psh/6, psw/4, psh/6);
		 this.add(lblPlayerPowerValue);
	
		 lblPlayerRace = new JLabel("Race");
		lblPlayerRace.setBounds(psw/8,psh/3, psw/8, psh/4);
		this.add(lblPlayerRace);
		
		 lblPlayerClass = new JLabel("Class");
		lblPlayerClass.setBounds(psw/4, psh/3, psw/8, psh/4);
		this.add(lblPlayerClass);
		
		 lblPlayerSex = new JLabel("Sex");
		lblPlayerSex.setBounds(psw*3/8, psh/3, psw/8, psh/4);
		this.add(lblPlayerSex);
		
		
	
		 lblPlayerHead = new JLabel("Head");
		 lblPlayerHead.setBounds(psw*2/3, psh/3, psw/6, psh*2/9);
		 this.add(lblPlayerHead);
		
		
		
		 lblPlayerRiarm = new JLabel("RiArm");
		 lblPlayerRiarm.setBounds(psw/2, psh*5/9, psw/6, psh*2/9);
		 this.add(lblPlayerRiarm);
		
		 lblPlayerBody = new JLabel("Body");
		 lblPlayerBody.setBounds(psw*2/3, psh*5/9, psw/6, psh*2/9);
		 this.add(lblPlayerBody);
		
		 lblPlayerLearm = new JLabel("LeArm");
		 lblPlayerLearm.setBounds(psw*5/6, psh*5/9,psw/6, psh*2/9);
		 this.add(lblPlayerLearm);
		
		 lblPlayerLegs = new JLabel("Legs");
		 lblPlayerLegs.setBounds(psw*2/3, psh*7/9, psw/6, psh*2/9);
		 this.add(lblPlayerLegs);
	
			returnBack = new JButton();
			returnBack.setBounds(psw*7/8,psh*7/8,psw/8,psh/8);
			returnBack.setActionCommand("return");
			returnBack.setVisible(true);
			//returnBack.setContentAreaFilled(false);
			//returnBack.setBorderPainted(false);
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
}
