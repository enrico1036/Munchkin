package dataStructure;

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
		//TODO: send message to server with dragFrom and caller
	}

}
