package user_interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import client.MunchkinClient;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class GameUI extends GamePanel implements ComponentListener {
	
	private JLabel lblPlayerName,lblPlayerLevel,lblPlayerRace,lblPlayerClass,
	lblPlayerSex,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue,
	lblPlayerHead,lblPlayerRiarm,lblPlayerBody,lblPlayerLearm,lblPlayerLegs;
	
	
	private int ww,wh;
	private JPanel PlayerStats,hand,Player2,Player3,
	Player4,Player5,Player6,Cards,Table,ZoomedPanel;
	

	/**
	 * Create the panel.
	 */
	public GameUI(GameWindow window)  {
		super(window);
		
		this.addComponentListener(this);
		
		ww=window.getContentPane().getWidth();
		wh=window.getContentPane().getHeight();
		
		setBackground(Color.RED);
		setLayout(null);
		
		/*
		 * 
		 * PLAYERSTATS PANEL AND ITS COMPONENTS
		 * 
		 * 
		 */
		
		PlayerStats = new JPanel();
		PlayerStats.setBackground(Color.PINK);
		PlayerStats.setBounds(0, wh*2/3, ww*2/5, wh/3);
		add(PlayerStats);
		PlayerStats.setLayout(null);
		
		lblPlayerName = new JLabel("PlayerName");
		lblPlayerName.setBounds(0, 0, PlayerStats.getWidth()/2, PlayerStats.getHeight()/3);
		PlayerStats.add(lblPlayerName);

		lblPlayerLevel = new JLabel("Level");
		lblPlayerLevel.setBounds(PlayerStats.getWidth()/2,0, PlayerStats.getWidth()/4, PlayerStats.getHeight()/6);
		PlayerStats.add(lblPlayerLevel);

		 lblPlayerLevelValue = new JLabel("Level Value");
		lblPlayerLevelValue.setBounds(PlayerStats.getWidth()*3/4,0, PlayerStats.getWidth()/4, PlayerStats.getHeight()/6);
		PlayerStats.add(lblPlayerLevelValue);
		
		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setBounds(PlayerStats.getWidth()/2, PlayerStats.getHeight()/6, PlayerStats.getWidth()/4, PlayerStats.getHeight()/6);
		PlayerStats.add(lblPlayerPower);

		 lblPlayerPowerValue = new JLabel("Power Value");
		 lblPlayerPowerValue.setBounds(PlayerStats.getWidth()*3/4, PlayerStats.getHeight()/6, PlayerStats.getWidth()/4, PlayerStats.getHeight()/6);
		PlayerStats.add(lblPlayerPowerValue);
	
		 lblPlayerRace = new JLabel("Race");
		lblPlayerRace.setBounds(0,PlayerStats.getHeight()/3, PlayerStats.getWidth()/6, PlayerStats.getHeight()/3);
		PlayerStats.add(lblPlayerRace);
		
		 lblPlayerClass = new JLabel("Class");
		lblPlayerClass.setBounds(PlayerStats.getWidth()/6, PlayerStats.getHeight()/3, PlayerStats.getWidth()/6, PlayerStats.getHeight()/3);
		PlayerStats.add(lblPlayerClass);
		
		 lblPlayerSex = new JLabel("Sex");
		lblPlayerSex.setBounds(PlayerStats.getWidth()/3, PlayerStats.getHeight()/3, PlayerStats.getWidth()/6, PlayerStats.getHeight()/3);
		PlayerStats.add(lblPlayerSex);
		
		
		BufferedImage img = MunchkinClient.getImage("player_head");
		
		 lblPlayerHead = new JLabel();
		 lblPlayerHead.setBounds(PlayerStats.getWidth()*2/3, PlayerStats.getHeight()/3, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		 Image dimg = img.getScaledInstance(lblPlayerHead.getWidth(), lblPlayerHead.getHeight(),
			        Image.SCALE_SMOOTH);
		 ImageIcon head= new ImageIcon(dimg);
		 lblPlayerHead.setIcon(head);
		 PlayerStats.add(lblPlayerHead);
		
		
		
		 lblPlayerRiarm = new JLabel("RiArm");
		 lblPlayerRiarm.setBounds(PlayerStats.getWidth()/2, PlayerStats.getHeight()*5/9, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		PlayerStats.add(lblPlayerRiarm);
		
		 lblPlayerBody = new JLabel("Body");
		 lblPlayerBody.setBounds(PlayerStats.getWidth()*2/3, PlayerStats.getHeight()*5/9, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		PlayerStats.add(lblPlayerBody);
		
		 lblPlayerLearm = new JLabel("LeArm");
		 lblPlayerLearm.setBounds(PlayerStats.getWidth()*5/6, PlayerStats.getHeight()*5/9, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		PlayerStats.add(lblPlayerLearm);
		
		 lblPlayerLegs = new JLabel("Legs");
		 lblPlayerLegs.setBounds(PlayerStats.getWidth()*2/3, PlayerStats.getHeight()*7/9, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		 PlayerStats.add(lblPlayerLegs);
		
		/*
		 * 
		 * HAND PANEL AND ITS COMPONENTS
		 * 
		 * 
		 */
		
		
		hand = new JPanel();
		hand.setBackground(Color.ORANGE);
		hand.setBounds(ww*2/5, wh*2/3, ww*3/5, wh/3);
		add(hand);
		hand.setLayout(null);
		
		JLabel lblHand = new JLabel("hand");
		lblHand.setBounds(151, 48, 34, 24);
		lblHand.setHorizontalTextPosition(SwingConstants.CENTER);
		hand.add(lblHand);
		
		
		/*
		 * 
		 * PLAYER 2 PANEL AND ITS COMPONENTS
		 * 
		 */
		
		
		Player2 = new JPanel();
		Player2.setBackground(Color.CYAN);
		Player2.setBounds(0, 0, ww/5, wh/3);
		add(Player2);
		Player2.setLayout(null);
		
		JLabel lblPlayer = new JLabel("PLAYER 2");
		lblPlayer.setBounds(52, 32, 46, 14);
		lblPlayer.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		Player2.add(lblPlayer);
		
		/*
		 * 
		 * PLAYER 3 PANEL AND ITS COMPONENTS
		 * 
		 */
		
		Player3 = new JPanel();
		Player3.setBackground(Color.MAGENTA);
		Player3.setBounds(ww/5, 0, ww/5, wh/3);
		add(Player3);
		Player3.setLayout(null);
		
		JLabel lblPlayer_1 = new JLabel("PLAYER 3");
		lblPlayer_1.setBounds(43, 11, 46, 14);
		Player3.add(lblPlayer_1);
		
		/*
		 * 
		 * PLAYER 4 PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Player4 = new JPanel();
		Player4.setBackground(Color.BLUE);
		Player4.setBounds(ww*2/5, 0, ww/5, wh/3);
		add(Player4);
		Player4.setLayout(null);
		
		JLabel lblPlayer_2 = new JLabel("PLAYER4");
		lblPlayer_2.setBounds(39, 11, 46, 14);
		Player4.add(lblPlayer_2);
		
		
		/*
		 * 
		 * PLAYER 5 PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Player5 = new JPanel();
		Player5.setBackground(Color.LIGHT_GRAY);
		Player5.setBounds(ww*3/5,  0, ww/5, wh/3);
		add(Player5);
		Player5.setLayout(null);
		
		JLabel lblPlayerName_1 = new JLabel("PLAYER NAME5");
		lblPlayerName_1.setBounds(0, 0, 99, 20);
		Player5.add(lblPlayerName_1);
		
		JLabel lblLevel_1 = new JLabel("LEVEL5");
		lblLevel_1.setBounds(10, 31, 44, 20);
		Player5.add(lblLevel_1);
		
		
		
		JLabel lblRace_1 = new JLabel("RACE5");
		lblRace_1.setBounds(0, 57, 46, 14);
		Player5.add(lblRace_1);
		
		JLabel lblClass_1 = new JLabel("CLASS 5");
		lblClass_1.setBounds(0, 82, 46, 14);
		Player5.add(lblClass_1);
		
		JLabel lblSex_1 = new JLabel("SEX5");
		lblSex_1.setBounds(0, 113, 46, 14);
		Player5.add(lblSex_1);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(55, 113, 44, 14);
		Player5.add(btnNewButton_1);
		
		/*
		 * 
		 * PLAYER 6 PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Player6 = new JPanel();
		Player6.setBackground(Color.GREEN);
		Player6.setBounds(ww*4/5, 0, ww/5, wh/3);
		add(Player6);
		Player6.setLayout(null);
		
		JLabel lblPlayer_3 = new JLabel("PLAYER6");
		lblPlayer_3.setBounds(10, 40, 46, 14);
		Player6.add(lblPlayer_3);
		
		
		/*
		 * 
		 * DECKS PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Cards = new JPanel();
		Cards.setBackground(Color.PINK);
		Cards.setBounds(ww*3/4, wh/3, ww/4, wh/3);
		add(Cards);
		Cards.setLayout(null);
		
		JLabel lblDecks = new JLabel("DECKS");
		lblDecks.setBounds(66, 44, 46, 14);
		Cards.add(lblDecks);
		
		/*
		 * 
		 * TABLE PANEL AND ITS COMPONENTS
		 * 
		 */
		
		 Table = new JPanel();
		Table.setBackground(Color.GRAY);
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
		ZoomedPanel.setBounds(ww/3, wh/20, ww/3, wh*4/6);
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
	@Override
	public void componentResized(ComponentEvent e) {
		this.updateComponents();
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
		Cards.setBounds(ww*3/4, wh/3, ww/4, wh/3);
		Table.setBounds(0, wh/3, ww*3/4, wh/3);
		ZoomedPanel.setBounds(ww/3, wh/20, ww/3, wh*4/6);
		
		
		
		
		//-------Resize all JComponent of PlayerStats Panel-----------
		
		lblPlayerName.setBounds(0, 0, PlayerStats.getWidth()/2, PlayerStats.getHeight()/3);
		lblPlayerLevel.setBounds(PlayerStats.getWidth()/2,0, PlayerStats.getWidth()/4, PlayerStats.getHeight()/6);
		lblPlayerLevelValue.setBounds(PlayerStats.getWidth()*3/4,0, PlayerStats.getWidth()/4, PlayerStats.getHeight()/6);
		lblPlayerPower.setBounds(PlayerStats.getWidth()/2, PlayerStats.getHeight()/6, PlayerStats.getWidth()/4, PlayerStats.getHeight()/6);
		lblPlayerPowerValue.setBounds(PlayerStats.getWidth()*3/4, PlayerStats.getHeight()/6, PlayerStats.getWidth()/4, PlayerStats.getHeight()/6);
		lblPlayerRace.setBounds(0,PlayerStats.getHeight()/3, PlayerStats.getWidth()/6, PlayerStats.getHeight()/3);
		lblPlayerClass.setBounds(PlayerStats.getWidth()/6, PlayerStats.getHeight()/3, PlayerStats.getWidth()/6, PlayerStats.getHeight()/3);
		lblPlayerSex.setBounds(PlayerStats.getWidth()/3, PlayerStats.getHeight()/3, PlayerStats.getWidth()/6, PlayerStats.getHeight()/3);
		lblPlayerHead.setBounds(PlayerStats.getWidth()*2/3, PlayerStats.getHeight()/3, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		lblPlayerRiarm.setBounds(PlayerStats.getWidth()/2, PlayerStats.getHeight()*5/9, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		lblPlayerBody.setBounds(PlayerStats.getWidth()*2/3, PlayerStats.getHeight()*5/9, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		lblPlayerLearm.setBounds(PlayerStats.getWidth()*5/6, PlayerStats.getHeight()*5/9, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		lblPlayerLegs.setBounds(PlayerStats.getWidth()*2/3, PlayerStats.getHeight()*7/9, PlayerStats.getWidth()/6, PlayerStats.getHeight()*2/9);
		
		
	}
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
	
}
