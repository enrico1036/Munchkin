package user_interface;


import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;

import javax.imageio.*;

public class  MenuUI extends Frame {

	//Costruttore dela classe che va a chiamare il metodo PrepareGui()
	 public MenuUI(){  
	      super("Munchkin");
	      prepareGUI();
	 	   }
     
	 	//Prendo le dimensione dello schermo per dimensionare la finestra
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   double width = screenSize.getWidth();
	   double height = screenSize.getHeight();
	  
	   //Effettuo un cast per poter inserire le due grandezze sui metodi setSize() e drawImage()
      int w= (int) width;
      int h= (int) height;
      
      
	  
	  //Metodo paint che mi va a disegnera l'immagine in background
     
	 public void paint(Graphics g) {
		
		   File s1 = new File("Resources/Images/home.jpg");
		   File s2 = new File("Resources/Images/connect_to_server.png");
		   File s3 = new File("Resources/Images/option.png");
		   File s4 = new File("Resources/Images/exit.png");
		 
		 
		 	Image back = createImage(s1);
		    Image conn = createImage(s2);
		    Image opt = createImage(s3);
		    Image exit = createImage(s4);
		   g.drawImage(back, 0,0,w,h,null);
		   g.drawImage(conn, 200,200,this);
		   g.drawImage(opt, 200,300,this);
		   g.drawImage(exit, 200,400,this);
		   
		    
		  }
	 
	//Metodo che gestisce la finestra settando altezza e larghezza
	 
	  private void prepareGUI(){  
		  this.setSize(screenSize);
	      addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      }); 
	   }    
	  
	/*metodo che va a caricare l'immagine, mediante la libreria ImageIO, 
		restituendo poi al metodo paint() l'immagine caricata. Inoltre dato
		che tale caricamento potrebbe non restituire niente e quindi conviene
		gestire tale problema con un'eccezione.
	 */
	   private Image createImage(File b){     
		   BufferedImage img=null;
		   
		   try {
			  img = ImageIO.read(b);
			  
			} catch (IOException e) {
			}
		    
		    
		    return img;
		  }
	   
	
}