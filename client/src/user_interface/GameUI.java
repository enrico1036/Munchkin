package user_interface;

import javax.swing.JPanel;

import cards.ClassCard;
import cards.EquipSlot;
import cards.Equipment;
import cards.Race;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import client.MunchkinClient;
import javafx.print.PageLayout;
import network.GameEventHandler;
import network.message.client.SelectedCardMessage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

public class GameUI extends GamePanel {

	private BufferedImage background, framePlayerStats, doorCardsImage, DiscardsImage, TreasureCardsImage, head, hand1,
			body, hand2, feet;
	private HashMap<String,PlayerPanel> OpponentPlayers;

	private HandManager HandCards;

	// ----------Table JComponents
	private ImageButton lblDrawnCard;

	// --------Player 1 JComponents---------
	private JLabel lblPlayerName, lblPlayerLevel, lblPlayernumcard, lblPlayerPower, lblPlayerPowerValue,
			lblPlayerLevelValue;

	private ClientCard PlayerHead, PlayerHand1, PlayerBody, PlayerHand2, PlayerFeet, PlayerRace, PlayerClass;

	// ----------Deck JComponents---------------
	private ImageButton doorCards, Discards, treasureCards;

	// -----------Dice JComponents-------------
	private JLabel diceLabel;
	/*
	 * ww= Gamewindow (JFrame) width wh= Gamewindow (JFrame) height psw=
	 * PlayerStats (JPanel) width psh= PlayerStats (JPanel) height dw= Deck
	 * (JPanel) width dh= Deck (JPanel) height
	 */
	private int ww, wh, psw, psh, dw, dh;
	// There are all the JPanels in GameUI
	private JPanel Hand, Decks, Table;
	private PaintPanel PlayerStats;
	private DiceManager Dice;
	private ZoomedPanel zp;

