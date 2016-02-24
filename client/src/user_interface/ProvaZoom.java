package user_interface;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import image.ResourceManager;

public class ProvaZoom extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProvaZoom frame = new ProvaZoom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProvaZoom() {
		image.ResourceManager.loadAll();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ZoomedPanel zp = new ZoomedPanel();
		zp.setBounds(10, 10, 160, 160);
		zp.setBorder(new EmptyBorder(5, 5, 5, 5));
		zp.setVisible(true);
		zp.setLayout(null);
		
		client.ClientCard card = new client.ClientCard("ciao", ResourceManager.getImage("door_card"), new Rectangle(180, 180, 70, 70), zp);
		client.ClientCard card2 = new client.ClientCard("ciao 2", ResourceManager.getImage("treasure_card"), new Rectangle(250, 180, 70, 70), zp);
		
		contentPane.add(card);
		contentPane.add(card2);
		contentPane.add(zp);
	}
}
