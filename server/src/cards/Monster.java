package cards;

public class Monster extends Card {

	private int level;
	private int earningLevels;
	private int earningTreasures;
	
	
	public Monster(int level) {
		this.level = level;
	}
	
	@Override
	public void effect() {
		// TODO Act bonus/malus versus different class players
		System.out.println("Mostro cattivo");
	}
	
	public void badThings(){
		
	}
	
}
