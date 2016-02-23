package user_interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import client.MunchkinClient;

import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;

public class GameUI extends GamePanel implements ComponentListener {
	
	private ImageIcon showPlayerCards;
	private BufferedImage background;
	private Border loweredbevel;
	
	//--------Player 1 JComonents---------
	private JLabel lblPlayerName,lblPlayerLevel,lblPlayerRace,lblPlayerClass,
	lblPlayerSex,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue,
	lblPlayerHead,lblPlayerRiarm,lblPlayerBody,lblPlayerLearm,lblPlayerLegs;
	
	
	//---------Player 2 JComponents------------
	private JLabel lblPlayer2Name,lblPlayer2Level,lblPlayer2Race,lblPlayer2Class,
	lblPlayer2Cards,lblPlayer2Power,lblPlayer2PowerValue,lblPlayer2LevelValue;
	private JButton btnPlayer2Cards;
	
	//---------Player 3 JComponents------------
	private JLabel lblPlayer3Name,lblPlayer3Level,lblPlayer3Race,lblPlayer3Class,
	lblPlayer3Cards,lblPlayer3Power,lblPlayer3PowerValue,lblPlayer3LevelValue;
	private JButton btnPlayer3Cards;
		
	//---------Player 4 JComponents------------
	private JLabel lblPlayer4Name,lblPlayer4Level,lblPlayer4Race,lblPlayer4Class,
	lblPlayer4Cards,lblPlayer4Power,lblPlayer4PowerValue,lblPlayer4LevelValue;
	private JButton btnPlayer4Cards;
		
	//---------Player 5 JComponents------------
	private JLabel lblPlayer5Name,lblPlayer5Level,lblPlayer5Race,lblPlayer5Class,
	lblPlayer5Cards,lblPlayer5Power,lblPlayer5PowerValue,lblPlayer5LevelValue;
	private JButton btnPlayer5Cards;
				
	//---------Player 6 JComponents------------
	private JLabel lblPlayer6Name,lblPlayer6Level,lblPlayer6Race,lblPlayer6Class,
	lblPlayer6Cards,lblPlayer6Power,lblPlayer6PowerValue,lblPlayer6LevelValue;
	private JButton btnPlayer6Cards;
	
	/*
	 * ww= Gamewindow (JFrame) width
	 * wh= Gamewindow (JFrame) height
	 * pw= Player* (JPanel) width
	 * ph= Player* (JPanel) height
	 * psw= PlayerStats (JPanel) width
	 * psh= PlayerStats (JPanel) height
	 */
	private int ww,wh,pw,ph,psw,psh;
	
	//There are all the JPanels in GameUI
	private JPanel PlayerStats,hand,Player2,Player3,
	Player4,Player5,Player6,Decks,Table,ZoomedPanel;
	

