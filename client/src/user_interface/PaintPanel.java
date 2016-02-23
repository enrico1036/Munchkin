package user_interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {

	private BufferedImage background;
	private int width,height;
	
	public PaintPanel(BufferedImage image,int w,int h){
		
		this.background=image;
		this.width=w;
		this.height=h;
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0,0,width,height,this);
	}
	

}

