package prove.InterfaceUI;

import client.MunchkinClient;

public class PlayerOpponentUI extends PlayerUI {

	
	returnBack = new ImageButton(MunchkinClient.getImage("return_back"));
	returnBack.setBounds(psw*7/8,psh*7/8,psw/8,psh/8);
	returnBack.setActionCommand("return");
	returnBack.setVisible(true);
	add(returnBack);

}