	/**
	 * Create the panel.
	 */
	public GameUI(GameWindow window)  {
		super(window);
		
		this.addComponentListener(this);

		
		this.background=MunchkinClient.getImage("lobby_background");
		this.setOpaque(false);
		this.setLayout(null);
		
		
		ww=window.getContentPane().getWidth();
		wh=window.getContentPane().getHeight();
		

		
		showPlayerCards = new ImageIcon();
		showPlayerCards.setImage(MunchkinClient.getImage("cards"));
		
	
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		
		
		/*
		 * 
		 * 
		 * 
		 * PLAYERSTATS PANEL AND ITS COMPONENTS
		 * 
		 * 
		 * 
		 */
		
		PlayerStats = new JPanel();
		PlayerStats.setOpaque(false);
		PlayerStats.setBounds(0, wh*2/3, ww*2/5, wh/3);
		PlayerStats.setBorder(loweredbevel);
		add(PlayerStats);
		PlayerStats.setLayout(null);
		
		psw=PlayerStats.getWidth();
		psh=PlayerStats.getHeight();
		
		lblPlayerName = new JLabel("PlayerName");
		lblPlayerName.setBounds(0, 0, psw/2, psh/3);
		PlayerStats.add(lblPlayerName);

		lblPlayerLevel = new JLabel("Level");
		lblPlayerLevel.setBounds(psw/2,0, psw/4,psh/6);
		PlayerStats.add(lblPlayerLevel);

		 lblPlayerLevelValue = new JLabel("Level Value");
		lblPlayerLevelValue.setBounds(psw*3/4,0, psw/4, psh/6);
		PlayerStats.add(lblPlayerLevelValue);
		
		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setBounds(psw/2, psh/6, psw/4, psh/6);
		PlayerStats.add(lblPlayerPower);

		 lblPlayerPowerValue = new JLabel("Power Value");
		 lblPlayerPowerValue.setBounds(psw*3/4, psh/6, psw/4, psh/6);
		PlayerStats.add(lblPlayerPowerValue);
	
		 lblPlayerRace = new JLabel("Race");
		lblPlayerRace.setBounds(0,psh/3, psw/6, psh/3);
		PlayerStats.add(lblPlayerRace);
		
		 lblPlayerClass = new JLabel("Class");
		lblPlayerClass.setBounds(psw/6, psh/3, psw/6, psh/3);
		PlayerStats.add(lblPlayerClass);
		
		 lblPlayerSex = new JLabel("Sex");
		lblPlayerSex.setBounds(psw/3, psh/3, psw/6, psh/3);
		PlayerStats.add(lblPlayerSex);
		
		
		//BufferedImage img = MunchkinClient.getImage("player_head");
		
		 lblPlayerHead = new JLabel("Head");
		 lblPlayerHead.setBounds(psw*2/3, psh/3, psw/6, psh*2/9);
		/* Image dimg = img.getScaledInstance(lblPlayerHead.getWidth(), lblPlayerHead.getHeight(),
			        Image.SCALE_SMOOTH);
		 ImageIcon head= new ImageIcon(dimg);
		 lblPlayerHead.setIcon(head);*/
		 PlayerStats.add(lblPlayerHead);
		
		
		
		 lblPlayerRiarm = new JLabel("RiArm");
		 lblPlayerRiarm.setBounds(psw/2, psh*5/9, psw/6, psh*2/9);
		PlayerStats.add(lblPlayerRiarm);
		
		 lblPlayerBody = new JLabel("Body");
		 lblPlayerBody.setBounds(psw*2/3, psh*5/9, psw/6, psh*2/9);
		PlayerStats.add(lblPlayerBody);
		
		 lblPlayerLearm = new JLabel("LeArm");
		 lblPlayerLearm.setBounds(psw*5/6, psh*5/9,psw/6, psh*2/9);
		PlayerStats.add(lblPlayerLearm);
		
		 lblPlayerLegs = new JLabel("Legs");
		 lblPlayerLegs.setBounds(psw*2/3, psh*7/9, psw/6, psh*2/9);
		 PlayerStats.add(lblPlayerLegs);
		
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
		
		
		hand = new JPanel();
		hand.setOpaque(false);
		hand.setBounds(ww*2/5, wh*2/3, ww*3/5, wh/3);
		add(hand);
		hand.setLayout(null);
		
		JLabel lblHand = new JLabel("hand");
		lblHand.setBounds(151, 48, 34, 24);
		lblHand.setHorizontalTextPosition(SwingConstants.CENTER);
		hand.add(lblHand);
		
		
		/*
		 * 
		 * 
		 * 
		 * PLAYER 2 PANEL AND ITS COMPONENTS
		 * 
		 * 
		 * 
		 */
		
		
		Player2 = new JPanel();
		Player2.setOpaque(false);
		Player2.setBounds(0, 0, ww/5, wh/3);
		Player2.setBorder(loweredbevel);
		add(Player2);
		Player2.setLayout(null);
		
		pw=Player2.getWidth();
		ph=Player2.getHeight();
		
		lblPlayer2Name = new JLabel("PlayerName");
		lblPlayer2Name.setBounds(0, 0,pw/2, ph/2);
		Player2.add(lblPlayer2Name);

		lblPlayer2Level = new JLabel("Level");
		lblPlayer2Level.setBounds(pw/2,0, pw/4, ph/4);
		Player2.add(lblPlayer2Level);

		 lblPlayer2LevelValue = new JLabel("Level Value");
		 lblPlayer2LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		Player2.add(lblPlayer2LevelValue);
		
		lblPlayer2Power = new JLabel("Power");
		lblPlayer2Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		Player2.add(lblPlayer2Power);

		 lblPlayer2PowerValue = new JLabel("Power Value");
		 lblPlayer2PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		 Player2.add(lblPlayer2PowerValue);
	
		 lblPlayer2Race = new JLabel("Race");
		lblPlayer2Race.setBounds(0,ph/2, pw/3, ph/2);
		Player2.add(lblPlayer2Race);
		
		 lblPlayer2Class = new JLabel("Class");
		lblPlayer2Class.setBounds(pw/3, ph/2, pw/3, ph/2);
		Player2.add(lblPlayer2Class);
		
		 lblPlayer2Cards = new JLabel("N° Cards");
		 lblPlayer2Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		Player2.add(lblPlayer2Cards);
		
		
		
		btnPlayer2Cards = new JButton(showPlayerCards);
		btnPlayer2Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);
		btnPlayer2Cards.setVisible(true);
		btnPlayer2Cards.setContentAreaFilled(false);
		btnPlayer2Cards.setBorderPainted(false);
		Player2.add(btnPlayer2Cards);
		
