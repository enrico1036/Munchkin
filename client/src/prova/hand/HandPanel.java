package prova.hand;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HandPanel extends JPanel{
	
	// Maximum space between cards
	private static final int SPACING = 5;
	// Space between panel borders and cards
	private static final int PADDING = 5;
	
	private final ArrayList<JButton> cards;
	private Dimension cardSize;
	
	public HandPanel(){
		cards = new ArrayList<>();
		cardSize = null;
		
		setLayout(null);
		setBackground(Color.RED);
	}
	
	public void addCard(final JButton card){
		add(card);
		cards.add(card);
		
		
		
		if(cardSize == null)
			cardSize = card.getSize();
		
		updadeView();
	}
	
	public void updadeView(){
		// Exit if empty
		if(cardSize == null || cards.isEmpty())
			return;
		
		// Reset z order
		for(int i=0 ; i< cards.size(); i++)
			setComponentZOrder(cards.get(i), cards.size() - i - 1);
		
		// Length of card array if full expanded
		int expSpacing = ((getWidth() - 2*PADDING) - (int)cardSize.getWidth()) 
				/ (cards.size() - ((cards.size() > 1) ? 1 : 0));
		
		// Calculate real spacing between each card
		int spacing = Math.min(expSpacing, (int)cardSize.getWidth() + SPACING);
		
		for(int i=0; i<cards.size(); i++){
			cards.get(i).setLocation(PADDING + i * spacing, PADDING);
		}
		
		repaint();
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		updadeView();
	}
}
