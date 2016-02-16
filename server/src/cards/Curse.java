package cards;

public class Curse extends Card {
	
	private boolean immediate; //true if immediate, false if semipermanent
	
	public Curse(boolean immediate) {
		this.type = CardType.Door;
		this.immediate = immediate;
	}

	@Override
	void effect() {
		// TODO Auto-generated method stub

	}

}
