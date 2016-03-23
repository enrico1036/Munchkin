package prove.SoloIconaMouse;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements MouseListener{

	public MyPanel() {
		addMouseListener(this);
	}

	public MyPanel(LayoutManager layout) {
		super(layout);
	}

	public MyPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public MyPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Prova.destination = this;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}

