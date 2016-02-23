package user_interface;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import client.MunchkinClient;

public class LobbyUI extends GamePanel {

	private BufferedImage background,dragon,users;
	private JLabel Users;
	private JTextArea Gamestatus;
	private int wndWidth,wndHeight;
	private JCheckBox User_ready;
	
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
		
		
		this.Gamestatus = new JTextArea();
		Gamestatus.setFocusable(false);
		this.add(Gamestatus);
		
		
		for(int i =100;i>0;i--)
		{
			Gamestatus.append("Gamestarting in "+i+"\n");
			
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
		 Gamestatus.setBounds( wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
		 User_ready.setBounds( wndWidth*3/5+wndWidth/5, wndHeight/10, User_ready.getWidth(), User_ready.getHeight());
		 
	}
	
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand()=="tick")
		{
			window.SetActivePanel(MunchkinClient.getPanel(0));
		}
	}
	
}
