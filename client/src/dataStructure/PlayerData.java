package dataStructure;

public class PlayerData extends SharedData{

	private String username;
	private int level;
	private int combatLevel;
	private String raceCard;
	private String classCard;
	private int handSize;
	
	public PlayerData() {
		username = "";
		level = 0;
		combatLevel = 0;
		raceCard = "";
		classCard = "";
		handSize = 0;
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
	
	

}
