package cards;

public class Equipment extends Card {
	private int bonus;
	private int value;
	private EquipSlot slot;
	//TODO: valutare se conviene mettere le carte che sono usabili solo da qualcuno o no ( private String raceAllowed )

	
	public Equipment(String title, EquipSlot slot, int bonus, int value) {
		super(title, CardType.Treasure, Category.Equipment);
		this.slot = slot;
		this.bonus = bonus;
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
