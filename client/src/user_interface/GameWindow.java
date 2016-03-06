package user_interface;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GameWindow extends JFrame implements WindowStateListener,ComponentListener{

	boolean maximized = false;
	Dimension dimmin= new Dimension(800,450);
    public GameWindow() {
    	setTitle("Munchkin");
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	double width = screenSize.getWidth();
    	double height = screenSize.getHeight();
    	
    	int w= (int) ( width*0.8);
    	int h= (int) ( height*0.8);
    	    	
    	this.addWindowStateListener(this);
    	this.setSize(w, h);
    	
    	int x = (int) ((width-w)/2);
    	int y = (int) ((height-h)/2);
     	this.setLocation(x, y);
     	this.setMinimumSize(dimmin);
    	this.addComponentListener(this);
     this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  
    }



    public void SetActivePanel(JPanel a)
    {
    	this.setContentPane(a);
    	this.revalidate();
    }


	@Override
	public void windowStateChanged(WindowEvent e) {
		if(e.getNewState() == MAXIMIZED_BOTH){
			maximized = true;
		} else if (e.getOldState() == MAXIMIZED_BOTH){
			maximized = false;
		}
		
	}


	@Override
	public void componentResized(ComponentEvent e) {
		
		if(!maximized)
			this.setSize(this.getSize().width, (int) (this.getSize().width * 9.0f/16.0f));
		
	}



	@Override
	public void componentHidden(ComponentEvent e) {
		
	}



	@Override
	public void componentMoved(ComponentEvent e) {
		
	}



	@Override
	public void componentShown(ComponentEvent e) {
		
	}

    
    }
