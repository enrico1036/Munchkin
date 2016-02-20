package user_interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;

import client.MunchkinClient;

public class LobbyUI extends GamePanel {

	private BufferedImage background=null;
	private JTextArea Users,Gamestatus;
	private int wndWidth,wndHeight;
	
	public LobbyUI(GameWindow window) {
				
		this.background=MunchkinClient.getImage("lobby_background");
		this.Users = new JTextArea("Users");
		Users.setEditable(false);		
		this.add(Users);
		
		
		this.Gamestatus = new JTextArea("Gamestatus");
		Gamestatus.setEditable(false);
		
		this.add(Gamestatus);
	}


	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		
	}
	
	private void updateComponents(){
		 wndWidth = this.getWidth();
		 wndHeight = this.getHeight();
		 
		 this.resizeArea(Users, wndWidth*4/5, 0, 50, 50);
		 this.resizeArea(Gamestatus, wndWidth*4/5, wndHeight/2, 50, 50);
		 
		 
	}
	
	public void resizeArea(JTextArea text,int newX,int newY,int newWidth,int newHeight){
		
		text.setBounds(newX, newY, newWidth, newWidth);
		
		
	}
	
}
