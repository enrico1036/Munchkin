package Prova;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JFrame {

	private MyPanel contentPane;
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");
		MyFrame frame = new MyFrame();
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public MyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		contentPane = new MyPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		

		

	}
}
