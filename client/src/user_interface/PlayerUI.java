package user_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import cards.EquipSlot;
import cards.Equipment;
import client.MunchkinClient;
import dataStructure.Data;
import dataStructure.DataListener;
import dataStructure.PlayerData;
import dataStructure.SharedData;
import dataStructure.TurnData;
import user_interface.GameUI;
import game.GameManager;
import network.GameEventHandler;
import cards.EquipSlot;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;

import java.awt.GridLayout;

public class PlayerUI extends GamePanel implements DataListener {

	// ---------Player JComponents------------

	private String playerName;
	private EquipmentPanel equipment;
	private StatisticsPanel statistics;
	private ZoomedPanel zp;
	protected GroupLayout groupLayout;

	public PlayerUI(GameWindow window, String playerName, BufferedImage bg) {
		super(window, bg);

		this.playerName = playerName;
		Data.getPlayer(playerName).addDataListener(this);
		Data.getTurn().addDataListener(this);
		
		setOpaque(false);

		zp = ((GameUI) (MunchkinClient.getPanel("GameUI"))).zp;
		
		statistics = new StatisticsPanel(playerName);
		equipment = new EquipmentPanel();
		
		groupLayout = new GroupLayout(this);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setAutoCreateGaps(true);		
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addComponent(statistics)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(statistics)
				);
		setLayout(groupLayout);
	
		dataChanged();
	}



	public EquipmentPanel getEquipment() {
		return equipment;
	}

	public StatisticsPanel getStatistics() {
		return statistics;
	}

	@Override
	public void dataChanged() {

		// update the player level and power value
		statistics.getLblPlayerLevelValue().setText(String.valueOf(Data.getPlayer(playerName).getLevel()));
		statistics.getLblPlayerPowerValue().setText(String.valueOf(Data.getPlayer(playerName).getCombatLevel()));
		//rePaintMyComponent((JComponent)statistics.getLblPlayerLevelValue());
		//rePaintMyComponent((JComponent)statistics.getLblPlayerPowerValue());
		// update the player race
		BufferedImage image = MunchkinClient.getImage(Data.getPlayer(playerName).getRaceCard());

		if (statistics.getPlayerRace().zoomedPanelIsNull())
			statistics.getPlayerRace().setZoomedPanel(zp);
			statistics.getPlayerRace().setImage(image);
			//rePaintMyComponent((JComponent)statistics.getPlayerRace());

		// update the player class
		image = MunchkinClient.getImage(Data.getPlayer(playerName).getClassCard());

		if (statistics.getPlayerClass().zoomedPanelIsNull())
			statistics.getPlayerClass().setZoomedPanel(zp);
			statistics.getPlayerClass().setImage(image);
			//rePaintMyComponent((JComponent)statistics.getPlayerClass());

		// update the size of the player hand
		statistics.getLblNumCards().setText(String.valueOf(Data.getPlayer(playerName).getHandSize()));
		//rePaintMyComponent((JComponent)statistics.getLblNumCards());
		
		equipment.getPlayerHead().setImage(MunchkinClient.getImage(Data.getPlayer(playerName).getEquipment(EquipSlot.head)));
		equipment.getPlayerHand1().setImage(MunchkinClient.getImage(Data.getPlayer(playerName).getEquipment(EquipSlot.hand1)));
		equipment.getPlayerHand2().setImage(MunchkinClient.getImage(Data.getPlayer(playerName).getEquipment(EquipSlot.hand2)));
		equipment.getPlayerBody().setImage(MunchkinClient.getImage(Data.getPlayer(playerName).getEquipment(EquipSlot.body)));
		equipment.getPlayerFeet().setImage(MunchkinClient.getImage(Data.getPlayer(playerName).getEquipment(EquipSlot.feet)));
		/*
		rePaintMyComponent((JComponent)equipment.getPlayerHead());
		rePaintMyComponent((JComponent)equipment.getPlayerHand1());
		rePaintMyComponent((JComponent)equipment.getPlayerHand2());
		rePaintMyComponent((JComponent)equipment.getPlayerBody());
		rePaintMyComponent((JComponent)equipment.getPlayerFeet());
		*/
		
		/**
		 * if-else statement that set as red, otherwise black,
		 *  the color of the PlayerName Label,if that player is the current.
		 * 
		 */
		if(Data.getPlayer(playerName).getUsername().equals(Data.getTurn().getCurrentPlayer())){
			statistics.getLblPlayerName().setForeground(Color.RED);
		}else
		{
			statistics.getLblPlayerName().setForeground(Color.BLACK);
		}
		
		
		MunchkinClient.getWindow().setSize(MunchkinClient.getWindow().getWidth(), MunchkinClient.getWindow().getHeight()+1);
		MunchkinClient.getWindow().setSize(MunchkinClient.getWindow().getWidth(), MunchkinClient.getWindow().getHeight()-1);
		
	}
	

	public void rePaintMyComponent(JComponent item){
		Runnable target = new Runnable() {
			@Override
			public void run() {
				item.repaint();
				
			}
			};
			
			try {
			SwingUtilities.invokeAndWait(target);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			}
}
