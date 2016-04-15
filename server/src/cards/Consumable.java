package cards;

import java.util.HashMap;

public class Consumable extends Card {
	
	private boolean combatOnly;
	private HashMap<String, HashMap<String, String>> effects;
	
	public Consumable(String title, CardType type, boolean combat) {
		super(title, type, Category.Consumable);
		this.combatOnly = combat;
		effects= new HashMap<String, HashMap<String, String>>();
	}
	

}
