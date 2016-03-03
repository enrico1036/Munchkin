package cards;

public class Monster extends Card {

	private int level;
	private int earningLevels;
	private int earningTreasures;
	
	
	public Monster(String title, int level) {
		this.title = title;
		this.type = CardType.Door;
		this.level = level;
		this.category = Category.Monster;
	}
	
	@Override
	public void effect() {
		// TODO Act bonus/malus versus different class players
		System.out.println("Mostro cattivo");
	}
	
	public void badThings(){
		
	}
	
}
