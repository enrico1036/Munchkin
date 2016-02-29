package cards;

public abstract class Card { 
		protected String title;
		protected CardType type;
		protected Category category;
		
		public abstract void effect();
		
		public CardType getType() {
			return type;
		}
		
		public Category getCategory() {
			return category;
		}
}
