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
		zp.setVisible(true);
		zp.setLayout(null);
        //zp.setBounds(getWidth() / 3, getHeight() / 20, getWidth() / 3, getHeight() * 5 / 8);
        add(zp);
        setComponentZOrder(zp, 0);
		
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
		phasePanel.setVisible(true);
		phasePanel.setOpaque(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.CENTER)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(opponentsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(decksPanel, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
										.addComponent(phasePanel, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(selfPanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(handPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))	
					.addContainerGap())
				.addComponent(zp, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(opponentsPanel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(decksPanel, 60, 60, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(phasePanel, 20, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED, 15, 15)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(selfPanel, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addComponent(handPanel, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
					.addGap(6))
				.addComponent(zp, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
		);
		setLayout(groupLayout);
		
		
        //opponentsPanel.setAllSize();
       }
}
