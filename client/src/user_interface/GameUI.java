package user_interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import client.MunchkinClient;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Rectangle;

import client.CardType;
import client.ClientCard;
import client.HandManager;

public class GameUI extends GamePanel {
	
	private ImageIcon inventory;
	private BufferedImage background,framePlayerStats;
	private PlayerPanel OpponentPlayers[] = new PlayerPanel[5];
	private HandManager HandCards;
	
	//--------Hand JComponents------------
	private JButton handInventory;
	
	//--------Player 1 JComponents---------
	private JLabel lblPlayerName,lblPlayerLevel,lblPlayerRace,lblPlayerClass,
	lblPlayerSex,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue,
	lblPlayerHead,lblPlayerRiarm,lblPlayerBody,lblPlayerLearm,lblPlayerLegs;
	
	
	/*
	 * ww= Gamewindow (JFrame) width
	 * wh= Gamewindow (JFrame) height
	 * psw= PlayerStats (JPanel) width
	 * psh= PlayerStats (JPanel) height
	 */
	private int ww,wh,psw,psh;
	
	//There are all the JPanels in GameUI
	private JPanel PlayerStats,Hand,Decks,Table;
	private ZoomedPanel zp;
	

	/**
	 * Create the panel.
	 */
	public GameUI(GameWindow window)  {
		super(window);
		
		this.createRandomFramePanel();
	
		this.background=MunchkinClient.getImage("lobby_background");
		this.framePlayerStats=MunchkinClient.getImage("playerstats_frame");
	
		this.setOpaque(false);
		this.setLayout(null);
		
		
		ww=window.getContentPane().getWidth();
		wh=window.getContentPane().getHeight();
		
		inventory = new ImageIcon();
		inventory.setImage(MunchkinClient.getImage("inventory"));
		
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
		Hand.setBounds(ww*2/5, wh*2/3, ww*3/5, wh/3);
		add(Hand);
		Hand.setLayout(null);
		
		handInventory = new JButton(inventory);
		handInventory.setBounds(Hand.getWidth()*7/8,Hand.getHeight()*7/8,Hand.getWidth()/8,Hand.getHeight()/8);
		handInventory.setVisible(true);
		handInventory.setContentAreaFilled(false);
		handInventory.setBorderPainted(false);
		Hand.add(handInventory);
		
		
		
		/*
		 * 
		 * ZOOMED PANEL AND ITS COMPONENTS
		 * 
		 */
		
		zp=new ZoomedPanel();
		
		HandCards = new HandManager();
		ClientCard card1 = new ClientCard("ciao", CardType.Door, MunchkinClient.getImage("door_card"), new Rectangle(Hand.getWidth()/3, Hand.getHeight()/4, Hand.getWidth()/6, Hand.getHeight()/2), zp);
		ClientCard card2 = new ClientCard("ciao2", CardType.Door, MunchkinClient.getImage("treasure_card"), new Rectangle(Hand.getWidth()*5/12, Hand.getHeight()/4, Hand.getWidth()/6, Hand.getHeight()/2), zp);
		HandCards.addCard(card1);
		HandCards.addCard(card2);
		
		zp.setVisible(true);
		zp.setBounds(ww/3, wh/20, ww/3, wh*5/6);
		this.add(zp);
		zp.setLayout(null);
		
		this.setComponentZOrder(zp, 0);	
		Hand.add(HandCards.getCard("ciao"));
		Hand.add(HandCards.getCard("ciao2"));
		
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
		PlayerStats.setBounds(0, wh*2/3, ww*2/5, wh/3);
		add(PlayerStats);
		PlayerStats.setLayout(null);
		
		
		psw=PlayerStats.getWidth();
		psh=PlayerStats.getHeight();
		
		lblPlayerName = new JLabel("PlayerName");
		lblPlayerName.setBounds(psw/16, 0, psw*7/16, psh/3);
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
		lblPlayerRace.setBounds(psw/8,psh/3, psw/8, psh/4);
		PlayerStats.add(lblPlayerRace);
		
		 lblPlayerClass = new JLabel("Class");
		lblPlayerClass.setBounds(psw/4, psh/3, psw/8, psh/4);
		PlayerStats.add(lblPlayerClass);
		
		 lblPlayerSex = new JLabel("Sex");
		lblPlayerSex.setBounds(psw*3/8, psh/3, psw/8, psh/4);
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
		 * OPPONENT PLAYER JPANEL
		 * 
		 * 
		 * 
		 */
		
		for(int k=0;k<5;k++)
		{
			OpponentPlayers[k].setOpaque(false);
			OpponentPlayers[k].setBounds(ww*k/5, 0, ww/5, wh/3);
			add(OpponentPlayers[k]);
			OpponentPlayers[k].setLayout(null);
		}

		
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

	}

	
	
	
	private void updateComponents(){
		
		ww=this.getWidth();
		wh=this.getHeight();

		//Resize automatically all JPanels on GameUI instance
		
		PlayerStats.setBounds(0, wh*2/3, ww*2/5, wh/3);
		Hand.setBounds(ww*2/5, wh*2/3, ww*3/5, wh/3);
		Decks.setBounds(ww*3/4, wh/3, ww/4, wh/3);
		Table.setBounds(0, wh/3, ww*3/4, wh/3);
		zp.setBounds(ww/3, wh/20, ww/3, wh*5/6);
		
		
		for(int k=0;k<5;k++)
		{
			OpponentPlayers[k].setBounds(ww*k/5, 0, ww/5, wh/3);
			OpponentPlayers[k].updatePlayerComponents();
		}
		
		
		
		//Get the width and the height of PlayerStats JPanel
		
		psw=PlayerStats.getWidth();
		psh=PlayerStats.getHeight();
		
		
		
		//-------Resize all JComponent of PlayerStats Panel-----------
		
		lblPlayerName.setBounds(psw/16, 0, psw*7/16, psh/3);
		lblPlayerLevel.setBounds(psw/2,0, psw/4,psh/6);
		lblPlayerLevelValue.setBounds(psw*3/4,0, psw/4, psh/6);
		lblPlayerPower.setBounds(psw/2, psh/6, psw/4, psh/6);
		lblPlayerPowerValue.setBounds(psw*3/4, psh/6, psw/4, psh/6);
		lblPlayerRace.setBounds(psw/8,psh/3, psw/8, psh/4);
		lblPlayerClass.setBounds(psw/4, psh/3, psw/8, psh/4);
		lblPlayerSex.setBounds(psw*3/8, psh/3, psw/8, psh/4);
		lblPlayerHead.setBounds(psw*2/3, psh/3, psw/6, psh*2/9);
		lblPlayerRiarm.setBounds(psw/2, psh*5/9, psw/6, psh*2/9);
		lblPlayerBody.setBounds(psw*2/3, psh*5/9, psw/6, psh*2/9);
		lblPlayerLearm.setBounds(psw*5/6, psh*5/9,psw/6, psh*2/9);
		lblPlayerLegs.setBounds(psw*2/3, psh*7/9, psw/6, psh*2/9);

		
		//Get the width and the height of Player* JPanel (* stands for 2,3,4,5,6). 
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		
	}
	
	
	
	
	
	private void createRandomFramePanel(){
		 int size = 10,k=0;
	        int[] x = new int[5];
	        
	        ArrayList<Integer> list = new ArrayList<Integer>(size);
	        for(int i = 1; i <= size; i++) {
	            list.add(i);
	        }

	        Random rand = new Random();
	        while(list.size() > 0&&k<5) {
	            int index = rand.nextInt(list.size());
	            x[k]=list.remove(index);
	            k++;
	        }  
	    	
	        for(k=0;k<5;k++){
	     OpponentPlayers[k] = new PlayerPanel(MunchkinClient.getImage("frameplayer"+x[k]));
	        }
	    
		
	} 
	
	

}
