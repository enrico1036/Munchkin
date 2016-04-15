package cards;

import java.util.ArrayList;
import java.util.HashMap;

import game.Player;
import javafx.util.Pair;
import utils.StateMachine;

public abstract class Card {
	
		protected String title;			// The name and unique identificator of the card
		
		protected CardType type;		// Basically Door or Treasure
		
		protected Category category;	// Monster, Curse, Equipment ecc
		
		protected HashMap<String, HashMap<String, String>> effects;	// List containing effectsID and a list of parameter for each effect 
		
		protected ArrayList<Pair<String, HashMap<String, String>>> endEffects; // List containing effectsID and a list of parameter for each effect to be called on card discard
		
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
		
	
		
		public ArrayList<Pair<String, HashMap<String, String>>> getEndEffects(){
			return endEffects;
		}
		
		public void addEndEffect(Pair<String, HashMap<String, String>> endEffect) {
			endEffects.add(endEffect);
		}
		
		public void setOwner(Player owner) {
			this.owner = owner;
		}
		
		public void effect(StateMachine gamePhase) {
			Effect.runEffect(gamePhase, this.owner, this.effects);
			//TODO DA SISTEMARE
		}
		
		public void endEffect(StateMachine gamePhase) {
			Effect.runEffect(gamePhase, this.owner, this.endEffects);
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
