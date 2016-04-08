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
	private CardListPanel<ClientCard> tablePanel;
	private SelfPlayerUI selfPanel;
	private DeckPanel decksPanel;
	private CardListPanel<ClientCard> handPanel;
	public static ZoomedPanel zp;
	private PhasePanel phasePanel;
	/**
	 * Create the panel.
	 */
	public GameUI(GameWindow window, BufferedImage background) {
		super(window, background);
		setMinimumSize(new Dimension(800, 450));
		setPreferredSize(new Dimension(800, 450));

		zp = new ZoomedPanel();
		
		opponentsPanel = new OpponentsPanel(window);
		opponentsPanel.setBackground(Color.BLUE);
		opponentsPanel.setOpaque(false);

		tablePanel = new CardListPanel<>(new Dimension(100, 150), 10, 10, Data.getTable());
		tablePanel.setBackground(Color.BLUE);
		tablePanel.setOpaque(false);

		selfPanel = new SelfPlayerUI(window, GameEventHandler.getConnection().getConnectedPlayerName(), MunchkinClient.getImage("playerstats_frame"));
		selfPanel.setBackground(Color.BLUE);
		selfPanel.setOpaque(false);

		decksPanel = new DeckPanel();
		decksPanel.setBackground(Color.BLUE);
		decksPanel.setOpaque(false);

		handPanel = new CardListPanel<>(new Dimension(100, 150), 10, 10, Data.getHand());
		handPanel.setVisible(true);
		
		phasePanel = new PhasePanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(opponentsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(phasePanel, 400, 400, 400)
								.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(decksPanel, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(selfPanel, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(handPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(opponentsPanel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(phasePanel, 100, 100, 100))
						.addComponent(decksPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(handPanel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addComponent(selfPanel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		zp.setVisible(true);
		zp.setOpaque(true);
		zp.setLayout(null);
        zp.setBounds(getWidth() / 3, getHeight() / 20, getWidth() / 3, getHeight() * 5 / 8);
        add(zp);
        setComponentZOrder(zp, 0);
        //opponentsPanel.setAllSize();
       }
}
