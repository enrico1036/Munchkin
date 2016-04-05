package dataStructure;

import java.util.ArrayList;

public class CardDataSet extends SharedData {

	private ArrayList<String> cards;

	public CardDataSet() {
		cards = new ArrayList<String>();
	}

	public ArrayList<String> getCards() {
		return cards;
	}

	public void setCards(ArrayList<String> cards) {
		this.cards = cards;
		update();
	}

	public void addCard(String card) {
		cards.add(card);
		update();
	}
	
	public void removeCard(String card){
		cards.remove(card);
		update();
	}
}
