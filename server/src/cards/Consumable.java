package cards;

public class Consumable extends Card {
	
	public Consumable(String title, CardType type) {
		this.title = title;
		this.type = type;
		this.category = Category.Consumable;
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}
}
