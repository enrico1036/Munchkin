package prove.SoloIconaMouse;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.event.MouseInputListener;

public class MyButton extends JButton implements MouseInputListener{

	boolean inButton;
	int globalX, globalY;
	
	
	public MyButton() {
	}

	public MyButton(Icon icon) {
		super(icon);
	}

	public MyButton(String text) {
		super(text);
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
//		JComponent parent = (JComponent) e.getComponent().getParent();
//		globalX = e.getX() + this.getX() + parent.getX();
//		globalY = e.getY() + this.getY() + parent.getY();
//
//		if (inButton)
//			this.setLocation(globalX - this.getWidth() / 2, globalY - this.getHeight() / 2);
		this.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

}
