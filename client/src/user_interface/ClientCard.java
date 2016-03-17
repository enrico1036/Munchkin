package user_interface;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * 
 * This JButton extension is used to hand the card on the Game Table
 * and every clientcard is associated to a zoomedPanel that show it
 * on a hidden JPanel that has shown every time we pass over it with
 * the mouse

 *
 */

public class ClientCard extends JButton implements MouseListener{


	/**
	 * title: It's the title of the card
	 * image: it's the image of the card
	 * zoomedPanel: it's the jpanel where the card can be shonw
	 */
	
	private String title;
	private BufferedImage image;
	private ZoomedPanel zoomedPanel;
	
	public ClientCard(String title) {
		this.title = title;
		
	}
	/**
	 * This method is used to sets the image and the correlated zoomed Panel of the card
	 * 
	 * @param image:  it's the image of the card
	 * @param zoomedPanel: it's the jpanel where the card can be shonw
	 */
	public void CreateCard(BufferedImage image, ZoomedPanel zoomedPanel){
		this.image = image;
		setBorderPainted(false);
		setVisible(true);
		this.zoomedPanel = zoomedPanel;
		addMouseListener(this);
	}
	/**
	 * 
	 * @return return the title of the card
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * It has used to set the card image
	 * 
	 * @param image : the new image of the card
	 */
	public void setImage(BufferedImage image){
		this.image=image;
	}
	/**
	 * It has used to control that the card image is null
	 * 
	 * @return true: if the image is null
	 * @return false: if the image is not null
	 */
	public boolean imageIsNull(){
		boolean result=true;
		if(image!=null)
			result=false;
		
		return result;
	}
	
	/**
	 * 
	 * Method equals that control if this card has the same title the parametric card
	 * 
	 * @param card: it is the card that we want to control
	 * @return true: if the two cards have the same title
	 * @return false: otherwise
	 */
		
	public boolean equals(ClientCard card){
		return(title.equals(card.getTitle()));
	}
	
	/**
	 * Unemplemented method of MousListener
	 */
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	/**
	 * Unemplemented method of MousListener
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	/**
	 * Unemplemented method of MousListener
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	
	/**
	 *  When we pass over the card the zoomed panel must show the card
	 * 
	 * @param e: is the mouse event from the Interface MouseListener
	 */
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		zoomedPanel.showCard(image);
	}

	/**
	 *  When we pass over the card the zoomed panel must hide the card
	 * 
	 * @param e: is the mouse event from the Interface MouseListener
	 */
	
	@Override
	public void mouseExited(MouseEvent e) {
		zoomedPanel.hideCard();
		
	}

	/**
	 * paintComponent from JComponents class that draw the card image
	 * 
	 */
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image,0,0, this.getWidth(), this.getHeight(), null);
		
	}
	public boolean equals(HandCard card) {
		return title.equals(card.getTitle());
	}
	
}
