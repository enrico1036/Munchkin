package user_interface;

import javax.swing.JPanel;

import cards.ClassCard;
import cards.EquipSlot;
import cards.Race;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import client.MunchkinClient;
import network.GameEventHandler;
import client.HandCards;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import client.CardType;
import client.ClientCard;
import client.HandManager;

public class GameUI extends GamePanel {
	

	private BufferedImage background,framePlayerStats,doorCardsImage,
	doorDiscardsImage,TreasureCardsImage,TreasureDiscardsImage,head,hand1,body,hand2,feet;
	private PlayerPanel OpponentPlayers[];

	private HandManager HandCards;

	//----------Table JComponents
	private ImageButton lblDrawnCard;

	//--------Player 1 JComponents---------
	private JLabel lblPlayerName,lblPlayerLevel,
	lblPlayernumcard,lblPlayerPower,lblPlayerPowerValue,lblPlayerLevelValue;
	
	private ClientCard PlayerHead,PlayerHand1,PlayerBody,PlayerHand2,PlayerFeet,PlayerRace,PlayerClass;
	
	//----------Deck JComponents---------------
	private ImageButton doorCards,doorDiscards,treasureCards,treasureDiscards;
	
	//-----------Dice JComponents-------------
	private JLabel diceLabel;
	
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
	private DiceManager Dice;
	private ZoomedPanel zp;
	

