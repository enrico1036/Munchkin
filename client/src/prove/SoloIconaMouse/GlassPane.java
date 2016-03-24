package prove.SoloIconaMouse;

import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class GlassPane extends JPanel {

	public GlassPane() {
		
	}

	public GlassPane(LayoutManager layout) {
		super(layout);
		
	}

	public GlassPane(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
	}

	public GlassPane(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
	}

	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		Prova.drawArrow(g);
	}
}
