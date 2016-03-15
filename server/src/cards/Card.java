package cards;

import java.util.ArrayList;
import java.util.HashMap;

import game.Player;
import javafx.util.Pair;

public abstract class Card {
	
		protected String title;			// The name and unique identificator of the card
		
		protected CardType type;		// Basically Door or Treasure
		
		protected Category category;	// Monster, Curse, Equipment ecc
		
		protected ArrayList<Pair<String, HashMap<String, String>>> effects;	// List containing effectsID and a list of parameter for each effect 
		
		protected Player owner;	// Card owner
		
		public Card(String title, CardType type, Category category) {
			this.title = title;
			this.type = type;
			this.category = category;
		}
		
		public ArrayList<Pair<String, HashMap<String, String>>> getEffects(){
			return effects;
		}
		
		public void addEffect(Pair<String, HashMap<String, String>> effect) {
			effects.add(effect);
		}
		
		public void setOwner(Player owner) {
			this.owner = owner;
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
