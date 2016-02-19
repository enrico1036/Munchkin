package user_interface;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Game_Window extends JFrame  {

	
	
    public Game_Window() {
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	double width = screenSize.getWidth();
    	double height = screenSize.getHeight();
    	
    	int w= (int) ( width*0.8);
    	int h= (int) ( height*0.8);
    	
    
    	
    	  
    	
    	this.setContentPane(panels[2]);
    	this.setSize(w, h);
    	
    	int x = (int) ((width-w)/2);
    	int y = (int) ((height-h)/2);
     	this.setLocation(x, y);
    	
     this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



	@Override
	public void validate() {
		
		super.validate();
		
		this.setSize(this.getSize().width, (int) (this.getSize().width * 9.0f/16.0f));
		
	}

    public void SetActivePanel(JPanel a)
    {
    	this.setContentPane(a);
    	this.revalidate();
    }
    }
}