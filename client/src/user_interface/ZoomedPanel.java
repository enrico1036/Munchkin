package user_interface;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZoomedPanel extends JLabel {
	
	private BufferedImage card;

	public ZoomedPanel() {

	}

	public void showCard(BufferedImage image) {
		card = image;
		this.setVisible(true);
		this.repaint();
	}
	
	public void hideCard() {
		this.setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(card, 0, 0, this.getWidth(), this.getHeight(), null);
		super.paintComponent(g);
	}

}
