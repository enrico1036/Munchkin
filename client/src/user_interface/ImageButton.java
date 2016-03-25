package user_interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class ImageButton extends JButton {

	private BufferedImage image;
	
	public ImageButton(BufferedImage img){
		
		this.image = img;
		this.setVisible(false);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image,0,0, this.getWidth(), this.getHeight(), null);
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		if(!isVisible())
		{setVisible(true);}
	}
	
	
	public boolean equals(ImageButton bnt){
		boolean result=false;
		if(this.getName().equals(bnt.getName())){
			result=true;
		}
		return result;
		
	}
	
	
}
