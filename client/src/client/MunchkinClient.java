package client;

import javax.swing.JPanel;

import user_interface.GameUI;
import user_interface.Game_Window;
import user_interface.MenuUI;
import user_interface.LobbyUI;
import user_interface.PauseUI;
 
public class MunchkinClient   {
	private static JPanel[] panels = new JPanel[4];
	
	
	public MunchkinClient(){
		panels[0]=new GameUI();
		panels[1]=new LobbyUI();
		panels[2]=new MenuUI();
		panels[3]=new PauseUI();
		
	}
	
	public static JPanel getPanel(int i) {
		return panels[i];
	}
	
	   public static void main(String[] args){
		   
			MunchkinClient client=new MunchkinClient();
	  
		Game_Window window = new Game_Window();
		window.setVisible(true);
		   
	   }

	  

	
	}




