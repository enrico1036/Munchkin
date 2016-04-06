package user_interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class EndUI extends JPanel {

	private String player;
	private BufferedImage background;
	
	public EndUI(BufferedImage background,String player) {
		this.background= background;
		this.player=player;
	
		
		setVisible(true);
		
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		g.drawImage(background, getWidth(), getHeight(), null);
	}
	
	

}
