/**
 * 
 */
package network.message.server;

import network.message.Message;

public class ClearAllTableMessage extends Message {

	/**
	 * @param code
	 */
	public ClearAllTableMessage() {
		super(Message.CLEAR_TBL);
	}

}
