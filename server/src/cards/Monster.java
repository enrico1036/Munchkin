package cards;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

public class Monster extends Card {

	private int level;
	private int earningLevels;
	private int earningTreasures;
	private int totalBonus;
	protected ArrayList<Pair<String, HashMap<String, String>>> badThings; // List containing effectsID and a list of parameter for each effect to be called on player loose
	
	
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
