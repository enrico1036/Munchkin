/**
 * 
 */
package network.message.server;

import network.message.Message;

/**
 * Used to show a popup dialog box to the client
 * you can set the text to show, 2 buttons, and a jSpinner 
 * 
 */
public class PopUpMessage extends Message {

	String text;
	String button1, button2;
	int min_val, max_val;
	int timeout_ms;
	
	/**
	 * @param text: what to show on the dialog box
	 * @param timeout_ms: time in milliseconds to wait before message disappear and automatically click on the second button
	 */
	public PopUpMessage(String text, int timeout_ms) {
		this(text, "", "", 0, 0, timeout_ms);
	}
	
	/**
	 * @param text: what to show on the dialog box
	 * @param button1: text to write on the first button, if "" button will be disabled
	 * @param timeout_ms: time in milliseconds to wait before message disappear and automatically click on the second button
	 */
	public PopUpMessage(String text, String button1, int timeout_ms) {
		this(text, button1, "", 0, 0, timeout_ms);
	}
	
	/**
	 * @param text: what to show on the dialog box
	 * @param button1: text to write on the first button, if "" button will be disabled
	 * @param button2: text to write on the second button, if "" button will be disabled
	 * @param timeout_ms: time in milliseconds to wait before message disappear and automatically click on the second button
	 */
	public PopUpMessage(String text, String button1, String button2, int timeout_ms) {
		this(text, button1, button2, 0, 0, timeout_ms);
	}

	/**
	 * @param text: what to show on the dialog box
	 * @param button1: text to write on the first button, if "" button will be disabled
	 * @param button2: text to write on the second button, if "" button will be disabled
	 * @param min: minimum value of the jSpinner
	 * @param max: maximum value of the jSpinner
	 * @param timeout_ms: time in milliseconds to wait before message disappear and automatically click on the second button
	 */
	public PopUpMessage(String text, String button1, String button2, int min, int max, int timeout_ms) {
		super(Message.POPUP);
		this.text = text;
		this.button1 = button1;
		this.button2 = button2;
		this.timeout_ms = timeout_ms;
		this.min_val = min;
		this.max_val = max;
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
	public String getButton1() {
		return button1;
	}

	/**
	 * @return the button2
	 */
	public String getButton2() {
		return button2;
	}

	/**
	 * @return the timeout_ms
	 */
	public int getTimeout_ms() {
		return timeout_ms;
	}
	
	/**
	 * @return true if a text is set in button1
	 */
	public boolean isButton1Set() {
		return button1 != "";
	}
	
	/**
	 * @return true if a text is set in button2
	 */
	public boolean isButton2Set() {
		return button2 != "";
	}
	
	/**
	 * @return true if min_val is different from max_val
	 */
	public boolean isValueSet() {
		return min_val != max_val;
	}

	public int getMin_val() {
		return min_val;
	}

	public int getMax_val() {
		return max_val;
	}

	
}
