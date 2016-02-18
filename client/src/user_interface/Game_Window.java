package user_interface;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;



public class Game_Window extends JFrame implements KeyListener {

	
 


    public Game_Window() {
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	double width = screenSize.getWidth();
    	double height = screenSize.getHeight();
    	
    	int w= (int) ( width*0.8);
    	int h= (int) ( height*0.8);
    	

    	  
    	this.addKeyListener(this);
    	this.setContentPane(new MenuUI());
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



	@Override
	public void keyPressed(KeyEvent arg0) {
	
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
	
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getKeyChar()=='a')
		{
			System.out.println("Sto beeeneeee");
		}
		
		
	}
    
}