package dataStructure;

import network.GameEventHandler;

public class Actions {
	private static String dragFrom;
	
	/**
	 * Store who started drag action
	 * @param caller
	 */
	public static void mouseDown(String caller) {
		dragFrom = caller;
	}
	
	/**
	 * When this method is called it means that a drag and drop was performed, 
	 * so tell to server who was dragged and where
	 * @param caller
	 */
	public static void mouseUp(String caller) {
		if(dragFrom.equals(caller))
			//card was clicked so send SelectedCardMessage
			GameEventHandler.selectCard(caller);
	}
	/**
	 * When this method is called it means that a click was performed over and item
	 * @param caller
	 */
	public static void mouseClicked(String caller) {
		GameEventHandler.selectCard(caller);
	}
	

}
