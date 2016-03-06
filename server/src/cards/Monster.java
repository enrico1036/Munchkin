package cards;

public class Monster extends Card {

	private int level;
	private int earningLevels;
	private int earningTreasures;
	
	
	public Monster(String title, int level, int earningLevels, int earningTreasures, String effectId) {
		this.title = title;
		this.type = CardType.Door;
		this.level = level;
		this.earningLevels = earningLevels;
		this.earningTreasures = earningTreasures;
		this.category = Category.Monster;
		this.effectId = effectId;
	}
	
	
	public void badThings(){
		
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getEarningLevels() {
		return earningLevels;
	}
	
	public int getEarningTreasures() {
		return earningTreasures;
	}
	
	
}
