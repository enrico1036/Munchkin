package prove.SoloIconaMouse;

import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class GlassPane extends JPanel {

	public GlassPane() {
		// TODO Auto-generated constructor stub
	}

	public GlassPane(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public GlassPane(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public GlassPane(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Prova.drawArrow(g);
	}
}
