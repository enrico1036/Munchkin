package user_interface;

import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Timer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ScrollableList extends JScrollPane implements AdjustmentListener {

	private JList<String> Gamestatus_list;
	private DefaultListModel<String> listModel;
	private int index;
	private boolean elementAdded;
	
	public ScrollableList (){
		listModel = new DefaultListModel<>();
		Gamestatus_list = new JList<>(listModel);
		Gamestatus_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Gamestatus_list.setLayoutOrientation(JList.VERTICAL);
		Gamestatus_list.setVisibleRowCount(-1);
		this.setViewportView(Gamestatus_list);
		this.setPreferredSize(new Dimension(250, 80));
		getVerticalScrollBar().addAdjustmentListener(this);
		
		index=0;
		elementAdded = false;
	}
	
	
	public void add_Element(String new_element)
	{
		listModel.addElement(new_element);
		Gamestatus_list.setSelectedIndex(index++);
		elementAdded = true;
		getVerticalScrollBar().addAdjustmentListener(this);
	}
	
	public void remove_Element(String elToDelete)
	{
		listModel.removeElement(elToDelete);
	}


	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		if(elementAdded){
			e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			elementAdded = false;
		}
	}

}
