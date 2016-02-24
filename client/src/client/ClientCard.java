package client;

import java.awt.Event;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import user_interface.ZoomedPanel;

public class ClientCard extends JButton implements MouseListener{

	private String title;
	private BufferedImage image;
	private ZoomedPanel zoomedPanel;
	
	public ClientCard(String title, BufferedImage image, Rectangle rectangle, ZoomedPanel zoomedPanel) {
		this.title = title;
		this.image = image;
		this.setIcon(new ImageIcon(image));
		this.setBorderPainted(false);
		this.setVisible(true);
		this.setBounds(rectangle);
		this.zoomedPanel = zoomedPanel;
		this.addMouseListener(this);
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
	public void setSize(int width, int height) {
		this.setIcon(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		super.setSize(width, height);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		this.setIcon(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		super.setBounds(x, y, width, height);
	}

}