		/*
		 * 
		 * 
		 * 
		 * PLAYER 3 PANEL AND ITS COMPONENTS
		 * 
		 * 
		 * 
		 */
		
		Player3 = new JPanel();
		Player3.setOpaque(false);
		Player3.setBounds(ww/5, 0, ww/5, wh/3);
		Player3.setBorder(loweredbevel);
		add(Player3);
		Player3.setLayout(null);
		

		lblPlayer3Name = new JLabel("PlayerName");
		lblPlayer3Name.setBounds(0, 0, pw/2,ph/2);
		Player3.add(lblPlayer3Name);

		lblPlayer3Level = new JLabel("Level");
		lblPlayer3Level.setBounds(pw/2,0, pw/4, ph/4);
		Player3.add(lblPlayer3Level);

		 lblPlayer3LevelValue = new JLabel("Level Value");
		 lblPlayer3LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		 Player3.add(lblPlayer3LevelValue);
		
		lblPlayer3Power = new JLabel("Power");
		lblPlayer3Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		Player3.add(lblPlayer3Power);

		 lblPlayer3PowerValue = new JLabel("Power Value");
		 lblPlayer3PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		 Player3.add(lblPlayer3PowerValue);
	
		 lblPlayer3Race = new JLabel("Race");
		lblPlayer3Race.setBounds(0,ph/2, pw/3, ph/2);
		Player3.add(lblPlayer3Race);
		
		 lblPlayer3Class = new JLabel("Class");
		lblPlayer3Class.setBounds(pw/3, ph/2, pw/3, ph/2);
		Player3.add(lblPlayer3Class);
		
		 lblPlayer3Cards = new JLabel("N° Cards");
		 lblPlayer3Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		 Player3.add(lblPlayer3Cards);
		
		
		
