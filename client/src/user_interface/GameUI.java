package user_interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import client.MunchkinClient;
import javafx.util.Pair;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import client.CardType;
import client.ClientCard;
import client.HandManager;

public class GameUI extends GamePanel {
	
	private ImageIcon inventory,doorCardsImage,doorDiscardsImage,TreasureCardsImage,
	TreasureDiscardsImage; 
	

	private BufferedImage background,framePlayerStats;
	private PlayerPanel OpponentPlayers[];

	private HandManager HandCards;
	
	//--------Hand JComponents------------
	private JButton handInventory;
	private ArrayList<ClientCard> hand;
	

		
	//--------Player 1 JComponents---------
	private JLabel lblPlayerName,lblPlayerLevel,lblPlayerRace,lblPlayerClass,
	lblPlayerSex,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue,
	lblPlayerHead,lblPlayerRiarm,lblPlayerBody,lblPlayerLearm,lblPlayerLegs;
	
	//----------Deck JComponents---------------
	private JButton doorCards,doorDiscards,treasureCards,treasureDiscards;
	
	/*
	 * ww= Gamewindow (JFrame) width
	 * wh= Gamewindow (JFrame) height
	 * psw= PlayerStats (JPanel) width
	 * psh= PlayerStats (JPanel) height
	 * dw= Deck (JPanel) width
	 * dh= Deck (JPanel) height
	 */
	private int ww,wh,psw,psh,dw,dh;
	
	//There are all the JPanels in GameUI
	private JPanel PlayerStats,Hand,Decks,Table;
	private ZoomedPanel zp;
	

	/**
	 * Create the panel.
	 */
	public GameUI(GameWindow window)  {
		super(window);
		OpponentPlayers = new PlayerPanel[5];
		this.createRandomFramePanel();
	
		hand = new ArrayList<ClientCard>();
		
		
		
		this.background=MunchkinClient.getImage("panel_background");
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
		ClientCard card1 = new ClientCard("door", CardType.Door);
		card1.CreateCard(MunchkinClient.getImage("door_card"), zp);
		ClientCard card2 = new ClientCard("treasure", CardType.Treasure);
		card2.CreateCard(MunchkinClient.getImage("treasure_card"), zp);
		HandCards.drawCard(card1);
		HandCards.drawCard(card2);
		
		zp.setVisible(true);
		zp.setBounds(ww/3, wh/20, ww/3, wh*5/6);
		this.add(zp);
		zp.setLayout(null);
		
		this.setComponentZOrder(zp, 0);	
		this.handPositioning();
		Hand.add(HandCards.getCard("door"));
		Hand.add(HandCards.getCard("treasure"));
		
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
		
		doorCardsImage = new ImageIcon();
		doorCardsImage.setImage(MunchkinClient.getImage("door_back"));
		
		doorDiscardsImage = new ImageIcon();
		doorDiscardsImage.setImage(MunchkinClient.getImage("door_card"));
		
		TreasureCardsImage = new ImageIcon();
		TreasureCardsImage.setImage(MunchkinClient.getImage("treasure_back"));
		
		TreasureDiscardsImage = new ImageIcon();
		TreasureDiscardsImage.setImage(MunchkinClient.getImage("treasure_card"));
		
		
		 Decks = new JPanel();
		 Decks.setOpaque(false);
		 Decks.setBounds(ww/2, wh/3, ww/2, wh/3);
		add(Decks);
		Decks.setLayout(null);
		
		dw=Decks.getWidth();
		dh=Decks.getHeight();
		
		doorCards = new JButton(doorCardsImage);
		doorCards.setBounds(dw/25, dh/10, dw/5, dh*8/10);
		doorCards.setActionCommand("DrawDoor");
		Decks.add(doorCards);
		
		doorDiscards = new JButton(doorDiscardsImage);
		doorDiscards.setBounds(dw*7/25, dh/10, dw/5, dh*8/10);
		Decks.add(doorDiscards);
		
		treasureCards = new JButton(TreasureCardsImage);
		treasureCards.setBounds(dw*13/25, dh/10, dw/5, dh*8/10);
		treasureCards.setActionCommand("DrawTreasure");
		Decks.add(treasureCards);
		
		treasureDiscards = new JButton(TreasureDiscardsImage);
		treasureDiscards.setBounds(dw*19/25, dh/10, dw/5, dh*8/10);
		Decks.add(treasureDiscards);
		
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
		Decks.setBounds(ww/2, wh/3, ww/2, wh/3);
		Table.setBounds(0, wh/3, ww*3/4, wh/3);
		zp.setBounds(ww/3, wh/20, ww/3, wh*5/6);
		
		this.handPositioning();
		
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
		
		this.resizeButton(doorCards, doorCardsImage, dw/25, dh/10, dw/5, dh*8/10);
		this.resizeButton(doorDiscards, doorDiscardsImage, dw*7/25, dh/10, dw/5, dh*8/10);
		this.resizeButton(treasureCards, TreasureCardsImage,dw*13/25, dh/10, dw/5, dh*8/10);
		this.resizeButton(treasureDiscards, TreasureDiscardsImage, dw*19/25, dh/10, dw/5, dh*8/10);
		
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		switch(e.getActionCommand()){
		
		case "DrawDoor":
			ClientCard card1 = new ClientCard("door", CardType.Door);
			card1.CreateCard(MunchkinClient.getImage("door_card"),  zp);
			
			HandCards.drawCard(card1);
			this.setComponentZOrder(zp, 0);	
			this.handPositioning();
			Hand.add(HandCards.getCard("door"));
			break;
		case "DrawTreasure":
			ClientCard card2 = new ClientCard("treasure", CardType.Treasure);
					card2.CreateCard( MunchkinClient.getImage("treasure_card"), zp);
			
			HandCards.drawCard(card2);
			this.setComponentZOrder(zp, 0);	
			this.handPositioning();
			Hand.add(HandCards.getCard("door"));			
		
		}
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
	
	public PlayerPanel[] getOpponentPlayers() {
		return OpponentPlayers;
	}
	
	
	public void resizeButton(JButton btn,ImageIcon image,int newX,int newY,int newWidth,int newHeight){
		 Image img = image.getImage();
		   Image newimg = img.getScaledInstance(newWidth,newHeight, java.awt.Image.SCALE_SMOOTH ) ;  
		   ImageIcon x = new ImageIcon(newimg);
		   btn.setIcon(x);
		   btn.setBounds(newX,newY,newWidth,newHeight);
		  
		   
	}
	
	public void handPositioning(){
		int handSize,initpos,handWidth,i=0,handHeight;
		handSize= HandCards.getSize();
		handWidth=Hand.getWidth();
		handHeight=Hand.getHeight();
		initpos=handWidth/2-((handSize/2)*(handWidth/12));
		for(ClientCard card : hand){
			card.setBounds(initpos+i*handWidth, handHeight/4, handWidth/6, handHeight/2);
			i++;
		}
		Hand.revalidate();
		Hand.repaint();
	}

}
