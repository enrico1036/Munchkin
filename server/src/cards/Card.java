package cards;

import java.util.HashMap;
import game.Player;
import utils.StateMachine;

public abstract class Card {
	
		protected String title;			// The name and unique identificator of the card
		
		protected CardType type;		// Basically Door or Treasure
		
		protected Category category;	// Monster, Curse, Equipment ecc
		
		protected HashMap<String, HashMap<String, String>> effects;	// List containing effectsID and a list of parameter for each effect 
		
		protected HashMap<String, HashMap<String, String>> endEffects; // List containing effectsID and a list of parameter for each effect to be called on card discard
		
		protected Player owner;	// Card owner
		
		public Card(String title, CardType type, Category category) {
			this.title = title;
			this.type = type;
			this.category = category;
			effects = new HashMap<String, HashMap<String, String>>();
		}
		
		public HashMap<String, HashMap<String, String>> getEffects(){
			return effects;
		}
		
	
		
		public HashMap<String, HashMap<String, String>> getEndEffects(){
			return endEffects;
		}
		
	
		
		public void setOwner(Player owner) {
			this.owner = owner;
		}
		
		public void effect(StateMachine gamePhase) {
			try {
				Effect.runEffect(gamePhase, this.owner, this.effects);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void endEffect(StateMachine gamePhase) {
			try {
				Effect.runEffect(gamePhase, this.owner, this.endEffects);
			} catch (Exception e) {
				//e.printStackTrace();
			}
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
