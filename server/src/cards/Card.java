package cards;

public abstract class Card { 
		protected String title;
		protected CardType type;
		
		abstract void effect();
		
		public CardType getType() {
			return type;
		}
}
