package cards;

public class Curse extends Card {
	
	private boolean immediate; //true if immediate, false if semipermanent
	
	public Curse(String title, boolean immediate) {
		this.title = title;
		this.type = CardType.Door;
		this.immediate = immediate;
	}

	@Override
	void effect() {
		// TODO Auto-generated method stub

	}

}
