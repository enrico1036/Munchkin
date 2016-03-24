package prove.InterfaceUI;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import client.MunchkinClient;
import user_interface.GameUI;

public class PlayerOpponentUI extends PlayerUI {

	private ImageButton stateswitcher;
	private int psw,psh;
	private boolean detail=false;
	private ZoomedPanel zp;
	
	
	public PlayerOpponentUI(GameWindow window,String playerName,BufferedImage background){
		super(window,playerName, background);
	
	psw=getPsw();
	psh=getPsh();
	
	zp=((GameUI)(MunchkinClient.getPanel("GameUI"))).zp;
		
	stateswitcher = new ImageButton(MunchkinClient.getImage("return_back"));
	stateswitcher.setBounds(psw*7/8,psh*7/8,psw/8,psh/8);
	stateswitcher.setActionCommand("detailed");
	stateswitcher.setVisible(true);
	add(stateswitcher);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand()=="detailed"){
			detail=!detail;
			if(detail){
				//Open the detail panel
			add(super.getEquipment());
			super.getStatistics().setBounds(0,0,psw/2,psh);
			super.getEquipment().setBounds(psw/2, 0, psw/2, psh);
			//TODO  prendi il glass glass.disegna(this)
			}else{
				remove(super.getEquipment());
				super.getStatistics().setBounds(0,0,psw,psh);
				//TODO prendi il glass e fai glass.disegna(this)
			}
			
		}
	}
}
