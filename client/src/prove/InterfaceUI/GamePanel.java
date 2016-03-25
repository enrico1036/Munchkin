package prove.InterfaceUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	protected BufferedImage background;
	protected GameWindow window;

	public GamePanel(GameWindow window, BufferedImage background) {
		this.window = window;
		this.background = background;
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
