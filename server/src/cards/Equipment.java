package cards;

import sun.security.util.ECUtil;

public class Equipment extends Card {
	private int bonus;
	private int value;
	private EquipSlot slot;
	

	public Equipment(EquipSlot slot) {
		this.type = CardType.Treasure;
		this.slot = slot;
	}

	@Override
	void effect() {
		// TODO Auto-generated method stub

	}

}
