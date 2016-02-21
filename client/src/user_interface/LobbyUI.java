package user_interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import client.MunchkinClient;

public class LobbyUI extends GamePanel {

	private BufferedImage background,dragon,users;
	private JTextArea Users,Gamestatus;
	private int wndWidth,wndHeight;
	
	public LobbyUI(GameWindow window) {
				
		this.background=MunchkinClient.getImage("lobby_background");
		this.dragon=MunchkinClient.getImage("dragon_lobby");
		this.users=MunchkinClient.getImage("users_lobby");
		
		this.Users = new JTextArea("Utente a \nUtente b \nUtente c");
		Users.setFocusable(false);
		Users.setBackground(new Color(0,0,0,10));
		this.add(Users);
		
		
		this.Gamestatus = new JTextArea();
		Gamestatus.setFocusable(false);
		this.add(Gamestatus);
		
		
		for(int i =100;i>0;i--)
		{
			Gamestatus.append("Gamestarting in "+i+"\n");
		}
		
		
	}


	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		g.drawImage(dragon, 0, 0, this.getWidth()*2/5, this.getHeight(), null);
		g.drawImage(users, this.getWidth()*3/5, 0, users.getWidth(), users.getHeight(), null);
		
	}
	
	private void updateComponents(){
		 wndWidth = this.getWidth();
		 wndHeight = this.getHeight();
		 
		 this.resizeTextArea(Users, wndWidth*3/5, wndHeight/10, wndWidth/5, wndHeight/4);
		 this.resizeTextArea(Gamestatus, wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
		 
		 
	}
	
	public void resizeTextArea(JTextArea text,int newX,int newY,int newWidth,int newHeight){
		
		text.setBounds(newX, newY, newWidth, newWidth);
		
		
	}
	
}
