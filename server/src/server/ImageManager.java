package server;

import java.io.File;
import java.util.HashMap;

import javafx.util.Pair;

public class ImageManager {
	private static HashMap<String, String> cards;

	public static void loadAll() {
		// Load images from resource folder
		XmlCardLoader loader = null;
		try {
			loader = new XmlCardLoader(new File("Resources/cards.xml"));
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		cards = new HashMap<String, String>();
		for (Pair<String, String> pair : loader.getCards()) {
			cards.put(pair.getKey(), pair.getValue());
		}
	}

	public static String getCard(String Name) {
		return cards.get(Name);
	}

}
