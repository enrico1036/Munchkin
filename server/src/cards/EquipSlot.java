package cards;

public enum EquipSlot {
	head("head"), 
	body("body"), 
	hand1("hand1"), 
	hand2("hand2"), 
	feet("feet");
	
	private final String slot;
	EquipSlot(String slot){
		this.slot = slot;
	}
	
	public static EquipSlot parse(String str){
		switch(str){
		case "head":
			return EquipSlot.head;
		case "body":
			return EquipSlot.body;
		case "hand1":
			return EquipSlot.hand1;
		case "hand2":
			return EquipSlot.hand2;
		case "feet":
			return EquipSlot.feet;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return slot;
	}
	
	

}
