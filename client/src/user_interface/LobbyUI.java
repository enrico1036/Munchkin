package user_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.sun.corba.se.pept.transport.Connection;

import client.MunchkinClient;
import game.Player;
import network.GameEventHandler;
import network.PlayerConnection;
import network.ServerConnection;
import network.message.client.ChatMessage;
import network.message.client.ClientGeneralRequest;


public class LobbyUI extends GamePanel {

	private BufferedImage background,dragon,users;
	private JTextArea Gamestatus;
	private JList<String> Gamestatus_list;
	private int wndWidth,wndHeight;
	private JButton User_ready;
	//private JScrollPane listScroller;
	private DefaultListModel<String> listModel;
	private ScrollableList ScrollList;
	private JLabel[][] Users ;
	private JTextField textBox;
	private ArrayList<Player> players;
	
	public LobbyUI(GameWindow window) {
		
		super(window);
		this.background=MunchkinClient.getImage("panel_background");
		this.dragon=MunchkinClient.getImage("dragon_lobby");
		this.users=MunchkinClient.getImage("users_lobby");
		
		Users= new JLabel [6][2];
		this.instancePlayersMatrix();
		
		this.User_ready= new JButton();
		User_ready.setActionCommand("tick");
		User_ready.addActionListener(this);
		this.add(User_ready);
		
		
		ScrollList = new ScrollableList();
		ScrollList.setBounds( wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
		this.add(ScrollList);

		players = new ArrayList<Player>();
		
		GameEventHandler.getPlayerList();
		
		for(int i =100;i>0;i--)
		{

			ScrollList.add_Element("ciaooooooo"+i);
		}
		
		textBox = new JTextField("Prova");
		textBox.addActionListener(this);
		textBox.setActionCommand("Enter");
		textBox.setBounds(0,0, 50, 50);
		this.add(textBox);
		
	}
	


	public ScrollableList getScrollList() {
		return ScrollList;
	}



	public void setScrollList(ScrollableList scrollList) {
		ScrollList = scrollList;
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
		 
		 for(int i=0;i<6;i++)
			{
				for(int k=0;k<2;k++)
				{
					Users[i][k].setBounds(wndWidth*(3)/5+wndWidth*k/10, wndHeight*(i+1)/10, wndWidth/10, wndHeight/10);
				}
				
			}
		 User_ready.setBounds( wndWidth*3/5+wndWidth/5, wndHeight/10, User_ready.getWidth(), User_ready.getHeight());
		 //listScroller.setBounds( wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
		 ScrollList.setBounds( wndWidth*3/5, wndHeight/2, wndWidth/5, wndHeight/4);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand()=="tick")
		{
			
			GameEventHandler.setReadyStatus(..( 
				((MenuUI)(MunchkinClient.getPanel("MenuUI"))).getConnection().getConnectedPlayerName()));
			this.showPlayer();
			
		}else if(e.getActionCommand()=="Enter"&&textBox.getText().trim()!=""){
			GameEventHandler.sendChatMessage(GameEventHandler.getConnection().getConnectedPlayerName(),
							textBox.getText());
			textBox.setText("");
		}
	}
	
	public void instancePlayersMatrix(){
		
		for(int i=0;i<6;i++)
		{
			Users[i] = new JLabel[2];
			Users[i][0] = new JLabel();
			Users[i][1] = new JLabel();
			Users[i][1].setText("Non Pronto");
			for(int k=0;k<2;k++)
			{
				Users[i][k].setVisible(false);
				Users[i][k].setBorder(BorderFactory.createLineBorder(Color.black));
				Users[i][k].setBounds(wndWidth*(3)/5+wndWidth*k/10, wndHeight*(i+1)/10, wndWidth/10, wndHeight/10);
				this.add(Users[i][k]);
			}
			
		}
		GameEventHandler.getConnection().send(new ClientGeneralRequest(ClientGeneralRequest.REQUEST_PLAYERS_LIST));
		Users[0][0].setText(GameEventHandler.getConnection().getConnectedPlayerName());
		Users[0][0].setVisible(true);
		Users[0][1].setVisible(true);
	}
	
	public void showPlayer(){
		
		int i;
		boolean selected = false;
			
			players=GameManager.getPlayers();
			for(i=1;){
				if(!(Users[i][0].isVisible())){
					Users[i][0].setVisible(true);
					Users[i][1].setVisible(true);
					Users[i][0].setText("Utente "+i);
					selected=true;
				}else{
					System.out.println("Non trovato "+i);
					
				}
				i++;	
			}
				if(i==6&&!selected)
				{
					window.SetActivePanel(MunchkinClient.getPanel("GameUI"));
					//fai richiesta al server per sapere quanti client ci sono
					
				}
			}
				
	
	
}
