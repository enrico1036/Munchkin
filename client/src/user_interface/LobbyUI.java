package user_interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.javafx.charts.ChartLayoutAnimator;

import client.MunchkinClient;
import network.GameEventHandler;

public class LobbyUI extends GamePanel implements ActionListener {

	private BufferedImage dragon, userImage;
	private int wndWidth, wndHeight;
	private JButton user_ready;
	private JLabel[][] users;
	private JTextField textBox;
	private String[] players;
	private boolean[] readyStatus;
	private ChatArea chatArea;

	public LobbyUI(GameWindow window, BufferedImage background) {

		super(window, background); // MunchkinClient.getImage("panel_background")
		dragon = MunchkinClient.getImage("dragon_lobby");
		userImage = MunchkinClient.getImage("users_lobby");

		users = new JLabel[6][2];
		instancePlayersMatrix();
		//setLayout(null);
		user_ready = new JButton("READY");
		user_ready.setActionCommand("tick");
		user_ready.addActionListener(this);
		add(user_ready);

		/*
		 * ScrollList = new ScrollableList(); ScrollList.setBounds(wndWidth * 3 / 5, wndHeight * 7 / 10, wndWidth * 2 / 5, wndHeight * 27 / 100); add(ScrollList);
		 * 
		 * textBox = new JTextField("Enter your text here"); textBox.addActionListener(this); textBox.setActionCommand("Enter"); textBox.setBounds(wndWidth * 3 / 5, wndHeight * 9 / 10, wndWidth * 2 / 5, wndHeight / 10); add(textBox);
		 */

		chatArea = new ChatArea(0.5f);
		chatArea.setBounds(wndWidth * 3 / 5, wndHeight * 7 / 10, wndWidth * 2 / 5, wndHeight * 27 / 100);
		chatArea.addActionListener(this);
		add(chatArea);

		GameEventHandler.getReadyPlayerList();
		
	}

	public final ChatArea getChatArea() {
		return chatArea;
	}

	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		
		g.drawImage(dragon, 0, 0, getWidth() * 2 / 5, getHeight(), null);
		g.drawImage(userImage, getWidth() * 3 / 5, 0, userImage.getWidth(), userImage.getHeight(), null);
	}

	private void updateComponents() {
		wndWidth = this.getWidth();
		wndHeight = this.getHeight();

		for (int i = 0; i < 6; i++) {
			for (int k = 0; k < 2; k++) {
				users[i][k].setBounds(wndWidth * (3) / 5 + wndWidth * k / 10, wndHeight * (i + 1) / 10, wndWidth / 10, wndHeight / 10);
			}

		}
		user_ready.setBounds(wndWidth * 3 / 5 + wndWidth / 5, wndHeight / 10, user_ready.getWidth(), user_ready.getHeight());
		chatArea.setBounds(wndWidth * 3 / 5, wndHeight * 7 / 10, wndWidth * 2 / 5, wndHeight * 27 / 100);
		// ScrollList.setBounds(wndWidth * 3 / 5, wndHeight * 7 / 10, wndWidth * 2 / 5, wndHeight * 27 / 100);
		// textBox.setBounds(wndWidth * 3 / 5, wndHeight * 9 / 10, wndWidth * 2 / 5, wndHeight / 10);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "tick") {
			GameEventHandler.setReadyStatus();

		} /*
			 * else if (e.getActionCommand() == "Enter" && textBox.getText().trim() != "") { GameEventHandler.sendChatMessage(GameEventHandler.getConnection() .getConnectedPlayerName(), textBox.getText()); textBox.setText(""); }
			 */

		else if (e.getActionCommand() == "Send") {
			String message = chatArea.getTextAndClear().trim();
			if (!message.isEmpty())
				GameEventHandler.sendChatMessage(GameEventHandler.getConnection().getConnectedPlayerName(), message);
		}

	}

	public void instancePlayersMatrix() {

		for (int i = 0; i < 6; i++) {
			users[i] = new JLabel[2];
			users[i][0] = new JLabel();
			users[i][1] = new JLabel();
			users[i][1].setText("Non Pronto");
			for (int k = 0; k < 2; k++) {
				users[i][k].setVisible(false);
				users[i][k].setBorder(BorderFactory.createLineBorder(Color.black));
				users[i][k].setBounds(wndWidth * (3) / 5 + wndWidth * k / 10, wndHeight * (i + 1) / 10, wndWidth / 10, wndHeight / 10);
				add(users[i][k]);
			}

		}

		users[0][0].setText(GameEventHandler.getConnection().getConnectedPlayerName());
		users[0][0].setVisible(true);
		users[0][1].setVisible(true);
	}

	public void showPlayer() {

		// Clear the list of Jlabels
		for (int i = 1; i < 6; i++) {
			users[i][0].setVisible(false);
			users[i][1].setVisible(false);
			users[i][0].setText("");
			users[i][1].setText("");
		}

		players = GameEventHandler.getPlayers();
		readyStatus = GameEventHandler.getReadyStatus();

		// Update status of connected players
		for (int i = 0; i < players.length; i++) {
			users[i][0].setVisible(true);
			users[i][1].setVisible(true);
			users[i][0].setText(players[i]);
			if (readyStatus[i] != true)
				users[i][1].setText("Non Pronto");
			else
				users[i][1].setText("Pronto");

		}

	}

}
