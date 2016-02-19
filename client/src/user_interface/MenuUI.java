package user_interface;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuUI extends JPanel implements KeyListener{
	
	BufferedImage background=null;
	
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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		switch (e.getKeyChar())
		{
		case 'a':
			this.setContentPane(panels[0]);
			
			break;
		case 's':
			this.setContentPane(panels[2]);
			break;
		}
		this.revalidate();
		
	}
	
}
	
	
	
	
	
	

