package user_interface;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import client.MunchkinClient;
import network.GameEventHandler;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;

import java.awt.GridLayout;

public class OpponentsPanel extends JPanel {
	
	private HashMap<String, PlayerOpponentUI> opponentPlayers;
	GameWindow window;

	public OpponentsPanel(GameWindow window) {
		this.window = window;
		
		GameEventHandler.getReadyPlayerList();
		opponentPlayers = new HashMap<String, PlayerOpponentUI>();
		createRandomFramePanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		SequentialGroup horizontalGroup = groupLayout.createSequentialGroup();
		ParallelGroup verticalGroup = groupLayout.createParallelGroup(Alignment.CENTER);
		//TODO: try to center panels. Uncommenting PreferredGaps they will be centered but too small
		horizontalGroup.addPreferredGap(ComponentPlacement.RELATED, 788/2, Short.MAX_VALUE);
		for(PlayerOpponentUI opponent : opponentPlayers.values()) {
			horizontalGroup.addComponent(opponent, 0, 788/3, GroupLayout.PREFERRED_SIZE);
			verticalGroup.addComponent(opponent);
		}
		horizontalGroup.addPreferredGap(ComponentPlacement.RELATED, 788/2, Short.MAX_VALUE);
		groupLayout.setHorizontalGroup(horizontalGroup);
		groupLayout.setVerticalGroup(verticalGroup);
		setLayout(groupLayout);
	}

	private void createRandomFramePanel() {
		int size = 10, k = 0;
		int[] x = new int[GameEventHandler.getPlayers().length - 1];

		ArrayList<Integer> list = new ArrayList<Integer>(size);
		for (int i = 1; i <= size; i++) {
			list.add(i);
		}

		Random rand = new Random();
		while (list.size() > 0 && k < GameEventHandler.getPlayers().length - 1) {
			int index = rand.nextInt(list.size());
			x[k] = list.remove(index);
			k++;
		}

		int j = 0;
		for (k = 0; k < GameEventHandler.getPlayers().length; k++) {
			if (!(GameEventHandler.getPlayers()[k].equals(GameEventHandler.getConnection().getConnectedPlayerName()))) {

				opponentPlayers.put(GameEventHandler.getPlayers()[k], new PlayerOpponentUI(window, GameEventHandler.getPlayers()[k], MunchkinClient.getImage("frameplayer" + x[j])));
				j++;
			}
		}
	}
	

	
	public void setAllSize() {
		for(PlayerOpponentUI opponent : opponentPlayers.values()) {
			System.out.println(getWidth()/5+" "+getHeight());
			opponent.setSize(getWidth()/5, getHeight());
		}	
	}

}
