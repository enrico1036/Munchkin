package prove.InterfaceUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;

import cards.EquipSlot;
import cards.Equipment;
import client.MunchkinClient;
import dataStructure.Data;
import dataStructure.DataListener;
import dataStructure.PlayerData;
import dataStructure.SharedData;
import user_interface.GameUI;
import game.GameManager;
import network.GameEventHandler;
import cards.EquipSlot;

public class PlayerUI extends GamePanel implements DataListener {

	// ---------Player JComponents------------

	private PlayerData player;

	private String playerName;

	private int psw, psh;

	private EquipmentPanel equipment;

	private StatisticsPanel statistics;

	private ZoomedPanel zp;

	public PlayerUI(GameWindow window, String Player, BufferedImage bg) {
		super(window, bg);
		playerName = Player;

		player = Data.getPlayer(playerName);

		player.addDataListener(this);

		zp = ((GameUI) (MunchkinClient.getPanel("GameUI"))).zp;

		psw = getWidth();
		psh = getHeight();

		/*
		 * EQUIPMENT PANEL AND ITS COMPONENTS
		 */
		equipment = new EquipmentPanel(psw, psh);
		/*
		 * PLAYERSTATS PANEL AND ITS COMPONENTS
		 */
		statistics = new StatisticsPanel(playerName, psw, psh);

	}

	public int getPsw() {
		return psw;
	}

	public int getPsh() {
		return psh;
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
		statistics.getLblPlayerLevelValue().setText(String.valueOf(player.getLevel()));
		statistics.getLblPlayerPowerValue().setText(String.valueOf(player.getCombatLevel()));

		// update the player race
		BufferedImage image = MunchkinClient.getImage(player.getRaceCard());

		if (statistics.getPlayerRace().imageIsNull())
			statistics.getPlayerRace().CreateCard(image, zp);
		else
			statistics.getPlayerRace().setImage(image);

		// update the player class
		image = MunchkinClient.getImage(player.getClassCard());

		if (statistics.getPlayerClass().imageIsNull())
			statistics.getPlayerClass().CreateCard(image, zp);
		else
			statistics.getPlayerClass().setImage(image);

		// update the size of the player hand
		statistics.getLblPlayerNumCards().setText(String.valueOf(player.getHandSize()));

		equipment.getPlayerHead().setImage(MunchkinClient.getImage(player.getEquipment(EquipSlot.head)));
		equipment.getPlayerHand1().setImage(MunchkinClient.getImage(player.getEquipment(EquipSlot.hand1)));
		equipment.getPlayerHand2().setImage(MunchkinClient.getImage(player.getEquipment(EquipSlot.hand2)));
		equipment.getPlayerBody().setImage(MunchkinClient.getImage(player.getEquipment(EquipSlot.body)));
		equipment.getPlayerFeet().setImage(MunchkinClient.getImage(player.getEquipment(EquipSlot.feet)));

	}

}
