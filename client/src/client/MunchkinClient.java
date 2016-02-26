package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.swing.JPanel;

import image.XmlImageLoader;
import javafx.util.Pair;
import user_interface.GameUI;
import user_interface.GameWindow;
import user_interface.MenuUI;
import user_interface.LobbyUI;
import user_interface.PauseUI;
import user_interface.OptionUI;

public class MunchkinClient {

	private static JPanel[] panels = new JPanel[5];
	
	private static HashMap<String, BufferedImage> images;
	

	
	public static BufferedImage getImage(String Name) {
		return images.get(Name);
	}


	private  GameWindow window;
	
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
		
		//Initialize decks
		DeckManager.loadDecks();
		
		// Initialize game window
		window = new GameWindow();
		window.setVisible(true);
		
		// Setup game panels
		panels[0] = new GameUI(window);
		panels[1] = new LobbyUI(window);
		panels[2] = new MenuUI(window);
		panels[3] = new PauseUI();
		panels[4] = new OptionUI();
		
		// Start game in menu ui
		window.SetActivePanel(panels[2]);
	}

	public static JPanel getPanel(int i) {
		return panels[i];
	}

	public static void main(String[] args) {

		MunchkinClient client = new MunchkinClient();

		
	}

}
