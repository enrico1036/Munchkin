package client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import image.XmlImageLoader;
import javafx.util.Pair;
import user_interface.GameUI;
import user_interface.Game_Window;
import user_interface.MenuUI;
import user_interface.LobbyUI;
import user_interface.PauseUI;

public class MunchkinClient {

	private static JPanel[] panels = new JPanel[4];
	private static HashMap<String, BufferedImage> images;
	private  Game_Window window;
	public MunchkinClient() {
		window = new Game_Window();
		window.setVisible(true);
		panels[0] = new GameUI(window);
		panels[1] = new LobbyUI();
		panels[2] = new MenuUI(window);
		panels[3] = new PauseUI();

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

	}

	public static JPanel getPanel(int i) {
		return panels[i];
	}

	public static void main(String[] args) {

		MunchkinClient client = new MunchkinClient();

		
	}

}
