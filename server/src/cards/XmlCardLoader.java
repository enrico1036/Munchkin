package cards;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * 	LEGAL XML FILE:
 *	<cards>
 *		
 *		<doors>
 *			<monster name="mmm" level="00" earningLevels="01" earningTreasures="02" effect="eee">
 *			<monster name="nnn" level="01" effect="fff">
 *			<curse name="ccc" immediate="true" effect="mario">
 *			<consumable name="nnn" effect="dsad">
 *		</doors>
 
 *		<treasures>
 *			<consumable name="nnn" effect="dsad">
 *			<equipment name="qqq" slot="head" strength="4" value="500">
 *		</treasures>
 *
 *	</cards>
 */

//TODO: Add effects, badthings and de-effects loading

public class XmlCardLoader {
	// Tag constant strings
	private static final String rootTag = "cards";
	private static final String doorTag = "doors";
	private static final String treasureTag = "treasures";
	private static final String monsterTag = "monster";
	private static final String curseTag = "curse";
	private static final String consumableTag = "consumable";
	private static final String classTag = "class";
	private static final String raceTag = "race";
	private static final String equipmentTag = "equipment";

	// Attribute constant strings
	private static final String nameAttr = "name";
	private static final String levelAttr = "level";
	private static final String effectAttr = "effect";
	private static final String badThingsAttr = "badThings";
	private static final String curseImmAttr = "immediate";
	private static final String consCombatAttr="onlyCombat";
	private static final String slotAttr = "slot";
	private static final String valueAttr = "value";
	private static final String bonusAttr = "bonus";
	private static final String earningLevelAttr = "earningLevels";
	private static final String earningTreasuresAttr = "earningTreasures";

	// These two arrays will contain loaded doors and treasures to be retrieved
	// separately
	private ArrayList<Card> loadedDoors = null;
	private ArrayList<Card> loadedTreasures = null;
	private Element root;

	public XmlCardLoader(File file) throws Exception {
		// Use a document builder in order to get a document
		// from the xml input file
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(file);

		// Retrieve root element
		root = (Element) doc.getDocumentElement();

		// Check if given file is a legal resource xml file
		if (!root.getTagName().equals(rootTag))
			throw new Exception("Not a legal card xml file");

		loadedDoors = new ArrayList<>();
		loadedTreasures = new ArrayList<>();
	}

	public void load() throws Exception {

		/*
		 * LOAD DOORS
		 */
		NodeList cardList = root.getElementsByTagName(doorTag).item(0).getChildNodes();
		

		// Loop every child
		for (int i = 0; i < cardList.getLength(); i++) {
			// Retrieve element
			Element elem = null;
			if (cardList.item(i).getNodeType() != Node.TEXT_NODE) {
				elem = (Element) cardList.item(i);
			} else {
				continue;
			}
			// Retrieve common attributes
			String name = elem.getAttribute(nameAttr);
			//String effect = elem.getAttribute(effectAttr);

			// Do not add cards without a valid name
			if (name.isEmpty())
				continue;

			Card doorCard = null;

			// Switch over tag
			switch (elem.getTagName()) {
			case monsterTag:
				try {
					int level = Integer.parseInt(elem.getAttribute(levelAttr));
					int earningLevel = Integer.parseInt(elem.getAttribute(earningLevelAttr));
					int earningTreasures = Integer.parseInt(elem.getAttribute(earningTreasuresAttr));
					Monster card= new Monster(name, level, earningLevel, earningTreasures);
					
					NodeList monsterNode = root.getElementsByTagName(monsterTag).item(0).getChildNodes();
					
					for(int j=0;j<monsterNode.getLength();j++){
						Element cardElem = null;
						if (monsterNode.item(j).getNodeType() != Node.TEXT_NODE) {
							cardElem= (Element) monsterNode.item(j);
							if(monsterNode.item(j).getNodeValue().equals(badThingsAttr)){
								String effectName= cardElem.getAttribute(nameAttr);
								HashMap<String,String> badThings= new HashMap<>();
							
								NodeList badThingsNode = root.getElementsByTagName(badThingsAttr).item(j).getChildNodes();
							
									for(int k=0;k<badThingsNode.getLength();k++){
										Element effectElem = (Element) badThingsNode.item(k);
										String nodeName=effectElem.getTagName();
										String nodeValue=effectElem.getNodeValue();
										badThings.put(nodeName, nodeValue);
									}
									card.badThings().put(effectName, badThings);
									
							}else if(monsterNode.item(j).getNodeValue().equals(effectAttr)){
								HashMap<String,String> buff = new HashMap<>();
								String buffName= cardElem.getAttribute(nameAttr);
								
								NodeList buffNode = root.getElementsByTagName(effectAttr).item(j).getChildNodes();
								
								for(int k=0;k<buffNode.getLength();k++){
									Element buffElem = (Element) buffNode.item(k);
									String nodeName=buffElem.getTagName();
									String nodeValue=buffElem.getNodeValue();
									buff.put(nodeName, nodeValue);
								}
								card.badThings().put(buffName, buff);
								
							}
						}
					}
					doorCard = card;
				} catch (NumberFormatException e) {
					// Level was not a number
				}
				break;

			case curseTag:
				boolean immediate = Boolean.parseBoolean(elem.getAttribute(curseImmAttr));
				doorCard = new Curse(name, immediate);
				break;

			case classTag:
				doorCard = new ClassCard(name);
				break;

			case raceTag:
				doorCard = new Race(name);
				break;

			case consumableTag:
				boolean onlyCombat = Boolean.parseBoolean(elem.getAttribute(consCombatAttr));
				doorCard = new Consumable(name, CardType.Door,onlyCombat);
				break;

			default:
				break;
			}

			// Load to array if not null
			if (doorCard != null)
				loadedDoors.add(doorCard);
		}

		/*
		 * LOAD TREASURES
		 */
		cardList = root.getElementsByTagName(treasureTag).item(0).getChildNodes();

		// Loop every child
		for (int i = 0; i < cardList.getLength(); i++) {
			// Retrieve element
			Element elem = null;
			if (cardList.item(i).getNodeType() != Node.TEXT_NODE) {
				elem = (Element) cardList.item(i);
			} else {
				continue;
			}

			// Retrieve common attributes
			String name = elem.getAttribute(nameAttr);
			String effect = elem.getAttribute(effectAttr);

			// Do not add cards without a valid name
			if (name.isEmpty())
				continue;

			Card treasureCard = null;

			// Switch over tag
			switch (elem.getTagName()) {

			case consumableTag:
				boolean onlyCombat = Boolean.parseBoolean(elem.getAttribute("onlyCombat"));
				treasureCard = new Consumable(name, CardType.Treasure, onlyCombat);
				break;

			case equipmentTag:
				try {
					EquipSlot slot = EquipSlot.parse(elem.getAttribute(slotAttr));
					int bonus = Integer.parseInt(elem.getAttribute(bonusAttr));
					int value = Integer.parseInt(elem.getAttribute(valueAttr));
					treasureCard = new Equipment(name, slot, bonus, value);
				} catch (NumberFormatException e) {
					// value or bonus were not a number
				}

			default:
				break;
			}

			// Load to array if not null
			if (treasureCard != null)
				loadedTreasures.add(treasureCard);
		}
	}

	public ArrayList<Card> getDoors() {
		return loadedDoors;
	}

	public ArrayList<Card> getTreasures() {
		return loadedTreasures;
	}

}
