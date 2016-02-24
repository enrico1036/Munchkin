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
	private JScrollPane listScroller;
	private DefaultListModel<String> listModel;
	
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
		
		
		listModel = new DefaultListModel<>();
		listModel.addElement("Jane Doe");
		listModel.addElement("John Smith");
		listModel.addElement("Kathy Green");
		Gamestatus_list = new JList<>(listModel);
		Gamestatus_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Gamestatus_list.setLayoutOrientation(JList.VERTICAL);
		Gamestatus_list.setVisibleRowCount(-1);
		listScroller = new JScrollPane(Gamestatus_list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		Gamestatus_list.setFocusable(false);
		Gamestatus_list.setVisible(true);
		listScroller.setBounds(wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
		this.add(listScroller);
		
		//this.Gamestatus = new JTextArea();
		//Gamestatus.setFocusable(false);
		//this.add(Gamestatus);
		
		
		for(int i =100;i>0;i--)
		{
			//Gamestatus.append("Gamestarting in "+i+"\n");
			listModel.addElement("ciauzzz");
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
		 //Gamestatus.setBounds( wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
		 User_ready.setBounds( wndWidth*3/5+wndWidth/5, wndHeight/10, User_ready.getWidth(), User_ready.getHeight());
		 listScroller.setBounds( wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand()=="tick")
		{
			window.SetActivePanel(MunchkinClient.getPanel(0));
		}
	}
	
}
