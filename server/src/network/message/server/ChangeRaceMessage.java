package network.message.server;

import cards.ClassCard;
import cards.Race;
import network.message.Message;

public class ChangeRaceMessage extends Message {

	private Race selected;
	
	public ChangeRaceMessage(Race selected) {
		super(Message.CLT_CHANGE_RACE);
		this.selected=selected;
		
		
	}
	
	public Race getEquipment(){
		return selected;
	}
	
}
