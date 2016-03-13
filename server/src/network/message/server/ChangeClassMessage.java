package network.message.server;

import cards.ClassCard;
import cards.Equipment;
import network.message.Message;

public class ChangeClassMessage extends Message {
	
	private ClassCard selected;
	
	public ChangeClassMessage(ClassCard selected) {
		super(Message.CLT_CHANGE_CLASS);
		this.selected=selected;
		
		
	}
	
	public ClassCard getClassCard(){
		return selected;
	}
	

}

