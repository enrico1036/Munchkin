package cards;

public class Equipment extends Card {
	private int bonus;
	private int value;
	private EquipSlot slot;	

	
	public Equipment(String title, EquipSlot slot, int bonus, int value) {
		super(title, CardType.Treasure, Category.Equipment);
		this.slot = slot;
		this.value = value;
	}
	
	public EquipSlot getSlot() {
		return this.slot;
	}
	
	public int getBonus() {
		return bonus;
	}
	
	public int getValue() {
		return value;
	}

}
