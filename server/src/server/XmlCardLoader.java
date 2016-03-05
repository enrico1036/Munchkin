package server;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cards.Card;
import cards.CardType;
import cards.Class;
import cards.Consumable;
import cards.Curse;
import cards.EquipSlot;
import cards.Equipment;
import cards.Monster;
import cards.Race;
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
	private static final String typeTag="type";
	
	
	private static final String monsterTag = "monster";
	private static final String monsterLevelTag="level";
	
	private static final String curseTag = "curse";
	private static final String curseimmTag ="immediate";
	
	private static final String consumableTag = "consumable";
	private static final String classTag = "class";
	
	private static final String raceTag = "race";
	
	private static final String equipmentTag = "equipment";
	private static final String equipmentSlotTag="slot";
	private static final String equipmentValueTag="value";
	private static final String equipmentBonusTag="bonus";


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

			// Retrieve name and type elements
			Element cardNameElem = (Element) cardElem.getElementsByTagName(nameTag).item(0);
			Element cardTypeElem = (Element) cardElem.getElementsByTagName(typeTag).item(0);
			
			//Element cardCategoryElem = (Element) cardElem.getElementsByTagName(categoryTag).item(0);

			Element cardMonsterElem = (Element) cardElem.getElementsByTagName(monsterTag).item(0);
			Element cardCurseElem = (Element) cardElem.getElementsByTagName(curseTag).item(0);
			Element cardConsumableElem = (Element) cardElem.getElementsByTagName(consumableTag).item(0);
			Element cardClassElem = (Element) cardElem.getElementsByTagName(classTag).item(0);
			Element cardRaceElem = (Element) cardElem.getElementsByTagName(raceTag).item(0);
			Element cardEquipmentElem = (Element) cardElem.getElementsByTagName(equipmentTag).item(0);

			// Continue if null
			if (cardNameElem == null || cardTypeElem == null)
				continue;

			// Store name and type
			String name = cardNameElem.getTextContent().trim();
			String type = cardTypeElem.getTextContent().trim();
			

			

			if (type == "Door") {
				if(cardMonsterElem!=null) {
					
						Element cardMonsterLevelElem = (Element) cardElem.getElementsByTagName(monsterLevelTag).item(0);
						int level = Integer.parseInt(cardMonsterLevelElem.getTextContent().trim());
						Monster loadedCard = new Monster(name, level);
						loadedCards.add(new Pair<String, Card>(name, loadedCard));
					}
					else if(cardCurseElem!=null)
						{
						Element cardCurseImmElem = (Element) cardElem.getElementsByTagName(curseimmTag).item(0);
						boolean immediate = Boolean.parseBoolean((cardCurseImmElem.getTextContent().trim()));
						Curse loadedCard = new Curse(name,immediate);
						loadedCards.add(new Pair<String, Card>(name, loadedCard));
						} 
					else if(cardConsumableElem!=null)
					{
						Consumable loadedCard = new Consumable(name, CardType.Door);
						loadedCards.add(new Pair<String, Card>(name, loadedCard));
					}
					
				

			}else if(type == "Treasure") {
				
				if(cardClassElem!=null)
				{
					Class loadedCard = new Class(name);
					loadedCards.add(new Pair<String, Card>(name, loadedCard));
				}
				else if(cardRaceElem!=null)
				{
					Race loadedCard = new Race(name);
					loadedCards.add(new Pair<String, Card>(name, loadedCard));
				} 
				else if(cardConsumableElem!=null)
				{
					Consumable loadedCard = new Consumable(name, CardType.Treasure);
					loadedCards.add(new Pair<String, Card>(name, loadedCard));
				}
				else if (cardEquipmentElem!=null)
				{
					Element cardEquipmentSlotlElem = (Element) cardElem.getElementsByTagName(equipmentSlotTag).item(0);
					Element cardEquipmentValueElem = (Element) cardElem.getElementsByTagName(equipmentValueTag).item(0);
					Element cardEquipmentBonusElem = (Element) cardElem.getElementsByTagName(equipmentBonusTag).item(0);
					
					Equipment loadedCard =new Equipment(name);
					EquipSlot slot =loadedCard.getSlot(cardEquipmentSlotlElem.getTextContent().trim());
					int value = Integer.parseInt(cardEquipmentValueElem.getTextContent().trim());
					int bonus = Integer.parseInt(cardEquipmentBonusElem.getTextContent().trim());
					loadedCard.createEquipment(slot, bonus, value);
					loadedCards.add(new Pair<String, Card>(name, loadedCard));
				}
			

				
			} else
				continue;

		}
	}
//problemone :D
	public ArrayList<Pair<String, Card>> getCards() {
		return loadedCards;
	}

}
