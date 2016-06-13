package cards;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;
import utils.StateMachine;

public class Monster extends Card {

	private int level;
	private int earningLevels;
	private int earningTreasures;
	private int totalBonus;
	private HashMap<String, HashMap<String, String>> badThings; // List containing effectsID and a list of parameter for each effect to be called on player loose

	
	public Monster(String title, int level, int earningLevels, int earningTreasures) {
		super(title, CardType.Door, Category.Monster);
		this.level = level;
		this.earningLevels = earningLevels;
		this.earningTreasures = earningTreasures;
		badThings = new HashMap<String, HashMap<String, String>>();
		
	}
	
	
	public  HashMap<String, HashMap<String, String>> getBadThings(){
		return badThings;
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
	
	public void badThings(StateMachine gamePhase) {
		try {
			Effect.runEffect(gamePhase, this.owner, this.badThings);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
}
