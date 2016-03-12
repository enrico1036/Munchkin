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

public class MunchkinClient {

	private static HashMap<String,GamePanel> panels;
	private static HashMap<String, BufferedImage> images;
	private  GameWindow window;
	public static PlayerConnection connection;

	
	public MunchkinClient() {
		// Load images from resource folder
		XmlImageLoader loader = null;
		try{
			loader = new XmlImageLoader(new File("Resources/Images/images.xml"));
			loader.load();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		images = new HashMap<String,BufferedImage>();
		for(Pair<String, BufferedImage> pair : loader.getImages()){
			images.put(pair.getKey(), pair.getValue());
		}
		panels= new HashMap<String,GamePanel>();
		//Initialize decks
		//DeckManager.loadDecks();
		
		// Initialize game window
		window = new GameWindow();
		window.setVisible(true);
		
		// Create MenuUI panel
		panels.put("MenuUI", new MenuUI(window));

		
		
		// Start game in menu ui
		window.SetActivePanel(panels.get("MenuUI"));
		
		// Create instance of PlayerConnection
		connection = new PlayerConnection();
	}

	public static JPanel getPanel(String panel) {
		return panels.get(panel);
	}
	
	public static HashMap<String,GamePanel> getPanels(){
		return panels;
	}
	
	public static BufferedImage getImage(String Name) {
		return images.get(Name);
	}
	
	public static void main(String[] args) {

		MunchkinClient client = new MunchkinClient();

		
	}

}
