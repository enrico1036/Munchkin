package user_interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import client.MunchkinClient;
import dataStructure.Data;
import dataStructure.DataListener;

public class PhasePanel extends JPanel implements DataListener {

	private BufferedImage background;

	public PhasePanel() {
		Data.getTurn().addDataListener(this);
		dataChanged();
	}

	@Override
	public void dataChanged() {
		try {
			switch (Data.getTurn().getPhase()) {
			case Equip:
				background = MunchkinClient.getImage("equip");
				break;
			case Draw:
				background = MunchkinClient.getImage("draw");
				break;
			case Trading:
				background = MunchkinClient.getImage("trading");
				break;
			case AskForHelp:
				background = MunchkinClient.getImage("combat");
				break;
			case Charity:
				background = MunchkinClient.getImage("draw");
				break;
			case InterferInCombat:
				background = MunchkinClient.getImage("combat");
				break;
			case LookForTrouble:
				background = MunchkinClient.getImage("combat");
				break;
			case LootTheRoom:
				background = MunchkinClient.getImage("draw");
				break;
			case OpenDoor:
				background = MunchkinClient.getImage("draw");
				break;
			default:
				break;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}

}
