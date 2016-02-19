package user_interface;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import client.MunchkinClient;

public class MenuUI extends GamePanel{
	
	BufferedImage background=null;
	private static MunchkinClient client = new MunchkinClient();
	private static Game_Window wnd = new Game_Window();
	public MenuUI() {
		
		try {
			this.background=ImageIO.read(new File("Resources/Images/home.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		this.addKeyListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background,0,0, this.getWidth(), this.getHeight(),null);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		switch (e.getKeyChar())
		{
		case 'z':
			wnd.SetActivePanel(client.getPanel(0));
			
			break;
		case 'x':
			wnd.SetActivePanel(client.getPanel(2));
			break;
		}
		
		
	}
	
}
	
	
	
	
	
	

