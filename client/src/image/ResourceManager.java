package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javafx.util.Pair;

public class ResourceManager {
	private static HashMap<String, BufferedImage> images;

	public static void loadAll() {
		// Load images from resource folder
		XmlImageLoader loader = null;
		try {
			loader = new XmlImageLoader(new File("Resources/Images/images.xml"));
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		images = new HashMap<String, BufferedImage>();
		for (Pair<String, BufferedImage> pair : loader.getImages()) {
			images.put(pair.getKey(), pair.getValue());
		}
	}

	public static BufferedImage getImage(String Name) {
		return images.get(Name);
	}

}
