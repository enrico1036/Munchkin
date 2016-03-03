package server;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cards.Card;
import cards.Monster;
import javafx.util.Pair;

/*
 * 	LEGAL XML FILE:
 * 	<resources>
 * 		<card name="Dads" type="Door" category="Equipment">
 * 	
 * 		<card name="Dads" type="Door" category="Monster">
 * 			<level>12</level>
 * 			
 * 		</card>
 * 		<card>
 * 			<name>	Immagine 			</name>
 * 			<type>  Tipo </type>
 * 			<category> Categoria </category>
 * 		</card>
 * 
 * 		<monster>...</monster>
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
 */

public class XmlCardLoader {

	private static final String rootTag = "resources";
	private static final String cardTag = "card";
	private static final String nameTag = "name";
	private static final String typeTag = "type";
	private static final String categoryTag = "category";

	// Tocca inserire

	private ArrayList<Pair<String, Card>> loadedCards = null;
	private Element root;

	public XmlCardLoader(File file) throws Exception {
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
		if (!root.getTagName().equals(rootTag))
			throw new Exception("Not a legal resource xml file");

		loadedCards = new ArrayList<Pair<String, Card>>();

	}

	public void load() throws Exception {

		NodeList cardList = root.getElementsByTagName(cardTag);

		for (int i = 0; i < cardList.getLength(); i++) {
			Element cardElem = (Element) cardList.item(i);

			// Retrieve name and path elements
			Element cardNameElem = (Element) cardElem.getElementsByTagName(nameTag).item(0);
			Element cardTypeElem = (Element) cardElem.getElementsByTagName(typeTag).item(0);
			Element cardCategoryElem = (Element) cardElem.getElementsByTagName(categoryTag).item(0);

			// Continue if null
			if (cardNameElem == null || cardTypeElem == null || cardCategoryElem == null)
				continue;

			// Store name
			String name = cardNameElem.getTextContent().trim();
			String type = cardTypeElem.getTextContent().trim();
			String category = cardCategoryElem.getTextContent().trim();

			Card loadedCard = null;

			if (type == "Door") {
				switch (category) {

				case "Monster":
					loadedCard = new Monster(name, level);
					break;
				case "Curse":
					break;
				case "Consumable":
					break;
				}

			} else if (type == "Treasure") {
				switch (category) {
				case "Class":
					break;
				case "Race":
					break;
				case "Consumable":
					break;
				case "Equipment":
					break;

				}
			} else
				continue;

			if (loadedCard != null)
				loadedCards.add(new Pair<String, Card>(name, loadedCard));
		}
	}

	public ArrayList<Pair<String, Card>> getCards() {
		return loadedCards;
	}

}
