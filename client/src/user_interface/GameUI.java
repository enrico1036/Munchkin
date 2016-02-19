package user_interface;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


import client.MunchkinClient;

public class GameUI extends GamePanel{
	
	BufferedImage background=null;
	
	private  Game_Window wnd;
	public GameUI(Game_Window window) {
		wnd =window;
		
		try {
			this.background=ImageIO.read(new File("Resources/Images/option.png"));
			this.addKeyListener(this);
			
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
		case 'a':
			wnd.SetActivePanel((MunchkinClient.getPanel(0)));
			
			break;
		case 's':
			wnd.SetActivePanel(MunchkinClient.getPanel(2));
			break;
		}
		
		
	}
	
}
	
	
	
	
	
	