	/**
	 * Create the panel.
	 */
	public GameUI(GameWindow window)  {
		super(window);
		OpponentPlayers = new PlayerPanel[GameEventHandler.getPlayers().length-1];
		this.createRandomFramePanel();

		
		this.background=MunchkinClient.getImage("panel_background");
		this.framePlayerStats=MunchkinClient.getImage("playerstats_frame");
	
		this.setOpaque(false);
		this.setLayout(null);
		
		
		ww=window.getContentPane().getWidth();
		wh=window.getContentPane().getHeight();
		
		
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
		Dice.setBounds(ww*2/5, wh*2/3, ww*3/5, wh/3);
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
		Hand.setBounds(ww*2/5, wh*2/3, ww*3/5, wh/3);
		add(Hand);
		Hand.setLayout(null);
		
		
		
		
		/*
		 * 
		 * ZOOMED PANEL AND ITS COMPONENTS
		 * 
		 */
		
		zp=new ZoomedPanel();
				
		HandCards card1 = new HandCards("door");
		card1.CreateCard(MunchkinClient.getImage("door_card"), zp);
		HandCards card2 = new HandCards("treasure");
		card2.CreateCard(MunchkinClient.getImage("treasure_card"), zp);
		
		
		HandCards = new HandManager(Hand);
		HandCards.drawCard(card1);
		HandCards.drawCard(card2);
		
		
		zp.setVisible(true);
		zp.setBounds(ww/3, wh/20, ww/3, wh*5/8);
		this.add(zp);
		zp.setLayout(null);
		
		this.setComponentZOrder(zp, 0);	

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
		
		lblPlayerName = new JLabel(GameEventHandler.getConnection().getConnectedPlayerName());
		lblPlayerName.setBounds(psw/16, 0, psw*7/16, psh/3);
		PlayerStats.add(lblPlayerName);

		lblPlayerLevel = new JLabel("Level");
		lblPlayerLevel.setBounds(psw/2,0, psw/4,psh/6);
		PlayerStats.add(lblPlayerLevel);

		 lblPlayerLevelValue = new JLabel(String.valueOf(1));
		lblPlayerLevelValue.setBounds(psw*3/4,0, psw/4, psh/6);
		PlayerStats.add(lblPlayerLevelValue);
		
		lblPlayerPower = new JLabel("Power");
		lblPlayerPower.setBounds(psw/2, psh/6, psw/4, psh/6);
		PlayerStats.add(lblPlayerPower);

		 lblPlayerPowerValue = new JLabel(String.valueOf(0));
		 lblPlayerPowerValue.setBounds(psw*3/4, psh/6, psw/4, psh/6);
		PlayerStats.add(lblPlayerPowerValue);
	
		 PlayerRace = new ClientCard("Race");
		PlayerRace.setBounds(psw/8,psh/3, psw/8, psh/4);
		PlayerStats.add(PlayerRace);
		
		 PlayerClass = new ClientCard("Class");
		PlayerClass.setBounds(psw/4, psh/3, psw/8, psh/4);
		PlayerStats.add(PlayerClass);
		
		 lblPlayernumcard = new JLabel(String.valueOf(0));
		 lblPlayernumcard.setBounds(psw*3/8, psh/3, psw/8, psh/4);
		PlayerStats.add(lblPlayernumcard);
		
		
		 head = MunchkinClient.getImage("player_head");
		 hand1 = MunchkinClient.getImage("player_hand1");
		 body = MunchkinClient.getImage("player_body");
		 hand2 = MunchkinClient.getImage("player_hand2");
		 feet = MunchkinClient.getImage("player_feet");
		
		
		 PlayerHead = new ClientCard("head");
		 PlayerHead.setBounds(psw*2/3, psh/3, psw/6, psh*2/9);
		 PlayerHead.CreateCard(head, zp);
		 PlayerStats.add(PlayerHead);
		
		
		
		 PlayerHand1 = new ClientCard("hand1");
		 PlayerHand1.setBounds(psw/2, psh*5/9, psw/6, psh*2/9);
		 PlayerHand1.CreateCard(hand1, zp);
		PlayerStats.add(PlayerHand1);
		
		 PlayerBody = new ClientCard("body");
		 PlayerBody.setBounds(psw*2/3, psh*5/9, psw/6, psh*2/9);
		 PlayerBody.CreateCard(body, zp);
		PlayerStats.add(PlayerBody);
		
		PlayerHand2 = new ClientCard("hand2");
		PlayerHand2.setBounds(psw*5/6, psh*5/9,psw/6, psh*2/9);
		PlayerHand2.CreateCard(hand2, zp);
		PlayerStats.add(PlayerHand2);
		
		 PlayerFeet = new ClientCard("feet");
		 PlayerFeet.setBounds(psw*2/3, psh*7/9, psw/6, psh*2/9);
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
		
		 
		 //k=uguale alla richiesta di quanti utenti ci sono
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
		
		
		doorCardsImage=MunchkinClient.getImage("door_back");
		
		doorDiscardsImage=MunchkinClient.getImage("door_card");
		
		TreasureCardsImage=MunchkinClient.getImage("treasure_back");

		TreasureDiscardsImage=MunchkinClient.getImage("treasure_card");
		
		
		 Decks = new JPanel();
		 Decks.setOpaque(false);
		 Decks.setBounds(ww/2, wh/3, ww/2, wh/3);
		add(Decks);
		Decks.setLayout(null);
		
		dw=Decks.getWidth();
		dh=Decks.getHeight();
		
		doorCards = new ImageButton(doorCardsImage);
		doorCards.setBounds(dw/25, dh/10, dw/5, dh*8/10);
		doorCards.setActionCommand("DrawDoor");
		doorCards.addActionListener(this);
		Decks.add(doorCards);
		
		doorDiscards = new ImageButton(doorDiscardsImage);
		doorDiscards.setBounds(dw*7/25, dh/10, dw/5, dh*8/10);
		Decks.add(doorDiscards);
		
		treasureCards = new ImageButton(TreasureCardsImage);
		treasureCards.setBounds(dw*13/25, dh/10, dw/5, dh*8/10);
		treasureCards.setActionCommand("DrawTreasure");
		treasureCards.addActionListener(this);
		Decks.add(treasureCards);
		
		treasureDiscards = new ImageButton(TreasureDiscardsImage);
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
		
		lblDrawnCard = new ImageButton(null);
		lblDrawnCard.setBounds(136, 25, 155, 86);
		Table.add(lblDrawnCard);

		
	
	}

	
	
	
	private void updateComponents(){
		
		ww=this.getWidth();
		wh=this.getHeight();

		//Resize automatically all JPanels on GameUI instance
		
		PlayerStats.setBounds(0, wh*2/3, ww*2/5, wh/3);
		Hand.setBounds(ww*2/5, wh*2/3, ww*3/5, wh/3);
		Decks.setBounds(ww/2, wh/3, ww/2, wh/3);
		Table.setBounds(0, wh/3, ww*3/4, wh/3);
		zp.setBounds(ww/3, wh/20, ww/3, wh*5/8);
		
		
		
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
		PlayerRace.setBounds(psw/8,psh/3, psw/8, psh/4);
		PlayerClass.setBounds(psw/4, psh/3, psw/8, psh/4);
		lblPlayernumcard.setBounds(psw*3/8, psh/3, psw/8, psh/4);
		PlayerHead.setBounds(psw*2/3, psh/3, psw/6, psh*2/9);
		PlayerHand1.setBounds(psw/2, psh*5/9, psw/6, psh*2/9);
		PlayerBody.setBounds(psw*2/3, psh*5/9, psw/6, psh*2/9);
		PlayerHand2.setBounds(psw*5/6, psh*5/9,psw/6, psh*2/9);
		PlayerFeet.setBounds(psw*2/3, psh*7/9, psw/6, psh*2/9);

		
		dw=Decks.getWidth();
		dh=Decks.getHeight();
		
		//Get the width and the height of Player* JPanel (* stands for 2,3,4,5,6). 
		
		doorCards.setBounds(dw/25, dh/10, dw/5, dh*8/10);
		doorDiscards.setBounds(dw*7/25, dh/10, dw/5, dh*8/10);
		treasureCards.setBounds(dw*13/25, dh/10, dw/5, dh*8/10);
		treasureDiscards.setBounds(dw*19/25, dh/10, dw/5, dh*8/10);
		
		HandCards.handPositioning();
		
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
			HandCards card1 = new HandCards("door");
			card1.CreateCard(MunchkinClient.getImage("door_card"),  zp);
			
			HandCards.drawCard(card1);
			Hand.add(HandCards.getCard("door"));
			
			
			break;
		case "DrawTreasure":
			HandCards card2 = new HandCards("treasure");
			card2.CreateCard(MunchkinClient.getImage("treasure_card"), zp);
			
			HandCards.drawCard(card2);
			Hand.add(HandCards.getCard("treasure"));
		break;
		
	
		}
	}
	
	public void changeEquipment(EquipSlot e,String EquipmentName){
		
		BufferedImage image= MunchkinClient.getImage(EquipmentName);
		
		switch(e){
		case head:
			PlayerHead.setImage(image);
			break;
		case hand1:
			PlayerHand1.setImage(image);
			break;
		case hand2:
			PlayerHand2.setImage(image);
			break;
		case body:
			PlayerBody.setImage(image);
			break;
		case feet:
			PlayerFeet.setImage(image);
			break;
			
		}
	}


	private void createRandomFramePanel(){
		 int size = 10,k=0;
	        int[] x = new int[GameEventHandler.getPlayers().length-1];
	        
	        ArrayList<Integer> list = new ArrayList<Integer>(size);
	        for(int i = 1; i <= size; i++) {
	            list.add(i);
	        }

	        Random rand = new Random();
	        while(list.size() > 0&&k<GameEventHandler.getPlayers().length-1) {
	            int index = rand.nextInt(list.size());
	            x[k]=list.remove(index);
	            k++;
	        }  
	    	
	        
	   
	        int j=0;
	        for(k=0;k<GameEventHandler.getPlayers().length-1;k++){
	        	if(!(GameEventHandler.getPlayers()[k].equals(
	        			GameEventHandler.getConnection().getConnectedPlayerName())))
	        		
	     OpponentPlayers[j] = new PlayerPanel(MunchkinClient.getImage("frameplayer"+x[j]),
	    		 GameEventHandler.getPlayers()[k],window);
	        }
	    
		
	} 
	
	public PlayerPanel[] getOpponentPlayers() {
		return OpponentPlayers;
	}




	public HandManager getHandCards() {
		return HandCards;
	}
	
	public ImageButton getDrawnCard(){
		return lblDrawnCard;
	}
	
	public DiceManager getDiceManager(){
		return Dice;
	}



	public void changeStatistics(int levelValue, int powerValue, String newClass,
			String newRace, int numCard){
		
		//update the player level and power value
		lblPlayerLevelValue.setText(String.valueOf(levelValue));
		lblPlayerPowerValue.setText(String.valueOf(powerValue));
		
		//update the player race
		BufferedImage image = MunchkinClient.getImage(newRace);
		
		if(PlayerRace.imageIsNull())
			PlayerRace.CreateCard(image, zp);
		else
			PlayerRace.setImage(image);
		
		//update the player class
		image = MunchkinClient.getImage(newClass);
		
		if(PlayerClass.imageIsNull())
			PlayerClass.CreateCard(image, zp);
		else
			PlayerClass.setImage(image);
		
		//update the number of the player hand
		lblPlayernumcard.setText(String.valueOf(numCard));
	
	}



	
	
}
	
	

