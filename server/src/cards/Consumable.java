package cards;

public class Consumable extends Card {
	
	public Consumable(String title, CardType type, String effectId) {
		this.title = title;
		this.type = type;
		this.category = Category.Consumable;
		this.effectId = effectId;
	}


}
