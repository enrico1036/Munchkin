package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.swing.JPanel;

import image.XmlImageLoader;
import javafx.util.Pair;
import network.PlayerConnection;
import user_interface.GamePanel;
import user_interface.GameUI;
import user_interface.GameWindow;
import user_interface.LobbyUI;
import user_interface.MenuUI;

/**
 * 
 * The class application of the Client Program
 *
 */
public class MunchkinClient {
/**
 * @param panels: is a hashMap that contains all GamePanel (Jpanel) of the User Interface
 * @param images: is the hashMap that contains all BufferedImage of the UI utility and the all cards
 * @param window: is the JFrame of the User Interface
 * @param connection: is the PlyaerConnection of this client player
 */
	private static HashMap<String,GamePanel> panels;
	private static  GameWindow window;
	public static PlayerConnection connection;
	
	
	public static void startGame() {
		/** 
		 * Load images from resource folder
		 * Throw an exception for every exception that this loading can generate
		 */
		 ;
		try{
			XmlImageLoader.loadsource(new File("Resources/Images/images.xml"));
			//loader.load();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		/*
		// Print the images not loaded
		for(Pair<String,String> str : loader.getNotLoadedInfo()){
			System.out.println("Not loaded: " + str.getKey() + " in " + str.getValue());
		}
		*/
		/**
		 * Put,from the xmlImageLoader every images in the HashMap images
		 */
		/*
		images = new HashMap<String,BufferedImage>();
		for(Pair<String, BufferedImage> pair : loader.getImages()){
			images.put(pair.getKey(), pair.getValue());
		}*/
		
		
		panels= new HashMap<String,GamePanel>();
		
	
		
		/**
		 * Initialize game window (JFrame)
		 */
		window = new GameWindow();
		window.setVisible(true);
		
		/**
		 *  Create MenuUI panel
		 */
		panels.put("MenuUI", new MenuUI(window, MunchkinClient.getImage("menu_background")));
		
		
		
		/**
		 *  Start game in menu ui with the method of JFrame that set as active panel MenuUI
		 */
		window.SetActivePanel(panels.get("MenuUI"));
		
		/**
		 *  Create instance of PlayerConnection
		 *  for this player
		 */
		connection = new PlayerConnection();
	}

	/**
	 * It's used to get one panel with the name -->panel 
	 * @param panel: it's the name of the panel that we want to use
	 * @return the seleced panel
	 */
	public static JPanel getPanel(String panel) {
		return panels.get(panel);
	}
	/**
	 * It's used to get the panels hashMap
	 * @return an hashMap that contains all GamePanel
	 */
	public static HashMap<String,GamePanel> getPanels(){
		return panels;
	}
	/**
	 * It's used to get an image from the images HashMap
	 * @param Name: it's the name of the image that we want to use
	 * @return an image that has the same title of the param Name
	 */
	public static BufferedImage getImage(String name) {
		BufferedImage image=null;
		
		image=XmlImageLoader.getImages().get(name);
		if(image==null){
			
			try {
				XmlImageLoader.loadSingleImage(name);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		return XmlImageLoader.getImages().get(name);
	}
	/**
	 * It's used to get the active gameWindow
	 * @return the active gameWindow 
	 */
	public static GameWindow getWindow(){
		return window;
	}
	
	public static void main(String[] args) {

		MunchkinClient.startGame();

		
	}

}
