package prove.SoloIconaMouse;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;

/**
 * Working application that change cursor icon on drag begin and recognize the panel where the mouse is released
 * 
 * @author Enrico
 *
 */
public class Prova {

	private JFrame frame;
	public static Component destination;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prova window = new Prova();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Prova() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		MyPanel Lpanel = new MyPanel();
		Lpanel.setName("Lpanel");
		Lpanel.setBounds(6, 6, 178, 266);
		Lpanel.setBackground(Color.GRAY);
		frame.getContentPane().add(Lpanel);

		MyPanel Rpanel = new MyPanel();
		Rpanel.setName("Rpanel");
		Rpanel.setBounds(196, 6, 248, 266);
		Rpanel.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(Rpanel);

		GlassPane glassPane = new GlassPane();
		glassPane.setName("glass");
		glassPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		glassPane.setOpaque(false);
		frame.getContentPane().add(glassPane, 0);

		MyButton btnCarta = new MyButton("carta", glassPane);
		Lpanel.add(btnCarta);
	}

	/**
	 * Draw an arrow line betwwen two point
	 * 
	 * @param g
	 *            the graphic component
	 * @param x1
	 *            x-position of first point
	 * @param y1
	 *            y-position of first point
	 * @param x2
	 *            x-position of second point
	 * @param y2
	 *            y-position of second point
	 * @param d
	 *            the width of the arrow
	 * @param h
	 *            the height of the arrow
	 */
	static int x1, y1, x2, y2;
	static int[] xpoints = { 0, 0, 0 }, ypoints = { 0, 0, 0 };

	public static void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
		if (x1 == -1) {
			Prova.x1 = x1;
			return;
		}
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		Prova.x1 = x1;
		Prova.y1 = y1;
		Prova.x2 = x2;
		Prova.y2 = y2;
		for (int i = 0; i < 3; i++) {
			Prova.xpoints[i] = xpoints[i];
			Prova.ypoints[i] = ypoints[i];
		}
	}

	public static void drawArrow(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		if (x1 != -1) {
			g2d.setColor(Color.MAGENTA);
			g2d.setStroke(new BasicStroke(4));
			g2d.drawLine(x1, y1, x2, y2);
			g2d.setColor(Color.PINK);
			g2d.fillPolygon(xpoints, ypoints, 3);
		}
		g2d.dispose();
	}

}