	/**
	 * Create the panel.
	 */
	public GameUI(GameWindow window) {
		super(window);

		// Create the gameUI ZoomedPanel
		zp = new ZoomedPanel();
		GameEventHandler.getReadyPlayerList();
		OpponentPlayers = new HashMap<String,PlayerPanel>();
		this.createRandomFramePanel();

		background = MunchkinClient.getImage("panel_background");
		framePlayerStats = MunchkinClient.getImage("playerstats_frame");

		setOpaque(false);
		setLayout(null);

		ww = window.getContentPane().getWidth();
		wh = window.getContentPane().getHeight();

		/*
		 * 
		 * 
		 * 
		 * DICE PANEL AND ITS COMPONENTS
		 * 
		 * 
		 * 
		 * 
		 */

		Dice = new DiceManager(window);
		Dice.setOpaque(false);
		Dice.setBounds(ww * 2 / 5, wh * 2 / 3, ww * 3 / 5, wh / 3);
		Dice.setVisible(false);
		add(Dice);
		Dice.setLayout(null);

		/*
		 * 
		 * 
		 * 
		 * HAND PANEL AND ITS COMPONENTS
		 * 
		 * 
		 * 
		 * 
		 */

		Hand = new JPanel();
		Hand.setOpaque(false);
		Hand.setBounds(ww * 2 / 5, wh * 2 / 3, ww * 3 / 5, wh / 3);
		add(Hand);
		Hand.setLayout(null);

		/*
		 * 
		 * ZOOMED PANEL AND ITS COMPONENTS
		 * 
		 */

		HandCards = new HandManager(Hand);

		zp.setVisible(true);
		zp.setBounds(ww / 3, wh / 20, ww / 3, wh * 5 / 8);
		add(zp);
		zp.setLayout(null);

		setComponentZOrder(zp, 0);

		/*
		 * 
		 * 
		 * 
		 * PLAYERSTATS PANEL AND ITS COMPONENTS
		 * 
		 * 
		 * 
		 */

		PlayerStats = new PaintPanel(framePlayerStats);
		PlayerStats.setOpaque(false);
		PlayerStats.setBounds(0, wh * 2 / 3, ww * 2 / 5, wh / 3);
		add(PlayerStats);
		PlayerStats.setLayout(null);

		psw = PlayerStats.getWidth();
		psh = PlayerStats.getHeight();

		lblPlayerName = new JLabel(GameEventHandler.getConnection().getConnectedPlayerName());
		lblPlayerName.setBounds(psw / 16, 0, psw * 7 / 16, psh / 3);
		PlayerStats.add(lblPlayerName);

		lblPlayerLevel = new JLabel("Level");
		lblPlayerLevel.setBounds(psw / 2, 0, psw / 4, psh / 6);
		PlayerStats.add(lblPlayerLevel);

		lblPlayerLevelValue = new JLabel(String.valueOf(1));
		lblPlayerLevelValue.setBounds(psw * 3 / 4, 0, psw / 4, psh / 6);
		PlayerStats.add(lblPlayerLevelValue);

		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setBounds(psw / 2, psh / 6, psw / 4, psh / 6);
		PlayerStats.add(lblPlayerPower);

		lblPlayerPowerValue = new JLabel(String.valueOf(0));
		lblPlayerPowerValue.setBounds(psw * 3 / 4, psh / 6, psw / 4, psh / 6);
		PlayerStats.add(lblPlayerPowerValue);

		PlayerRace = new ClientCard("Race");
		PlayerRace.setBounds(psw / 8, psh / 3, psw / 8, psh / 4);
		PlayerStats.add(PlayerRace);

		PlayerClass = new ClientCard("Class");
		PlayerClass.setBounds(psw / 4, psh / 3, psw / 8, psh / 4);
		PlayerStats.add(PlayerClass);

		lblPlayernumcard = new JLabel(String.valueOf(0));
		lblPlayernumcard.setBounds(psw * 3 / 8, psh / 3, psw / 8, psh / 4);
		PlayerStats.add(lblPlayernumcard);

		head = MunchkinClient.getImage("player_head");
		hand1 = MunchkinClient.getImage("player_hand1");
		body = MunchkinClient.getImage("player_body");
		hand2 = MunchkinClient.getImage("player_hand2");
		feet = MunchkinClient.getImage("player_feet");

		PlayerHead = new ClientCard("head");
		PlayerHead.setBounds(psw * 2 / 3, psh / 3, psw / 6, psh * 2 / 9);
		PlayerHead.CreateCard(head, zp);
		PlayerStats.add(PlayerHead);

		PlayerHand1 = new ClientCard("hand1");
		PlayerHand1.setBounds(psw * 7 / 12, psh * 5 / 9, psw / 12, psh * 2 / 9);
		PlayerHand1.CreateCard(hand1, zp);
		PlayerStats.add(PlayerHand1);

		PlayerBody = new ClientCard("body");
		PlayerBody.setBounds(psw * 2 / 3, psh * 5 / 9, psw / 6, psh * 2 / 9);
		PlayerBody.CreateCard(body, zp);
		PlayerStats.add(PlayerBody);

		PlayerHand2 = new ClientCard("hand2");
		PlayerHand2.setBounds(psw * 5 / 6, psh * 5 / 9, psw / 12, psh * 2 / 9);
		PlayerHand2.CreateCard(hand2, zp);
		PlayerStats.add(PlayerHand2);

		PlayerFeet = new ClientCard("feet");
		PlayerFeet.setBounds(psw * 2 / 3, psh * 7 / 9, psw / 6, psh * 2 / 9);
		PlayerFeet.CreateCard(feet, zp);
		PlayerStats.add(PlayerFeet);

		/*
		 * 
		 * 
		 * 
		 * OPPONENT PLAYER JPANEL
		 * 
		 * 
		 * 
		 */

		int k=0;
		// k=uguale alla richiesta di quanti utenti ci sono
		for (String player: OpponentPlayers.keySet()) {
			if (OpponentPlayers.get(player) != null) {
				OpponentPlayers.get(player).setOpaque(false);
				OpponentPlayers.get(player).setBounds(ww * k / 5, 0, ww / 5, wh / 3);
				add(OpponentPlayers.get(player));
				OpponentPlayers.get(player).setLayout(null);
				k++;
			}
		}

		/*
		 * 
		 * DECKS PANEL AND ITS COMPONENTS
		 * 
		 */

		doorCardsImage = MunchkinClient.getImage("door_back");

		DiscardsImage = MunchkinClient.getImage("door_card");

		TreasureCardsImage = MunchkinClient.getImage("treasure_back");

		Decks = new JPanel();
		Decks.setOpaque(false);
		Decks.setBounds(ww / 2, wh / 3, ww / 2, wh / 3);
		Decks.setVisible(true);
		Decks.setLayout(null);
		add(Decks);

		dw = Decks.getWidth();
		dh = Decks.getHeight();

		doorCards = new ImageButton(doorCardsImage);
		doorCards.setBounds(dw / 25, dh / 10, dw / 5, dh * 8 / 10);
		doorCards.setActionCommand("DrawDoor");
		doorCards.addActionListener(this);
		doorCards.setVisible(true);
		Decks.add(doorCards);

		Discards = new ImageButton(DiscardsImage);
		Discards.setBounds(dw * 7 / 25, dh / 10, dw / 5, dh * 8 / 10);
		Discards.setVisible(true);
		Decks.add(Discards);

		treasureCards = new ImageButton(TreasureCardsImage);
		treasureCards.setBounds(dw * 13 / 25, dh / 10, dw / 5, dh * 8 / 10);
		treasureCards.addActionListener(this);
		treasureCards.setVisible(true);
		Decks.add(treasureCards);

		/*
		 * 
		 * TABLE PANEL AND ITS COMPONENTS
		 * 
		 */

		Table = new JPanel();
		Table.setOpaque(false);
		Table.setBounds(0, wh / 3, ww * 3 / 4, wh / 3);
		add(Table);
		Table.setLayout(null);

		JLabel lblMonster = new JLabel("Monster");
		lblMonster.setBounds(10, 26, 82, 85);
		Table.add(lblMonster);

		lblDrawnCard = new ImageButton(null);
		lblDrawnCard.setBounds(136, 25, 155, 86);
		Table.add(lblDrawnCard);

	}

