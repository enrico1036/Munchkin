package user_interface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import client.MunchkinClient;

public class LobbyUI extends GamePanel {

	private BufferedImage background,dragon,users;
	private JLabel Users;
	private JTextArea Gamestatus;
	private JList<String> Gamestatus_list;
	private int wndWidth,wndHeight;
	private JCheckBox User_ready;
	//private JScrollPane listScroller;
	private DefaultListModel<String> listModel;
	private ScrollableList ScrollList;
	
	public LobbyUI(GameWindow window) {
		
		super(window);
		this.background=MunchkinClient.getImage("lobby_background");
		this.dragon=MunchkinClient.getImage("dragon_lobby");
		this.users=MunchkinClient.getImage("users_lobby");
		
		
		this.Users = new JLabel("Utente a");
		Users.setFocusable(false);
		this.add(Users);
		
		this.User_ready= new JCheckBox();
		User_ready.setActionCommand("tick");
		User_ready.addActionListener(this);
		this.add(User_ready);
		
		ScrollList = new ScrollableList();
		ScrollList.setBounds( wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
		this.add(ScrollList);
		ScrollList.add_Element("ciaooooooo");

		
		
		for(int i =100;i>0;i--)
		{

			ScrollList.add_Element("ciaooooooo");
		}
		
		
	}
	


	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background, 0,0, this.getWidth(), this.getHeight(),null);
		g.drawImage(dragon, 0, 0, this.getWidth()*2/5, this.getHeight(), null);
		g.drawImage(users, this.getWidth()*3/5, 0, users.getWidth(), users.getHeight(), null);
		
	}
	
	private void updateComponents(){
		 wndWidth = this.getWidth();
		 wndHeight = this.getHeight();
		 
		 Users.setBounds(wndWidth*3/5, wndHeight/10, wndWidth/5, wndHeight/4);
		 User_ready.setBounds( wndWidth*3/5+wndWidth/5, wndHeight/10, User_ready.getWidth(), User_ready.getHeight());
		 //listScroller.setBounds( wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
		 ScrollList.setBounds( wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand()=="tick")
		{
			window.SetActivePanel(MunchkinClient.getPanel(0));
		}
	}
	
}
