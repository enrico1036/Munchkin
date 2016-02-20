package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import image.XmlImageLoader;
import javafx.util.Pair;
import user_interface.GameUI;
import user_interface.GameWindow;
import user_interface.MenuUI;
import user_interface.LobbyUI;
import user_interface.PauseUI;

public class MunchkinClient {

	private static JPanel[] panels = new JPanel[4];
	
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
		
		// Initialize game window
		window = new GameWindow();
		window.setVisible(true);
		
		// Setup game panels
		panels[0] = new GameUI();
		panels[1] = new LobbyUI();
		panels[2] = new MenuUI();
		panels[3] = new PauseUI();
		
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
