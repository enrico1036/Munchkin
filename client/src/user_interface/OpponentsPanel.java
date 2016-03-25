package user_interface;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;

import client.MunchkinClient;
import network.GameEventHandler;

public class OpponentsPanel extends JPanel {
	
	private HashMap<String, PlayerOpponentUI> opponentPlayers;
	GameWindow window;

	public OpponentsPanel(GameWindow window) {
		super(new FlowLayout(FlowLayout.CENTER));
		this.window = window;
		GameEventHandler.getReadyPlayerList();
		opponentPlayers = new HashMap<String, PlayerOpponentUI>();
		createRandomFramePanel();
		setOpaque(false);
		for(PlayerOpponentUI opponent : opponentPlayers.values()) {
			add(opponent);
		}
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

}
