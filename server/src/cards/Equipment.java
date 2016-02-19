package cards;

public class Equipment extends Card {
	private int bonus;
	private int value;
	private EquipSlot slot;
	

	public Equipment(String title, EquipSlot slot) {
		this.title = title;
		this.type = CardType.Treasure;
		this.slot = slot;
	}

	@Override
	void effect() {
		// TODO Auto-generated method stub

	}

}
