package user_interface;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import client.MunchkinClient;

public class GameUI extends GamePanel{
	
	BufferedImage background=null;
	
	public GameUI() {
		this.background=MunchkinClient.getImage("option_button");
	
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		
	}


	
}
	
	
	
	
	
	

