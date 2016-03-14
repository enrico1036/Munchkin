package network.message.server;

import network.message.Message;

public class DiceResultMessage extends Message {

	private int num;
	
	public DiceResultMessage(int num) {
		super(Message.DICE_RESULT);
		this.num = num;
		}
	
	public int getResult() {
		return num;
	}

}
