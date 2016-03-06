package cards;

public class Equipment extends Card {
	private int bonus;
	private int value;
	private EquipSlot slot;	

	
	public Equipment(String title, EquipSlot slot, int bonus, int value) {
		this.title = title;
		this.type = CardType.Treasure;
		this.slot = slot;
		this.category = Category.Equipment;
	}
	
	public EquipSlot getSlot() {
		return this.slot;
	}
	
	public int getBonus() {
		return bonus;
	}

}
