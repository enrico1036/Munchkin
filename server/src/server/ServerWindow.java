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
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringBufferInputStream;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import game.GameManager;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultCaret;

import Debug.Debug;
import Debug.ErrorLogStream;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

public class ServerWindow extends JFrame implements ActionListener {
	private static final int WIDTH = 450;
	private static final int HEIGHT = 300;
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
	private JTextArea logTextArea;

	public static void main(String[] args) {
		ServerWindow wnd = new ServerWindow();
		wnd.setVisible(true);
	}

	public ServerWindow() {
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setTitle("Server Munchkin");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		serverPane = new JPanel();
		serverPane.setBounds(0, 0, getWidth(), getHeight());
		setContentPane(serverPane);

		buttonShutdown = new JButton("Shutdown");
		buttonShutdown.setEnabled(false);
		buttonShutdown.setActionCommand("Shutdown");
		buttonShutdown.addActionListener(this);
		
		serverLabel = new JLabel();
		serverLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		serverLabel.setText(NOT_RUNNING_MESSAGE);
		serverLabel.setHorizontalAlignment(JLabel.CENTER);

		buttonStart = new JButton("Start");
		buttonStart.setActionCommand("Start");
		buttonStart.addActionListener(this);

		portSpinner = new JSpinner();
		portSpinner.setModel(new SpinnerNumberModel(35267, 1024, 65535, 1));
		portSpinner.setVisible(true);

		lblPortNumber = new JLabel("Port number:");
		
		JScrollPane scrollPane = new JScrollPane();
		logTextArea = new JTextArea();
		logTextArea.setTabSize(4);
		logTextArea.setWrapStyleWord(true);
		logTextArea.setText("");
		logTextArea.setLineWrap(true);
		logTextArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret)logTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(logTextArea);
		
		GroupLayout gl_serverPane = new GroupLayout(serverPane);
		gl_serverPane.setHorizontalGroup(
			gl_serverPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_serverPane.createSequentialGroup()
					.addGap(5)
					.addComponent(serverLabel, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
					.addGap(5))
				.addGroup(gl_serverPane.createSequentialGroup()
					.addGap(10)
					.addComponent(lblPortNumber, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(portSpinner, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(buttonStart, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonShutdown, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_serverPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_serverPane.setVerticalGroup(
			gl_serverPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_serverPane.createSequentialGroup()
					.addGap(5)
					.addComponent(serverLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_serverPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPortNumber, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(portSpinner, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonShutdown, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonStart, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
					.addContainerGap())
		);
		serverPane.setLayout(gl_serverPane);

		Debug.setOutputDestination(logTextArea);
//		System.setErr(new PrintStream(new ErrorLogStream(), true));
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
					logTextArea.setText("");
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
//						ByteArrayOutputStream baos = new ByteArrayOutputStream();
//						PrintStream myOutputStream = new PrintStream(baos);
//						e.printStackTrace(myOutputStream);
//						Debug.err(new String(baos.toByteArray()));
						e.printStackTrace();
					}

					// Disable start button and enable shutdown button
					buttonShutdown.setEnabled(false);
					buttonStart.setEnabled(true);
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
