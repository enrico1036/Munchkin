package user_interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
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

public class LobbyUI extends GamePanel {

	private BufferedImage background, dragon, users;
	private int wndWidth, wndHeight;
	private JButton User_ready;
	private ScrollableList ScrollList;
	private JLabel[][] Users;
	private JTextField textBox;
	private String[] players;
	private boolean[] readyStatus;
	private ChatArea chatArea;

	public LobbyUI(GameWindow window) {

		super(window);
		background = MunchkinClient.getImage("panel_background");
		dragon = MunchkinClient.getImage("dragon_lobby");
		users = MunchkinClient.getImage("users_lobby");

		Users = new JLabel[6][2];
		instancePlayersMatrix();

		User_ready = new JButton("READY");
		User_ready.setActionCommand("tick");
		User_ready.addActionListener(this);
		add(User_ready);

		/*
		 * ScrollList = new ScrollableList(); ScrollList.setBounds(wndWidth * 3
		 * / 5, wndHeight * 7 / 10, wndWidth * 2 / 5, wndHeight * 27 / 100);
		 * add(ScrollList);
		 * 
		 * textBox = new JTextField("Enter your text here");
		 * textBox.addActionListener(this); textBox.setActionCommand("Enter");
		 * textBox.setBounds(wndWidth * 3 / 5, wndHeight * 9 / 10, wndWidth * 2
		 * / 5, wndHeight / 10); add(textBox);
		 */

		chatArea = new ChatArea(0.5f);
		//chatArea.setBounds(wndWidth * 3 / 5, wndHeight * 7 / 10, wndWidth * 2 / 5, wndHeight * 27 / 100);
		chatArea.addActionListener(this);
		add(chatArea);

		GameEventHandler.getReadyPlayerList();
	}

	public ScrollableList getScrollList() {
		return ScrollList;
	}
	
	public final ChatArea getChatArea(){
		return chatArea;
	}

	public void setScrollList(ScrollableList scrollList) {
		ScrollList = scrollList;
	}

	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(dragon, 0, 0, this.getWidth() * 2 / 5, this.getHeight(), null);
		g.drawImage(users, this.getWidth() * 3 / 5, 0, users.getWidth(), users.getHeight(), null);
	}

	private void updateComponents() {
		wndWidth = this.getWidth();
		wndHeight = this.getHeight();

		for (int i = 0; i < 6; i++) {
			for (int k = 0; k < 2; k++) {
				Users[i][k].setBounds(wndWidth * (3) / 5 + wndWidth * k / 10, wndHeight * (i + 1) / 10, wndWidth / 10, wndHeight / 10);
			}

		}
		User_ready.setBounds(wndWidth * 3 / 5 + wndWidth / 5, wndHeight / 10, User_ready.getWidth(), User_ready.getHeight());
		chatArea.setLocation(wndWidth * 3 / 5, wndHeight * 7 / 10);
		//ScrollList.setBounds(wndWidth * 3 / 5, wndHeight * 7 / 10, wndWidth * 2 / 5, wndHeight * 27 / 100);
		//textBox.setBounds(wndWidth * 3 / 5, wndHeight * 9 / 10, wndWidth * 2 / 5, wndHeight / 10);
	}

	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (e.getActionCommand() == "tick") {
			GameEventHandler.setReadyStatus();

		} /*
			 * else if (e.getActionCommand() == "Enter" &&
			 * textBox.getText().trim() != "") {
			 * GameEventHandler.sendChatMessage(GameEventHandler.getConnection()
			 * .getConnectedPlayerName(), textBox.getText());
			 * textBox.setText(""); }
			 */

		else if (e.getActionCommand() == "Send") {
			String message = chatArea.getTextAndClear().trim();
			if(!message.isEmpty())
				GameEventHandler.sendChatMessage(GameEventHandler.getConnection().getConnectedPlayerName(), message);
		}

	}

	public void instancePlayersMatrix() {

		for (int i = 0; i < 6; i++) {
			Users[i] = new JLabel[2];
			Users[i][0] = new JLabel();
			Users[i][1] = new JLabel();
			Users[i][1].setText("Non Pronto");
			for (int k = 0; k < 2; k++) {
				Users[i][k].setVisible(false);
				Users[i][k].setBorder(BorderFactory.createLineBorder(Color.black));
				Users[i][k].setBounds(wndWidth * (3) / 5 + wndWidth * k / 10, wndHeight * (i + 1) / 10, wndWidth / 10, wndHeight / 10);
				add(Users[i][k]);
			}

		}

		Users[0][0].setText(GameEventHandler.getConnection().getConnectedPlayerName());
		Users[0][0].setVisible(true);
		Users[0][1].setVisible(true);
	}

	public void showPlayer() {

		// Clear the list of Jlabels
		for (int i = 1; i < 6; i++) {
			Users[i][0].setVisible(false);
			Users[i][1].setVisible(false);
			Users[i][0].setText("");
			Users[i][1].setText("");
		}

		players = GameEventHandler.getPlayers();
		readyStatus = GameEventHandler.getReadyStatus();

		// Update status of connected players
		for (int i = 0; i < players.length; i++) {
			Users[i][0].setVisible(true);
			Users[i][1].setVisible(true);
			Users[i][0].setText(players[i]);
			if (readyStatus[i] != true)
				Users[i][1].setText("Non Pronto");
			else
				Users[i][1].setText("Pronto");

		}

	}

}
