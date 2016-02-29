package user_interface;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import client.MunchkinClient;

public class MenuUI extends GamePanel{
	
	private BufferedImage background=null;
	private ImageIcon connetti,option,exit;
	private JButton button,button1,button2;
	private int wndWidth,wndHeight;
	
	
	public MenuUI(GameWindow window) {
		
		super(window);
		 Dimension buttdimmin = new Dimension(300,50);
		 		
		this.background = MunchkinClient.getImage("menu_background");
		this.setLayout(null);
		
		
		//Create an ImageIcone where we load the connect_button image
		connetti= new ImageIcon();
		connetti.setImage(MunchkinClient.getImage("connect_button"));
				
		//Create a new button that allows you to connect to the server 
		button = new JButton(connetti);
		button.setActionCommand("connect");
		button.setMinimumSize(buttdimmin);
		button.setVisible(true);
		button.addActionListener(this);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		this.add(button);
		
		

		//Create an ImageIcone where we load the option_button image
		option= new ImageIcon();
		option.setImage(MunchkinClient.getImage("option_button"));
				
		//Create a new button that allows you to go on the option page 
		button1 = new JButton(option);
		button1.setActionCommand("option");
		button1.setMinimumSize(buttdimmin);
		button1.setVisible(true);
		button1.addActionListener(this);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		this.add(button1);
		

		//Create an ImageIcone where we load the exit_button image
		exit= new ImageIcon();
		exit.setImage(MunchkinClient.getImage("exit_button"));
				
		//Create a new button that allows you to connect to the server 
		button2 = new JButton(exit);
		button2.setActionCommand("exit");
		button2.setMinimumSize(buttdimmin);
		button2.setVisible(true);
		button2.addActionListener(this);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		this.add(button2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		switch(e.getActionCommand()){
		case "connect":
		/*	// Show connection dialog
			ConnectionDialog dialog = new ConnectionDialog();
			dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
			
			// Check dialog result
			if(dialog.positiveResult()){
				boolean result = MunchkinClient.connection.establish(
						dialog.getServerAddress(), 
						dialog.getPort(), 
						dialog.getPlayerName());
				
				// If connected successfully
				if(result){
					// Switch panel to Lobby*/
					window.SetActivePanel(MunchkinClient.getPanel(1));
				/*} else {
					JOptionPane.showMessageDialog(this, "Could not connect to the server");
				}
			}*/
			break;
		case "option":
			window.SetActivePanel(MunchkinClient.getPanel(4));
			break;
		case "exit":
			System.exit(0);
			break;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background,0,0, this.getWidth(), this.getHeight(),null);
		
	}

	
	private void updateComponents(){
		 wndWidth = this.getWidth();
		 wndHeight = this.getHeight();
		
		 this.resizeButton(button,connetti,wndWidth/5,(wndHeight-button.getHeight())/3,wndWidth/5,	wndHeight/5);
		 this.resizeButton(button1,option,wndWidth/5,((wndHeight-button.getHeight())/3)+wndHeight*2/12,wndWidth/5,wndHeight/5);
		 this.resizeButton(button2,exit,wndWidth/5,((wndHeight-button.getHeight())/3)+wndHeight*4/12,wndWidth/5,wndHeight/5);
		
	}
	
	
	public void resizeButton(JButton btn,ImageIcon image,int newX,int newY,int newWidth,int newHeight){
		 Image img = image.getImage();
		   Image newimg = img.getScaledInstance(newWidth,newHeight, java.awt.Image.SCALE_SMOOTH ) ;  
		   ImageIcon x = new ImageIcon(newimg);
		   btn.setIcon(x);
		   btn.setBounds(newX,newY,newWidth,newHeight);
		  
		   
	}

	
}
	

	
	
	
	
	
	

