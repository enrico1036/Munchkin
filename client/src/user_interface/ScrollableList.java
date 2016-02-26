package user_interface;

import java.awt.Dimension;
import java.util.Timer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ScrollableList extends JScrollPane {

	private JList<String> Gamestatus_list;
	private DefaultListModel<String> listModel;
	
	public ScrollableList() {
		// TODO Auto-generated constructor stub
		listModel = new DefaultListModel<>();
		listModel.addElement("Jane Doe");
		Gamestatus_list = new JList<>(listModel);
		Gamestatus_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Gamestatus_list.setLayoutOrientation(JList.VERTICAL);
		Gamestatus_list.setVisibleRowCount(-1);
		this.setViewportView(Gamestatus_list);
		this.setPreferredSize(new Dimension(250, 80));
		
	}
	
	
	public void add_Element(String new_element)
	{
		listModel.addElement(new_element);
	}
	
	public void remove_Element(String elToDelete)
	{
		listModel.removeElement(elToDelete);
	}

}
