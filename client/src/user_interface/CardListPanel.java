package user_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import client.MunchkinClient;
import dataStructure.CardDataSet;
import dataStructure.DataListener;
import dataStructure.SharedData;

public class CardListPanel<E extends JComponent> extends JPanel implements MouseListener, ComponentListener, DataListener{
	
	private class BoundRect{
		public int x;
		public int y;
		public int width;
		public int height;
		
		public BoundRect(int x, int y, int width, int height){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}

	// Maximum space between cards
	private final int spacing;
	// Space between panel borders and cards
	private final int margin;
	private final ArrayList<E> items;
	private final Dimension itemSize;
	private int selectedItemIndex;
	
	private final CardDataSet data;

	public CardListPanel(final Dimension itemSize, int margin, int spacing, final CardDataSet data) {
		items = new ArrayList<>();
		
		this.itemSize = itemSize;
		this.spacing = spacing;
		this.margin = margin;

		selectedItemIndex = -1;
		
		setLayout(null);
		setOpaque(false);
		addComponentListener(this);
		
		this.data = data;
		data.addDataListener(this);
	}

	public void addItem(final E item) {
		item.addMouseListener(this);
		item.setSize(itemSize);
		item.setVisible(true);
		add(item);
		items.add(item);

		updateView();
	}

	public void updateView() {
		// Exit if empty
		if (items.isEmpty())
			return;

		// Reset z order
		for (int i = 0; i < items.size(); i++){
			setComponentZOrder(items.get(i), items.size() - i - 1);
		}

		if (selectedItemIndex == -1) {
			BoundRect viewport = new BoundRect(
					margin, 
					margin, 
					getWidth() - margin*2, 
					getHeight() - margin*2);
			arrangeItems(0, items.size(), viewport);
		} else {
			
			Point itemPos = items.get(selectedItemIndex).getLocation();
			// Items before
			BoundRect viewport = new BoundRect(
					margin, 
					margin, 
					itemPos.x - margin*2, 
					getHeight() - margin*2);
			arrangeItems(0, selectedItemIndex, viewport);
			
			itemPos.x += itemSize.width;
			
			// Items after
			viewport = new BoundRect(
					margin + itemPos.x, 
					margin, 
					getWidth() - itemPos.x - margin*2, 
					getHeight() - margin*2);
			arrangeItems(selectedItemIndex + 1, items.size() - selectedItemIndex - 1, viewport);
		}

		//rePaintMyComponent();
		
	}

	private void arrangeItems(int firstItem, int numItems, BoundRect view) {
		int ypos = (view.height - itemSize.height) / 2;
		
		double overlapCards = (numItems - ((numItems > 1) ? 1 : 0));
		double expandedSpacing = (view.width - itemSize.width) / overlapCards;
		double realSpacing = Math.max(0, Math.min(expandedSpacing, itemSize.width + spacing));

		for (int i = 0; i < numItems; i++) {
			items.get(i + firstItem).setLocation(view.x + (int) (i * realSpacing), view.y + ypos);
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		final JComponent selected = (JComponent) e.getSource();
//		selectedItemIndex = items.indexOf(selected);
//		updateView();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		final JComponent selected = (JComponent) e.getSource();
		selectedItemIndex = items.indexOf(selected);
		updateView();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		selectedItemIndex = -1;
		updateView();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		updateView();
	}

	@Override
	public void componentShown(ComponentEvent e) {	
	}

	@Override
	public void dataChanged() {
		items.clear();
		removeAll();
		
		for(String str : data.getCards()){
			ClientCard item = new ClientCard(str);
			items.add((E) item);
			item.addMouseListener(this);
			item.setSize(itemSize);
			item.setVisible(true);
			add(item);
			rePaintMyComponent((JComponent)item);
		}
		selectedItemIndex = -1;
		updateView();
	}
	
	public void rePaintMyComponent(JComponent item){
		Runnable target = new Runnable() {
			@Override
			public void run() {
				//item.repaint();
				
				MunchkinClient.getWindow().setSize(MunchkinClient.getWindow().getWidth(), MunchkinClient.getWindow().getHeight()+1);
				MunchkinClient.getWindow().setSize(MunchkinClient.getWindow().getWidth(), MunchkinClient.getWindow().getHeight()-1);
			}
			};
			
			try {
			SwingUtilities.invokeAndWait(target);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			}
}
