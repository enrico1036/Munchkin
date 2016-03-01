package user_interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class ImageButton extends JButton {

	private BufferedImage image;
	
	public ImageButton(BufferedImage img){
		
		this.image = img;
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, this.getWidth(), this.getHeight(), null);
	}
}
