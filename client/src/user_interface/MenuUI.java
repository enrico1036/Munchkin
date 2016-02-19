package user_interface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;



import client.MunchkinClient;

public class MenuUI extends GamePanel{
	
	BufferedImage background=null;
	GameWindow wnd;
	JButton button,button1,button2;
	
	public MenuUI(GameWindow window) {
		 wnd=window;
		 		
		this.background = MunchkinClient.getImage("menu_background");
		this.setLayout(null);
		
		
		//Create an ImageIcone where we load the connect_button image
		ImageIcon connetti= new ImageIcon();
		connetti.setImage(MunchkinClient.getImage("connect_button"));
		
		//Create a new button that allows you to connect to the server 
		button = new JButton(connetti);
		button.setActionCommand("ciaone");
		button.setSize(100,50);
		button.setVisible(true);
		button.addActionListener(this);
		this.add(button);
		
		
		//Create a new button that allows you to connect to the server
		button1 = new JButton();
		button1.setText("Sto Bene");
		button1.setVisible(true);
		this.add(button1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand() == "ciaone"){
			System.exit(0);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background,0,0, this.getWidth(), this.getHeight(),null);
	}
	
	protected void updateComponents(){
		int wndWidth = this.getWidth();
		int wndHeight = this.getHeight();
		
		button.setBounds((wndWidth - button.getWidth())/2, 20,
				wndWidth/10, wndHeight/10);
	}
}
	

	
	
	
	
	
	

