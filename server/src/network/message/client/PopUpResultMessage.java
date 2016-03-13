package network.message.client;

import network.message.Message;

public class PopUpResultMessage extends Message {

	boolean button1, button2;
	int value;
	
	public PopUpResultMessage() {
		this(false, 0);
	}
	
	public PopUpResultMessage(boolean button1, int value) {
		this(button1, false, value);
	}
	
	public PopUpResultMessage(boolean button1, boolean button2, int value) {
		super(Message.CLT_POPUP_RESULT);
		if(button1) {
			this.button1 = true;
			this.button2 = false;
		} else if(button2){
			this.button1 = false;
			this.button2 = true;
		} else {
			this.button1 = false;
			this.button2 = false;
		}
		this.value = value;
	}
	
	/**
	 * @return true if button1 was pressed
	 */
	public boolean isButton1Pressed() {
		return button1;
	}
	
	/**
	 * @return true button2 was pressed
	 */
	public boolean isButton2Pressed() {
		return button2;
	}
	
	/**
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	public boolean isTimedOut() {
		return !button1 && !button2;
	}

}
