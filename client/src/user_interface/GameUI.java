package user_interface;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


import client.MunchkinClient;

public class GameUI extends GamePanel{
	
	BufferedImage background=null;
	
	private  GameWindow wnd;
	public GameUI(GameWindow window) {
		wnd =window;
		
		this.background=MunchkinClient.getImage("option_button");
	
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		
	}


	
}
	
	
	
	
	
	

