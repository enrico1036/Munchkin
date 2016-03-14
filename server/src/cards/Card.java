package cards;

public abstract class Card {
	
		protected String title;			// The name and unique identificator of the card
		
		protected CardType type;		// Basically Door or Treasure
		
		protected Category category;	// Monster, Curse, Equipment ecc
		
		protected String effectId;		// Identificator of the effect, needed by the main server loop to
										// send to the clients the appropriate sequence of commands
		
		public String getEffectId(){
			return effectId;
		}
		
		public void effect(){
			
		}
		
		public String getTitle() {
			return title;
		}
		
		public CardType getType() {
			return type;
		}
		
		public Category getCategory() {
			return category;
		}
}
