package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.swing.JPanel;

import image.XmlImageLoader;
import javafx.util.Pair;
import network.PlayerConnection;
import user_interface.GameUI;
import user_interface.GameWindow;
import user_interface.MenuUI;
import user_interface.LobbyUI;
import user_interface.ShowPlayers;
import user_interface.GamePanel;
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
	private static HashMap<String, BufferedImage> images;
	private static  GameWindow window;
	public static PlayerConnection connection;

	
	public static void startGame() {
		/** 
		 * Load images from resource folder
		 * Throw an exception for every exception that this loading can generate
		 */
		XmlImageLoader loader = null;
		try{
			loader = new XmlImageLoader(new File("Resources/Images/images.xml"));
			loader.load();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		/**
		 * Put,from the xmlImageLoader every image in the HashMap images
		 */
		images = new HashMap<String,BufferedImage>();
		for(Pair<String, BufferedImage> pair : loader.getImages()){
			images.put(pair.getKey(), pair.getValue());
		}
		panels= new HashMap<String,GamePanel>();
		
		//TODO COSA E' STA ROBBA????
		//Initialize decks
		//DeckManager.loadDecks();
		
		/**
		 * Initialize game window (JFrame)
		 */
		window = new GameWindow();
		window.setVisible(true);
		
		/**
		 *  Create MenuUI panel
		 */
		panels.put("MenuUI", new MenuUI(window));
		
		
		
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
	public static BufferedImage getImage(String Name) {
		return images.get(Name);
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
