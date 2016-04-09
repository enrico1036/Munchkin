package user_interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZoomedPanel extends JLabel {
	
	private static final long serialVersionUID = -847663119189970074L;
	private BufferedImage card;

	public ZoomedPanel() {
		setOpaque(false);
	}

	public void showPanel(BufferedImage image) {
		card = image;
		setVisible(true);
		repaint();
	}
	
	public void hidePanel() {
		setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(card, 0, 0, getWidth(), getHeight(), null);
	}

}