	private void updateComponents() {

		ww = this.getWidth();
		wh = this.getHeight();

		// Resize automatically all JPanels on GameUI instance

		PlayerStats.setBounds(0, wh * 2 / 3, ww * 2 / 5, wh / 3);
		Hand.setBounds(ww * 2 / 5, wh * 2 / 3, ww * 3 / 5, wh / 3);
		Decks.setBounds(ww / 2, wh / 3, ww / 2, wh / 3);
		Table.setBounds(0, wh / 3, ww * 3 / 4, wh / 3);
		zp.setBounds(ww / 3, wh / 20, ww / 3, wh * 5 / 8);

		int k=0;
		for (String player: OpponentPlayers.keySet()) {
			OpponentPlayers.get(player).setBounds(ww * k / 5, 0, ww / 5, wh / 3);
			OpponentPlayers.get(player).updatePlayerComponents();
			k++;
		}

		// Get the width and the height of PlayerStats JPanel

		psw = PlayerStats.getWidth();
		psh = PlayerStats.getHeight();

		// -------Resize all JComponent of PlayerStats Panel-----------

		lblPlayerName.setBounds(psw / 16, 0, psw * 7 / 16, psh / 3);
		lblPlayerLevel.setBounds(psw / 2, 0, psw / 4, psh / 6);
		lblPlayerLevelValue.setBounds(psw * 3 / 4, 0, psw / 4, psh / 6);
		lblPlayerPower.setBounds(psw / 2, psh / 6, psw / 4, psh / 6);
		lblPlayerPowerValue.setBounds(psw * 3 / 4, psh / 6, psw / 4, psh / 6);
		PlayerRace.setBounds(psw / 8, psh / 3, psw / 8, psh / 4);
		PlayerClass.setBounds(psw / 4, psh / 3, psw / 8, psh / 4);
		lblPlayernumcard.setBounds(psw * 3 / 8, psh / 3, psw / 8, psh / 4);
		PlayerHead.setBounds(psw * 2 / 3, psh / 3, psw / 6, psh * 2 / 9);
		PlayerHand1.setBounds(psw / 2, psh * 5 / 9, psw / 6, psh * 2 / 9);
		PlayerBody.setBounds(psw * 2 / 3, psh * 5 / 9, psw / 6, psh * 2 / 9);
		PlayerHand2.setBounds(psw * 5 / 6, psh * 5 / 9, psw / 6, psh * 2 / 9);
		PlayerFeet.setBounds(psw * 2 / 3, psh * 7 / 9, psw / 6, psh * 2 / 9);

		dw = Decks.getWidth();
		dh = Decks.getHeight();

		// Get the width and the height of Player* JPanel (* stands for
		// 2,3,4,5,6).

		doorCards.setBounds(dw / 25, dh / 10, dw / 5, dh * 8 / 10);
		Discards.setBounds(dw * 7 / 25, dh / 10, dw / 5, dh * 8 / 10);
		treasureCards.setBounds(dw * 13 / 25, dh / 10, dw / 5, dh * 8 / 10);

		HandCards.handPositioning();

	}

	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);

		if (e.getActionCommand().equals("DrawDoor")) {

			GameEventHandler.selectCard(SelectedCardMessage.DOOR_DECK);

		}
	}

	public void changeEquipment(Equipment head, Equipment hand1, Equipment hand2, Equipment body, Equipment feet) {
		if (head != null)
			PlayerHead.setImage(MunchkinClient.getImage(head.getTitle()));
		if (hand1 != null)
			PlayerHand1.setImage(MunchkinClient.getImage(hand1.getTitle()));
		if (hand2 != null)
			PlayerHand2.setImage(MunchkinClient.getImage(hand2.getTitle()));
		if (body != null)
			PlayerBody.setImage(MunchkinClient.getImage(body.getTitle()));
		if (feet != null)
			PlayerFeet.setImage(MunchkinClient.getImage(feet.getTitle()));
	}

	private void createRandomFramePanel() {
		int size = 10, k = 0;
		int[] x = new int[GameEventHandler.getPlayers().length - 1];

		ArrayList<Integer> list = new ArrayList<Integer>(size);
		for (int i = 1; i <= size; i++) {
			list.add(i);
		}

		Random rand = new Random();
		while (list.size() > 0 && k < GameEventHandler.getPlayers().length - 1) {
			int index = rand.nextInt(list.size());
			x[k] = list.remove(index);
			k++;
		}

		int j=0;
		for (k = 0; k < GameEventHandler.getPlayers().length; k++) {
			if (!(GameEventHandler.getPlayers()[k].equals(GameEventHandler.getConnection().getConnectedPlayerName()))) {

				OpponentPlayers.put(GameEventHandler.getPlayers()[k], new PlayerPanel(MunchkinClient.getImage("frameplayer" + x[j]),
						GameEventHandler.getPlayers()[k], window, zp));
				j++;
			}
		}
	}

	public HashMap<String,PlayerPanel> getOpponentPlayers() {
		return OpponentPlayers;
	}

	public HandManager getHandCards() {
		return HandCards;
	}

	public ImageButton getDrawnCard() {
		return lblDrawnCard;
	}

	public DiceManager getDiceManager() {
		return Dice;
	}

	public void changeStatistics(int levelValue, int powerValue, String newClass, String newRace, int numCard) {

		// update the player level and power value
		lblPlayerLevelValue.setText(String.valueOf(levelValue));
		lblPlayerPowerValue.setText(String.valueOf(powerValue));

		// update the player race
		BufferedImage image = MunchkinClient.getImage(newRace);

		if (PlayerRace.imageIsNull())
			PlayerRace.CreateCard(image, zp);
		else
			PlayerRace.setImage(image);

		// update the player class
		image = MunchkinClient.getImage(newClass);

		if (PlayerClass.imageIsNull())
			PlayerClass.CreateCard(image, zp);
		else
			PlayerClass.setImage(image);

		// update the number of the player hand
		lblPlayernumcard.setText(String.valueOf(numCard));

	}

	public ImageButton getDiscards() {
		return Discards;
	}

}
