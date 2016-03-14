package network.message.client;

import network.message.Message;

public class PopUpResultMessage extends Message {

	private boolean button1, button2;
	private int value;
	private String sender;
	
	// It has invoked when the timeout ends
	public PopUpResultMessage(String sender) {
		this(false, 0, sender);
	}
	//It has invoked when the player click on the button1
	public PopUpResultMessage(boolean button1, String sender) {
		this(button1, false, sender);
	}
	//It has invoked when the player set the value of the Jspinner and click the button1
	public PopUpResultMessage(boolean button1, int value, String sender) {
		this(button1, false, value, sender);
	}
	//It has invoked when the player click one of the two buttons
	public PopUpResultMessage(boolean button1, boolean button2, String sender) {
		this(button1, button2, 0, sender);
	}
	//Completed constructor with all features, it is invoked when a player take a choice with a value of Jspinner
	public PopUpResultMessage(boolean button1, boolean button2, int value, String sender) {
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
	
	public String getSender() {
		return sender;
	}

}
