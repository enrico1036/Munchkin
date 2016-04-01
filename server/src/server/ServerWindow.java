package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import game.GameManager;
import utils.Debug;

public class ServerWindow extends JFrame implements ActionListener{
	
	 boolean  maximized = false;
	 JPanel serverPane;
	 JLabel serverLabel,portLabel;
	 JButton buttonShutdown,buttonStart;
	 JTextField textPort;
	 MunchkinServer server;
	
	
     public  ServerWindow(MunchkinServer s) {

    	 server=s;
    	
    	
    	setTitle("Server Munchkin");

    	setSize(653,362);
    	
    	setResizable(false);
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		serverPane = new JPanel();
		serverPane.setBounds(0, 0,getWidth(),getHeight());
		serverPane.setBackground(Color.WHITE);

		serverLabel = new JLabel();
		serverLabel.setText("The server is now running");
		serverLabel.setHorizontalAlignment(JLabel.CENTER);
        
		buttonShutdown = new JButton("Shutdown");
		buttonShutdown.setActionCommand("Shutdown");
		buttonShutdown.addActionListener(this);
		
		buttonStart = new JButton("Start");
		buttonStart.setActionCommand("Start");
		buttonStart.addActionListener(this);
		
		textPort=new JTextField("port");
		textPort.setHorizontalAlignment(JLabel.CENTER);
		
		
		portLabel = new JLabel();
		portLabel.setText("The port selected is:");
		portLabel.setHorizontalAlignment(JLabel.CENTER);
		
		setContentPane(serverPane);
		
		GroupLayout gl_serverPane = new GroupLayout(serverPane);
		gl_serverPane.setHorizontalGroup(
			gl_serverPane.createParallelGroup(Alignment.LEADING)
				.addComponent(serverLabel, GroupLayout.PREFERRED_SIZE, 653, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_serverPane.createSequentialGroup()
					.addComponent(portLabel, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(textPort, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_serverPane.createSequentialGroup()
					.addGap(81)
					.addComponent(buttonShutdown, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(buttonStart, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
		);
		gl_serverPane.setVerticalGroup(
			gl_serverPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_serverPane.createSequentialGroup()
					.addComponent(serverLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(gl_serverPane.createParallelGroup(Alignment.LEADING)
						.addComponent(portLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPort, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_serverPane.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonShutdown, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonStart, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
		);
		serverPane.setLayout(gl_serverPane);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start")){
			
			String s= textPort.getText();
			
			if(isInteger(s)&&!(s.equals(""))){
				
				int port=Integer.parseInt(s);
					
				// Create and initialize server
				server = new MunchkinServer(port, 6, 1);
				Debug.print("Server started on port "+port);
				
				}
			
		}else if(e.getActionCommand().equals("Shutdown")){
			if(server!=null)
			// Exit
			server.shutdown();
		}
		
		
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}

}
