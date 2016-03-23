package prove.SoloIconaMouse;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
/**
 * Working application that change cursor icon on drag begin 
 * and recognize the panel where the mouse is released
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
		
		MyButton btnCarta = new MyButton("carta");
		Lpanel.add(btnCarta);
		

	}
}
