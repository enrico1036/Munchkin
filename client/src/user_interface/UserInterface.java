package user_interface;

import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Component;

import javax.imageio.*;

public class  UserInterface extends Frame {

	//Costruttore dela classe che va a chiamare il metodo PrepareGui()
	 public UserInterface(){  
	      super("Munchkin");
	      prepareGUI();
	   }
      
	 public void paint(Graphics g) {
		    Image img = createImage();
		    g.drawImage(img, 200,200,this);
		  }
	 
	//Metodo che gestisce la finestra settando altezza e larghezza
	 
	  private void prepareGUI(){  
	      setSize(1024,800);
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
		   File sourceimage = new File("Resources/Images/homeimage.png");
		   try {
			  img = ImageIO.read(sourceimage);
			} catch (IOException e) {
			}
		    
		    
		    return img;
		  }
	   
     
        
}