package user_interface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;


import client.MunchkinClient;

public class MenuUI extends GamePanel{
	
	private BufferedImage background=null;
	private ImageIcon connetti;
	private JButton button,button1,button2;
	private Dimension buttdimmin= new Dimension(300,50);
	private int wndWidth;
	private int wndHeight;
	
	public MenuUI() {
		 
		 		
		this.background = MunchkinClient.getImage("menu_background");
		this.setLayout(null);
		
		
		//Create an ImageIcone where we load the connect_button image
		 connetti= new ImageIcon();
		connetti.setImage(MunchkinClient.getImage("connect_button"));
		
		
		
		
		
		//Create a new button that allows you to connect to the server 
		button = new JButton(connetti);
		button.setActionCommand("ciaone");
		button.setVisible(true);
		button.addActionListener(this);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setMinimumSize(buttdimmin);
		this.add(button);
		
		
		//Create a new button that allows you to connect to the server
		button1 = new JButton();
		button1.setText("Sto Bene");
		button1.setVisible(true);
		
		this.add(button1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getActionCommand() == "ciaone"){
			System.exit(0);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		this.updateComponents();
		super.paintComponent(g);
		g.drawImage(background,0,0, this.getWidth(), this.getHeight(),null);
	}

	
	protected void updateComponents(){
		 wndWidth = this.getWidth();
		 wndHeight = this.getHeight();
		
		
		BufferedImage image = (BufferedImage) connetti.getImage(); // transform it 
		
		connetti = new ImageIcon(this.scale(image, imageType, dWidth, dHeight, fWidth, fHeight));  // transform it back
		this.ResizeButton(connetti);
				
		
	}
	
	/**
	 * scale image
	 * 
	 * @param sbi image to scale
	 * @param imageType type of image
	 * @param dWidth width of destination image
	 * @param dHeight height of destination image
	 * @param fWidth x-factor for transformation / scaling
	 * @param fHeight y-factor for transformation / scaling
	 * @return scaled image
	 */
	private static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
	    BufferedImage dbi = null;
	    if(sbi != null) {
	        dbi = new BufferedImage(dWidth, dHeight, imageType);
	        Graphics2D g = dbi.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
	        g.drawRenderedImage(sbi, at);
	    }
	    return dbi;
	}
	
	
	private void ResizeButton(ImageIcon img){
		button.setBounds(wndWidth/50, (wndHeight-button.getHeight())/2,
				img.getIconWidth(),img.getIconHeight());
		
	}
	
}
	

	
	
	
	
	
	

