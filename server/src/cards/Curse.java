package cards;

public class Curse extends Card {
	
	private boolean immediate; //true if immediate, false if semipermanent
	
	public Curse(String title, boolean immediate) {
		super(title, CardType.Door, Category.Curse);
		this.immediate = immediate;
	}


}
