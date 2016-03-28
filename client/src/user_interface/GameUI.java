package user_interface;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import client.MunchkinClient;
import dataStructure.Data;
import game.GameManager;
import network.GameEventHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class GameUI extends GamePanel{

	private OpponentsPanel opponentsPanel;
	private CardPanel tablePanel;
	private SelfPlayerUI selfPanel;
	private DeckPanel decksPanel;
	private CardPanel handPanel;
	public static final ZoomedPanel zp = new ZoomedPanel();

	/**
	 * Create the panel.
	 */
	public GameUI(GameWindow window, BufferedImage background) {
		super(window, background);
		setMinimumSize(new Dimension(800, 450));
		setPreferredSize(new Dimension(800, 450));

		opponentsPanel = new OpponentsPanel(window);
		opponentsPanel.setBackground(Color.BLUE);

		tablePanel = new CardPanel(Data.getTable());
		tablePanel.setBackground(Color.BLUE);

		selfPanel = new SelfPlayerUI(window, GameEventHandler.getConnection().getConnectedPlayerName(), MunchkinClient.getImage("playerstats_frame"));
		selfPanel.setBackground(Color.BLUE);

		decksPanel = new DeckPanel();
		decksPanel.setBackground(Color.BLUE);

		handPanel = new CardPanel(Data.getHand());
		handPanel.setBackground(Color.BLUE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(opponentsPanel, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING, 
										groupLayout.createSequentialGroup()
										.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(decksPanel, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(selfPanel, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(handPanel, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
										.addGap(6)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(opponentsPanel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE).addComponent(decksPanel, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)).addGap(6).addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(selfPanel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE).addComponent(handPanel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)).addGap(6)));
		setLayout(groupLayout);
		
		zp.setVisible(true);
		zp.setLayout(null);
        zp.setBounds(getWidth() / 3, getHeight() / 20, getWidth() / 3, getHeight() * 5 / 8);
        add(zp);
        setComponentZOrder(zp, 0);
        //opponentsPanel.setAllSize();
       }
}
