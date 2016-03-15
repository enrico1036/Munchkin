package cards;


public class Consumable extends Card {
	
	private boolean combatOnly;
	
	public Consumable(String title, CardType type, boolean combat) {
		super(title, type, Category.Consumable);
		this.combatOnly = combat;
	}


}
