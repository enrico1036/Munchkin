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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import game.GameManager;
import utils.Debug;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Font;

public class ServerWindow extends JFrame implements ActionListener {
	private static final int WIDTH = 300;
	private static final int HEIGHT = 350;
	private static final String NOT_RUNNING_MESSAGE = "Server not running";
	private static final String RUNNNING_MESSAGE = "Server running";

	boolean maximized = false;
	JPanel serverPane;
	JLabel serverLabel;
	JButton buttonShutdown, buttonStart;
	JSpinner portSpinner;
	MunchkinServer server;
	Thread runThread;
	JLabel lblPortNumber;

	public static void main(String[] args) {
		ServerWindow wnd = new ServerWindow();
		wnd.setVisible(true);
	}

	public ServerWindow() {
		setTitle("Server Munchkin");

		setSize(WIDTH, HEIGHT);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		serverPane = new JPanel();
		serverPane.setBounds(0, 0, getWidth(), getHeight());
		serverPane.setLayout(null);
		setContentPane(serverPane);

		buttonShutdown = new JButton("Shutdown");
		buttonShutdown.setBounds(184, 67, 100, 30);
		buttonShutdown.setActionCommand("Shutdown");
		buttonShutdown.addActionListener(this);
		serverPane.add(buttonShutdown);
		

		serverLabel = new JLabel();
		serverLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		serverLabel.setText(NOT_RUNNING_MESSAGE);
		serverLabel.setBounds(5, 5, 290, 20);
		serverLabel.setHorizontalAlignment(JLabel.CENTER);
		serverPane.add(serverLabel);

		buttonStart = new JButton("Start");
		buttonStart.setActionCommand("Start");
		buttonStart.setBounds(10, 67, 100, 30);
		buttonStart.addActionListener(this);
		serverPane.add(buttonStart);

		portSpinner = new JSpinner();
		portSpinner.setModel(new SpinnerNumberModel(35267, 1024, 65535, 1));
		portSpinner.setBounds(118, 36, 166, 20);
		portSpinner.setVisible(true);
		serverPane.add(portSpinner);

		lblPortNumber = new JLabel("Port number:");
		lblPortNumber.setBounds(10, 39, 74, 14);
		serverPane.add(lblPortNumber);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {

			int port = (int) portSpinner.getValue();
			
			// Asyncronously start a new server
			runThread = new Thread(new Runnable() {

				@Override
				public void run() {
					// Disable start button and enable shutdown button
					serverLabel.setText(ServerWindow.RUNNNING_MESSAGE);
					buttonShutdown.setEnabled(true);
					buttonStart.setEnabled(false);
					portSpinner.setEnabled(false);
					
					// Run game
					try {
						server = new MunchkinServer(port, 6, 1);
						server.populateLobby();
						GameManager.startGame();
						server.shutdown();
					} catch (Exception e) {
						e.printStackTrace();
					}

					// Disable start button and enable shutdown button
					buttonShutdown.setEnabled(true);
					buttonStart.setEnabled(false);
					portSpinner.setEnabled(true);
					serverLabel.setText(ServerWindow.NOT_RUNNING_MESSAGE);
				}
			});
			
			runThread.start();

			

		} else if (e.getActionCommand().equals("Shutdown")) {
			if (server != null) {
				server.shutdown();
				runThread.interrupt();

				buttonShutdown.setEnabled(false);
				buttonStart.setEnabled(true);
				portSpinner.setEnabled(true);
				serverLabel.setText(ServerWindow.NOT_RUNNING_MESSAGE);
			}
		}

	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
}
