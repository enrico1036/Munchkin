package user_interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {

	protected BufferedImage background;
	
	public PaintPanel(BufferedImage image){
		
		this.background=image;

		
	}
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0,0,this.getWidth(),this.getHeight(),this);
	}
	

}

