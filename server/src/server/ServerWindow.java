package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerWindow extends JFrame{
	
	boolean maximized = false;
	JPanel serverPane;
	JLabel serverLabel,portLabel;
	JButton buttonShutdown,buttonStart;
	JTextField textPort;
	
	
     public ServerWindow(String port) {

    	setTitle("Server Munchkin");

    	setSize(300,180);
    	
     	setResizable(false);
     	setVisible(true);
        
		serverPane = new JPanel();
		serverPane.setBounds(0, 0, this.getWidth(), this.getHeight()-20);
		serverPane.setBackground(Color.WHITE);
		serverPane.setLayout(null);

		serverLabel = new JLabel();
		serverLabel.setText("The server is now running");
		serverLabel.setHorizontalAlignment(JLabel.CENTER);
		serverLabel.setBounds(0, 0, serverPane.getWidth(), serverPane.getHeight()/8);
		serverPane.add(serverLabel);

    	int Dimx = serverPane.getWidth()/2;
    	int Dimy = serverPane.getHeight()/5;
        
		buttonShutdown = new JButton("Shutdown");
		buttonShutdown.setSize(Dimx, Dimy);
		buttonShutdown.setLocation(0,serverPane.getHeight()-Dimy);
		serverPane.add(buttonShutdown);
		
		buttonStart = new JButton("Start");
		buttonStart.setSize(Dimx, Dimy);
		buttonStart.setLocation(serverPane.getWidth()-Dimx,serverPane.getHeight()-Dimy);
		serverPane.add(buttonStart);
		
		textPort=new JTextField(port);
		textPort.setSize(Dimx, Dimy);
		textPort.setLocation(serverPane.getWidth()/2,serverPane.getHeight()/2);
		textPort.setHorizontalAlignment(JLabel.CENTER);
		textPort.setEditable(false);
		serverPane.add(textPort);
		
		portLabel = new JLabel();
		portLabel.setText("The port selected is:");
		portLabel.setSize(Dimx, Dimy);
		portLabel.setLocation(0,serverPane.getHeight()/2);
		portLabel.setHorizontalAlignment(JLabel.CENTER);
		serverPane.add(portLabel);
		
		setContentPane(serverPane);		
    }

}
