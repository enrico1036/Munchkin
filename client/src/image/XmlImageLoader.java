package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.util.Pair;

/**
 * 	LEGAL XML FILE:
 * 	<resources>
 * 		<image>
 * 			<name>	Immagine 			</name>
 * 			<path>  /home/User/Pictures </path>
 * 		</image>
 * 		...
 * 		...
 * 		...
 * 	</resources>
 * 
 * 	HOW TO USE THIS:
 * 	Create an instance of this class passing a File handle
 *  Wrap both constructor and load() into a try catch
 *  Retrieve resulting array with getImages() and check 
 *  with getNotLoadedInfo() any errors
 **/

public class XmlImageLoader {
	
	private static final String rootTag = "resources";
	private static final String imageTag = "image";
	private static final String nameTag = "name";
	private static final String pathTag = "path";
	
	private static HashMap<String, BufferedImage> loadedImages = null;
	private static HashMap<String, String> notLoaded = null;
	private static Element root;
	
	public static void loadsource(File file) throws Exception{
		// Use a document builder in order to get a document
		// from the xml input file
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(file);

		// Optimize document tree by removing empty nodes
		doc.getDocumentElement().normalize();
		
		// Retrieve root element
		root = doc.getDocumentElement();
		
		// Check if given file is a legal resource xml file
		if(!root.getTagName().equals(rootTag))
			throw new Exception("Not a legal resource xml file");	
		
		loadedImages = new HashMap<String, BufferedImage>();
		notLoaded = new HashMap<String, String>();
		
	}
	
	public static void load() throws Exception{
		// Retrieve children images
		NodeList imageList = root.getElementsByTagName(imageTag);
		
		// Iterate over each image tag and retrieve image info
		for (int i=0; i<imageList.getLength(); i++){
			Element imageElem = (Element) imageList.item(i);
			
			// Retrieve name and path elements
			Element imageNameElem = (Element) imageElem.getElementsByTagName(nameTag).item(0);
			Element imagePathElem = (Element) imageElem.getElementsByTagName(pathTag).item(0);
			
			// Continue if null
			if(imageNameElem == null || imagePathElem == null)
				continue;
			
			// Store name
			String name = imageNameElem.getTextContent().trim();
			String path = imagePathElem.getTextContent().trim();
			
			// Try to load and store image; if it's not possible,
			// add its info to the "not loaded" array
			BufferedImage image = null;
			try{
				image = ImageIO.read(new File(path));
				loadedImages.put(name, image);
			} catch (IOException e){
				notLoaded.put(name, path);
			}
		}
	}
	
	public static void loadSingleImage(String imageName)throws Exception{
	
		
			// Retrieve children images
				NodeList imageList = root.getElementsByTagName(imageTag);
			
				boolean founded=false;
				
				// Iterate over each image tag and retrieve image info
				for (int i=0; (i<imageList.getLength())&&(!founded); i++){
					Element imageElem = (Element) imageList.item(i);
					
					// Retrieve name and path elements
					Element imageNameElem = (Element) imageElem.getElementsByTagName(nameTag).item(0);
					Element imagePathElem = (Element) imageElem.getElementsByTagName(pathTag).item(0);
					
					// Continue if null
					if(imageNameElem == null || imagePathElem == null)
						continue;
					
					// Store name
					String name = imageNameElem.getTextContent().trim();
					String path = imagePathElem.getTextContent().trim();
					
										
					if(name.equals(imageName)){
						
						founded=true;
						
						// Try to load and store image; if it's not possible,
						// add its info to the "not loaded" array
						BufferedImage image = null;
						try{
							image = ImageIO.read(new File(path));
							loadedImages.put(name, image);
						} catch (IOException e){
							notLoaded.put(name, path);
						}
					}
				}
	}
	
	
	public static HashMap<String,BufferedImage> getImages(){
		return loadedImages;
	}
	
	public static  HashMap<String,String> getNotLoadedInfo(){
		return notLoaded;
	}
	
	
}
