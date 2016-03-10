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
import network.GameEventHandler;
import network.PlayerConnection;

public class MenuUI extends GamePanel{
	
	private BufferedImage background=null,connetti,exit;
	private ImageButton button,button2;
	private int wndWidth,wndHeight;
	
	
	public MenuUI(GameWindow window) {
		
		super(window);
		 Dimension buttdimmin = new Dimension(300,50);
		 		
		this.background = MunchkinClient.getImage("menu_background");
		this.setLayout(null);
		
		
		//Create an ImageIcone where we load the connect_button image
		
		connetti=MunchkinClient.getImage("connect_button");
				
		//Create a new button that allows you to connect to the server 
		button = new ImageButton(connetti);
		button.setActionCommand("connect");
		button.setMinimumSize(buttdimmin);
		button.setVisible(true);
		button.addActionListener(this);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		this.add(button);
		
		

		
		

		//Create an ImageIcone where we load the exit_button image
		
		exit=MunchkinClient.getImage("exit_button");
				
		//Create a new button that allows you to connect to the server 
		button2 = new ImageButton(exit);
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
			// Show connection dialog
			ConnectionDialog dialog = new ConnectionDialog();
			dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
			
			// Check dialog result
			if(dialog.positiveResult()){
				PlayerConnection connection = new PlayerConnection(
						dialog.getServerAddress(), 
						dialog.getPort(), 
						dialog.getPlayerName());
				
				// If connected successfully
				if(connection.isConnected()){
					GameEventHandler.initialize(connection);
					// Switch panel to Lobby*/
					window.SetActivePanel(MunchkinClient.getPanel(1));
				} else {
					JOptionPane.showMessageDialog(this, "Could not connect to the server");
				}
			}
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
		
		 button.setBounds(wndWidth/5,(wndHeight-button.getHeight())/3,wndWidth/5,	wndHeight/5);
		 button2.setBounds(wndWidth/5,((wndHeight-button.getHeight())/3)+wndHeight/5,wndWidth/5,wndHeight/5);
		
	}
	

	
}
	

	
	
	
	
	
	

