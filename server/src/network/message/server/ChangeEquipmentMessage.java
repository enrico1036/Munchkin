package network.message.server;

import cards.Equipment;
import network.message.Message;

public class ChangeEquipmentMessage extends Message {

	private Equipment selected;
	
	public ChangeEquipmentMessage(Equipment selected) {
		super(Message.CLT_CHANGE_EQUIPMENT);
		this.selected=selected;
		
		
	}
	
	public Equipment getEquipment(){
		return selected;
	}
	

}
