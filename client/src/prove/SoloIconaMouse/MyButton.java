package prove.SoloIconaMouse;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

public class MyButton extends JButton implements MouseInputListener{

	boolean inButton;
	int globalX, globalY;
	GlassPane glassPane;
	
	
	public MyButton() {
	}

	public MyButton(Icon icon) {
		super(icon);
	}

	public MyButton(String text, GlassPane glassPane) {
		super(text);
		this.glassPane = glassPane;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public MyButton(Action a) {
		super(a);
	}

	public MyButton(String text, Icon icon) {
		super(text, icon);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Prova.drawArrowLine(glassPane.getGraphics(), -1, -1, -1, -1, -1, -1);
		System.out.println(Prova.destination.getName());		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		inButton = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		inButton = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Component parent = (Component) e.getComponent().getParent();
		globalX = e.getX() + this.getX() + parent.getX();
		globalY = e.getY() + this.getY() + parent.getY();
//
//		if (inButton)
//			this.setLocation(globalX - this.getWidth() / 2, globalY - this.getHeight() / 2);

		Prova.drawArrowLine(glassPane.getGraphics(), this.getX() + this.getWidth()/2 + parent.getX(), this.getY()+ this.getHeight()/2 + parent.getY(), globalX, globalY, 50, 30);
		glassPane.repaint();
		this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

}
