package cards;

public class Consumable extends Card {
	
	private boolean combatOnly;
	
	public Consumable(String title, CardType type, String effectId,boolean combat) {
		this.title = title;
		this.type = type;
		this.category = Category.Consumable;
		this.effectId = effectId;
		this.combatOnly=combat;
	}


}