		btnPlayer3Cards = new JButton(showPlayerCards);
		btnPlayer3Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);
		btnPlayer3Cards.setVisible(true);
		btnPlayer3Cards.setContentAreaFilled(false);
		btnPlayer3Cards.setBorderPainted(false);
		Player3.add(btnPlayer3Cards);
		
		/*
		 * 
		 * PLAYER 4 PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Player4 = new JPanel();
		Player4.setOpaque(false);
		Player4.setBounds(ww*2/5, 0, ww/5, wh/3);
		Player4.setBorder(loweredbevel);
		add(Player4);
		Player4.setLayout(null);
		
		lblPlayer4Name = new JLabel("PlayerName");
		lblPlayer4Name.setBounds(0, 0, pw/2, ph/2);
		Player4.add(lblPlayer4Name);

		lblPlayer4Level = new JLabel("Level");
		lblPlayer4Level.setBounds(pw/2,0, pw/4, ph/4);
		Player4.add(lblPlayer4Level);

		 lblPlayer4LevelValue = new JLabel("Level Value");
		 lblPlayer4LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		 Player4.add(lblPlayer4LevelValue);
		
		lblPlayer4Power = new JLabel("Power");
		lblPlayer4Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		Player4.add(lblPlayer4Power);

		 lblPlayer4PowerValue = new JLabel("Power Value");
		 lblPlayer4PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		 Player4.add(lblPlayer4PowerValue);
	
		 lblPlayer4Race = new JLabel("Race");
		lblPlayer4Race.setBounds(0,ph/2, pw/3, ph/2);
		Player4.add(lblPlayer4Race);
		
		 lblPlayer4Class = new JLabel("Class");
		lblPlayer4Class.setBounds(pw/3,ph/2, pw/3, ph/2);
		Player4.add(lblPlayer4Class);
		
		 lblPlayer4Cards = new JLabel("N° Cards");
		 lblPlayer4Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		 Player4.add(lblPlayer4Cards);
		
		
		
		btnPlayer4Cards = new JButton(showPlayerCards);
		btnPlayer4Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);
		btnPlayer4Cards.setVisible(true);
		btnPlayer4Cards.setContentAreaFilled(false);
		btnPlayer4Cards.setBorderPainted(false);
		Player4.add(btnPlayer4Cards);
		
		
		
		/*
		 * 
		 * PLAYER 5 PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Player5 = new JPanel();
		Player5.setOpaque(false);
		Player5.setBounds(ww*3/5,  0, ww/5, wh/3);
		Player5.setBorder(loweredbevel);
		add(Player5);
		Player5.setLayout(null);
		
		lblPlayer5Name = new JLabel("PlayerName");
		lblPlayer5Name.setBounds(0, 0, pw/2, ph/2);
		Player5.add(lblPlayer5Name);

		lblPlayer5Level = new JLabel("Level");
		lblPlayer5Level.setBounds(pw/2,0, pw/4, ph/4);
		Player5.add(lblPlayer5Level);

		 lblPlayer5LevelValue = new JLabel("Level Value");
		 lblPlayer5LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		 Player5.add(lblPlayer5LevelValue);
		
		lblPlayer5Power = new JLabel("Power");
		lblPlayer5Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		Player5.add(lblPlayer5Power);

		 lblPlayer5PowerValue = new JLabel("Power Value");
		 lblPlayer5PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		 Player5.add(lblPlayer5PowerValue);
	
		 lblPlayer5Race = new JLabel("Race");
		lblPlayer5Race.setBounds(0,ph/2, pw/3, ph/2);
		Player5.add(lblPlayer5Race);
		
		 lblPlayer5Class = new JLabel("Class");
		lblPlayer5Class.setBounds(pw/3,ph/2, pw/3, ph/2);
		Player5.add(lblPlayer5Class);
		
		 lblPlayer5Cards = new JLabel("N° Cards");
		 lblPlayer5Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		 Player5.add(lblPlayer5Cards);
		
		
		
		btnPlayer5Cards = new JButton(showPlayerCards);
		btnPlayer5Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);
		btnPlayer5Cards.setVisible(true);
		btnPlayer5Cards.setContentAreaFilled(false);
		btnPlayer5Cards.setBorderPainted(false);
		Player5.add(btnPlayer5Cards);
		
		/*
		 * 
		 * PLAYER 6 PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Player6 = new JPanel();
		Player6.setOpaque(false);
		Player6.setBounds(ww*4/5, 0, ww/5, wh/3);
		Player6.setBorder(loweredbevel);
		add(Player6);
		Player6.setLayout(null);
		
		lblPlayer6Name = new JLabel("PlayerName");
		lblPlayer6Name.setBounds(0, 0, pw/2, ph/2);
		Player6.add(lblPlayer6Name);

		lblPlayer6Level = new JLabel("Level");
		lblPlayer6Level.setBounds(pw/2,0, pw/4, ph/4);
		Player6.add(lblPlayer6Level);

		 lblPlayer6LevelValue = new JLabel("Level Value");
		 lblPlayer6LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		 Player6.add(lblPlayer6LevelValue);
		
		lblPlayer6Power = new JLabel("Power");
		lblPlayer6Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		Player6.add(lblPlayer6Power);

		 lblPlayer6PowerValue = new JLabel("Power Value");
		 lblPlayer6PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		 Player6.add(lblPlayer6PowerValue);
	
		 lblPlayer6Race = new JLabel("Race");
		lblPlayer6Race.setBounds(0,ph/2, pw/3, ph/2);
		Player6.add(lblPlayer6Race);
		
		 lblPlayer6Class = new JLabel("Class");
		lblPlayer6Class.setBounds(pw/3,ph/2, pw/3, ph/2);
		Player6.add(lblPlayer6Class);
		
		 lblPlayer6Cards = new JLabel("N° Cards");
		 lblPlayer6Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		 Player6.add(lblPlayer6Cards);
		
		
		
		btnPlayer6Cards = new JButton(showPlayerCards);
		btnPlayer6Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);
		btnPlayer6Cards.setVisible(true);
		btnPlayer6Cards.setContentAreaFilled(false);
		btnPlayer6Cards.setBorderPainted(false);
		Player6.add(btnPlayer6Cards);
		
		
		/*
		 * 
		 * DECKS PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Decks = new JPanel();
		 Decks.setOpaque(false);
		 Decks.setBounds(ww*3/4, wh/3, ww/4, wh/3);
		add(Decks);
		Decks.setLayout(null);
		
		JLabel lblDecks = new JLabel("DECKS");
		lblDecks.setBounds(66, 44, 46, 14);
		Decks.add(lblDecks);
		
		/*
		 * 
		 * TABLE PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Table = new JPanel();
		Table.setOpaque(false);
		Table.setBounds(0, wh/3, ww*3/4, wh/3);
		add(Table);
		Table.setLayout(null);
		
		JLabel lblMonster = new JLabel("Monster");
		lblMonster.setBounds(10, 26, 82, 85);
		Table.add(lblMonster);
		
		JLabel lblDranwCard = new JLabel("DranwCard or  Played Card");
		lblDranwCard.setBounds(136, 25, 155, 86);
		Table.add(lblDranwCard);

		/*
		 * 
		 * ZOOMED PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 ZoomedPanel = new JPanel();
		ZoomedPanel.setBackground(Color.WHITE);
		ZoomedPanel.setVisible(false);
		ZoomedPanel.setBounds(ww/3, wh/20, ww/3, wh*5/6);
		add(ZoomedPanel);
		ZoomedPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(287, 0, 23, 23);
		ZoomedPanel.add(btnNewButton);
		
		JLabel lblZoomedPanel = new JLabel("ZOOMED PANEL");
		lblZoomedPanel.setBounds(78, 226, 159, 23);
		ZoomedPanel.add(lblZoomedPanel);
		setComponentZOrder(ZoomedPanel, 0);
		
	}

	
	private void updateComponents(){
		
		ww=this.getWidth();
		wh=this.getHeight();

		//Resize automatically all JPanels on GameUI istance
		
		PlayerStats.setBounds(0, wh*2/3, ww*2/5, wh/3);
		hand.setBounds(ww*2/5, wh*2/3, ww*3/5, wh/3);
		Player2.setBounds(0, 0, ww/5, wh/3);
		Player3.setBounds(ww/5, 0, ww/5, wh/3);
		Player4.setBounds(ww*2/5, 0, ww/5, wh/3);
		Player5.setBounds(ww*3/5,  0, ww/5, wh/3);
		Player6.setBounds(ww*4/5, 0, ww/5, wh/3);
		Decks.setBounds(ww*3/4, wh/3, ww/4, wh/3);
		Table.setBounds(0, wh/3, ww*3/4, wh/3);
		ZoomedPanel.setBounds(ww/3, wh/20, ww/3, wh*5/6);
		
		
		//Get the width and the height of PlayerStats JPanel
		
		psw=PlayerStats.getWidth();
		psh=PlayerStats.getHeight();
		
		
		
		//-------Resize all JComponent of PlayerStats Panel-----------
		
		lblPlayerName.setBounds(0, 0, psw/2, psh/3);
		lblPlayerLevel.setBounds(psw/2,0, psw/4,psh/6);
		lblPlayerLevelValue.setBounds(psw*3/4,0, psw/4, psh/6);
		lblPlayerPower.setBounds(psw/2, psh/6, psw/4, psh/6);
		lblPlayerPowerValue.setBounds(psw*3/4, psh/6, psw/4, psh/6);
		lblPlayerRace.setBounds(0,psh/3, psw/6, psh/3);
		lblPlayerClass.setBounds(psw/6, psh/3, psw/6, psh/3);
		lblPlayerSex.setBounds(psw/3, psh/3, psw/6, psh/3);
		lblPlayerHead.setBounds(psw*2/3, psh/3, psw/6, psh*2/9);
		lblPlayerRiarm.setBounds(psw/2, psh*5/9, psw/6, psh*2/9);
		lblPlayerBody.setBounds(psw*2/3, psh*5/9, psw/6, psh*2/9);
		lblPlayerLearm.setBounds(psw*5/6, psh*5/9,psw/6, psh*2/9);
		lblPlayerLegs.setBounds(psw*2/3, psh*7/9, psw/6, psh*2/9);

		
		//Get the width and the height of Player* JPanel (* stands for 2,3,4,5,6). 
		
		pw=Player2.getWidth();
		ph=Player2.getHeight();
		
		
		//-------Resize all JComponent of Player2 Panel-----------
		
		lblPlayer2Name.setBounds(0, 0,pw/2, ph/2);
		lblPlayer2Level.setBounds(pw/2,0, pw/4, ph/4);
		lblPlayer2LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		lblPlayer2Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		lblPlayer2PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		lblPlayer2Race.setBounds(0,ph/2, pw/3, ph/2);
		lblPlayer2Class.setBounds(pw/3, ph/2, pw/3, ph/2);
		lblPlayer2Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		btnPlayer2Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);


		
		//-------Resize all JComponent of Player3 Panel-----------
		
		
		lblPlayer3Name.setBounds(0, 0, pw/2,ph/2);
		lblPlayer3Level.setBounds(pw/2,0, pw/4, ph/4);
		lblPlayer3LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		lblPlayer3Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		lblPlayer3PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		lblPlayer3Race.setBounds(0,ph/2, pw/3, ph/2);
		lblPlayer3Class.setBounds(pw/3, ph/2, pw/3, ph/2);
		lblPlayer3Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		btnPlayer3Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);

		
		
		//-------Resize all JComponent of Player4 Panel-----------
		
		lblPlayer4Name.setBounds(0, 0, pw/2, ph/2);
		lblPlayer4Level.setBounds(pw/2,0, pw/4, ph/4);
		lblPlayer4LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		lblPlayer4Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		lblPlayer4PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		lblPlayer4Race.setBounds(0,ph/2, pw/3, ph/2);
		lblPlayer4Class.setBounds(pw/3,ph/2, pw/3, ph/2);
		lblPlayer4Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		btnPlayer4Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);

		//-------Resize all JComponent of Player5 Panel-----------
		

		lblPlayer5Name.setBounds(0, 0, pw/2, ph/2);
		lblPlayer5Level.setBounds(pw/2,0, pw/4, ph/4);
		lblPlayer5LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		lblPlayer5Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		lblPlayer5PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		lblPlayer5Race.setBounds(0,ph/2, pw/3, ph/2);
		lblPlayer5Class.setBounds(pw/3,ph/2, pw/3, ph/2);
		lblPlayer5Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		btnPlayer5Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);
		
		
		
		//-------Resize all JComponent of Player6 Panel-----------
		
		
		lblPlayer6Level.setBounds(pw/2,0, pw/4, ph/4);
		lblPlayer6LevelValue.setBounds(pw*3/4,0, pw/4, ph/4);
		lblPlayer6Power.setBounds(pw/2, ph/4, pw/4, ph/4);
		lblPlayer6PowerValue.setBounds(pw*3/4, ph/4, pw/4, ph/4);
		lblPlayer6Race.setBounds(0,ph/2, pw/3, ph/2);
		lblPlayer6Class.setBounds(pw/3,ph/2, pw/3, ph/2);
		lblPlayer6Cards.setBounds(pw*2/3, ph/2, pw/3, ph/2);
		btnPlayer6Cards.setBounds(pw*7/8,ph*7/8,pw/8,ph/8);
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		
	}
	
	
	
	//Unimplemented methods
	
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
