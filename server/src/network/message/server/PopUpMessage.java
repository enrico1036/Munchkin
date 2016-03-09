/**
 * 
 */
package network.message.server;

import network.message.Message;

/**
 * Used to show a popup dialog box to the client
 *
 */
public class PopUpMessage extends Message {

	String text;
	boolean button1, button2;
	int timeout_ms;
	
	/**
	 * 
	 * @param text: what to show on the dialog box
	 * @param button1: true if the first button should be active, false otherwise
	 * @param button2: true if the second button should be active, false otherwise
	 * @param timeout_ms: time in milliseconds to wait before message disappear and automatically click on the second button
	 */
	public PopUpMessage(String text, boolean button1, boolean button2, int timeout_ms) {
		super(Message.POPUP);
		this.text = text;
		this.button1 = button1;
		this.button2 = button2;
		this.timeout_ms = timeout_ms;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the button1
	 */
	public boolean getButton1() {
		return button1;
	}

	/**
	 * @return the button2
	 */
	public boolean getButton2() {
		return button2;
	}

	/**
	 * @return the timeout_ms
	 */
	public int getTimeout_ms() {
		return timeout_ms;
	}

	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(text);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(Boolean.toString(button1));
		builder.append(Message.ARG_SEPARATOR);
		builder.append(Boolean.toString(button2));
		builder.append(Message.ARG_SEPARATOR);
		builder.append(Integer.toString(timeout_ms));
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}

}
