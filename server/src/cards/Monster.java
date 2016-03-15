package cards;

public class Monster extends Card {

	private int level;
	private int earningLevels;
	private int earningTreasures;
	private int totalBonus;
	
	
	public Monster(String title, int level, int earningLevels, int earningTreasures) {
		super(title, CardType.Door, Category.Monster);
		this.level = level;
		this.earningLevels = earningLevels;
		this.earningTreasures = earningTreasures;
	}
	
	
	public void badThings(){
		
	}
	
	public int getTotalBonus(){
		return totalBonus;
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
	
	public int setTotalBonus(int bonus){
		totalBonus+=bonus;
		return totalBonus;
	}
	
	
}
