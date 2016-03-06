package cards;

public class Monster extends Card {

	private int level;
	private int earningLevels;
	private int earningTreasures;
	
	
	public Monster(String title, int level, String effectId) {
		this.title = title;
		this.type = CardType.Door;
		this.level = level;
		this.category = Category.Monster;
		this.effectId = effectId;
	}
	
	
	public void badThings(){
		
	}
	
}
