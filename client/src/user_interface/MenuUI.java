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
		    Image img = createImage();
		     g.drawImage(img, 0,0,w,h,null);
		    
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
	   private Image createImage(){     
		   BufferedImage img = null;
		   File sourceimage = new File("Resources/Images/home.jpg");
		   try {
			  img = ImageIO.read(sourceimage);
			} catch (IOException e) {
			}
		    
		    
		    return img;
		  }
	   

}