package user_interface;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class EndUI extends JPanel {

	private String player;
	private BufferedImage background;

	public EndUI(BufferedImage background, String player) {
		this.background = background;
		this.player = player;
		setVisible(true);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setSize(getParent().getSize());
		g.drawImage(background.getScaledInstance(getParent().getWidth(), getParent().getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
	}

}
