package cards;

public class Equipment extends Card {
	private int bonus;
	private int value;
	private EquipSlot slot;	

	public Equipment(String title){
		this.title = title;
	}
	
	public void createEquipment(EquipSlot slot,int bonus,int value) {
		this.type = CardType.Treasure;
		this.slot = slot;
		this.category = Category.Equipment;
	}
	
	public EquipSlot getSlot() {
		return this.slot;
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub

	}
	
	public EquipSlot getSlot(String slot){
		
		EquipSlot eqSlot=null;
		
		switch(slot){
		
			case"head":
				eqSlot=EquipSlot.head;
				break;
			case"body":
				eqSlot=EquipSlot.body;
				break;
			case"hand1":
				eqSlot=EquipSlot.hand1;
				break;
			case"hand2":
				eqSlot=EquipSlot.hand2;
				break;
			case"feet":
				eqSlot=EquipSlot.feet;
				break;
			
		}
		return eqSlot;
	}
	


}
