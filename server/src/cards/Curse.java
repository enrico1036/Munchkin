package cards;

public class Curse extends Card {
	
	private boolean immediate; //true if immediate, false if semipermanent
	
	public Curse(String title, boolean immediate, String effectId) {
		this.title = title;
		this.type = CardType.Door;
		this.immediate = immediate;
		this.effectId = effectId;
	}


}
