package dataStructure;

import java.util.HashMap;

import cards.EquipSlot;
import cards.Equipment;

public class PlayerData extends SharedData{

	private String username;
	private int level;
	private int combatLevel;
	private String raceCard;
	private String classCard;
	private int handSize;
	private HashMap<EquipSlot, String> equipments;
	
	public PlayerData() {
		username = "";
		level = 0;
		combatLevel = 0;
		raceCard = "";
		classCard = "";
		handSize = 0;
		equipments = new HashMap<EquipSlot, String>();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
		update();
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
		update();
	}

	/**
	 * @return the combatLevel
	 */
	public int getCombatLevel() {
		return combatLevel;
	}

	/**
	 * @param combatLevel the combatLevel to set
	 */
	public void setCombatLevel(int combatLevel) {
		this.combatLevel = combatLevel;
		update();
	}

	/**
	 * @return the raceCard
	 */
	public String getRaceCard() {
		return raceCard;
	}

	/**
	 * @param raceCard the raceCard to set
	 */
	public void setRaceCard(String raceCard) {
		this.raceCard = raceCard;
		update();
	}

	/**
	 * @return the classCard
	 */
	public String getClassCard() {
		return classCard;
	}

	/**
	 * @param classCard the classCard to set
	 */
	public void setClassCard(String classCard) {
		this.classCard = classCard;
		update();
	}

	/**
	 * @return the handSize
	 */
	public int getHandSize() {
		return handSize;
	}

	/**
	 * @param handSize the handSize to set
	 */
	public void setHandSize(int handSize) {
		this.handSize = handSize;
		update();
	}
	
	/**
	 * 
	 * @param slot
	 * @return
	 */
	public String getEquipment(EquipSlot slot) {
		return equipments.get(slot);
	}
	
	/**
	 * 
	 * @param slot
	 * @param name
	 */
	public void setSingleEquipment(EquipSlot slot, String name) {
		equipments.put(slot, name);
		update();
	}
	
	public void setEquipments(String head,String hand1,String hand2,String body,String feet){
		this.equipments.put(EquipSlot.head, head);
		this.equipments.put(EquipSlot.hand1, hand1);
		this.equipments.put(EquipSlot.hand2, hand2);
		this.equipments.put(EquipSlot.body, body);
		this.equipments.put(EquipSlot.feet, feet);
	}
	
	/**
	 * 
	 * 
	 */
	public void setStats(int handSize,String classCard,String raceCard,int combatLevel,int level){
		this.handSize = handSize;
		this.classCard=classCard;
		this.raceCard=raceCard;
		this.combatLevel = combatLevel;
		this.level=level;
		update();
	}
	

}
