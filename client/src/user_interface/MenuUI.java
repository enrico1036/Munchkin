package user_interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuUI extends JPanel{
	
	BufferedImage background=null;
	
	public MenuUI() {
		
		try {
			this.background=ImageIO.read(new File("Resources/Images/home.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background,0,0, this.getWidth(), this.getHeight(),null);
		
	}
	
}
	
	
	
	
	
	

