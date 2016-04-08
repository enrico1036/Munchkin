package user_interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import client.MunchkinClient;
import dataStructure.Data;
import dataStructure.DataListener;

public class PhasePanel extends JPanel implements DataListener {

	private BufferedImage background;
	
	public PhasePanel(){
		Data.getTurn().addDataListener(this);
	}

	@Override
	public void dataChanged() {
		try {
				switch(Data.getTurn().getPhase().toString()){
					case "Equip":
						background=MunchkinClient.getImage("equip");
						
						break;
						
					case "Draw":
						background=MunchkinClient.getImage("draw");
						
						break;
						
					case "Trading":
						background=MunchkinClient.getImage("trading");
						
						break;
						
					case "AskForHelp":
						background=MunchkinClient.getImage("combat");
						
						break;
				}
		} catch (NullPointerException e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		g.drawImage(background, getWidth(), getHeight(), null);
	}
	
	
}
