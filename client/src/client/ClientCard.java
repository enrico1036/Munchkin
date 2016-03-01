package client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import user_interface.ZoomedPanel;

public class ClientCard extends JButton implements MouseListener{


	private String title;
	private BufferedImage image;
	private CardType type;
	private ZoomedPanel zoomedPanel;
	
	public ClientCard(String title, CardType type) {
		this.title = title;
		this.type = type;
	}
	
	public void CreateCard(BufferedImage image, ZoomedPanel zoomedPanel){
		this.image = image;
		this.setIcon(new ImageIcon(image));
		this.setBorderPainted(false);
		this.setVisible(true);
		this.zoomedPanel = zoomedPanel;
		this.addMouseListener(this);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public CardType getType() {
		return this.type;
	}
	
	public boolean equals(ClientCard card){
		return(this.title.equals(card.title));
	}
	
	@Override
	public void setSize(int width, int height) {
		this.setIcon(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_FAST)));
		super.setSize(width, height);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		this.setIcon(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_FAST)));
		super.setBounds(x, y, width, height);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		zoomedPanel.showCard(image);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		zoomedPanel.hideCard();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, this.getWidth(), this.getHeight(), null);
		
	}
	
	
}
