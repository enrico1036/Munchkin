package user_interface;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.FlowLayout;

public class GameUI extends GamePanel {
	private JTextField lblPlayerName;
	private JTextField txtLevel;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public GameUI(GameWindow window) {
		setLayout(null);
		
		JPanel PlayerStats = new JPanel();
		PlayerStats.setBounds(0, 274, 191, 175);
		add(PlayerStats);
		PlayerStats.setLayout(null);
		
		lblPlayerName = new JTextField();
		lblPlayerName.setText("PlayerName");
		lblPlayerName.setEditable(false);
		lblPlayerName.setEnabled(false);
		lblPlayerName.setBounds(0, 0, 140, 42);
		PlayerStats.add(lblPlayerName);
		lblPlayerName.setColumns(10);
		
		txtLevel = new JTextField();
		txtLevel.setText("10");
		txtLevel.setEditable(false);
		txtLevel.setEnabled(false);
		txtLevel.setBounds(139, 21, 52, 21);
		PlayerStats.add(txtLevel);
		txtLevel.setColumns(10);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setBounds(145, 0, 46, 23);
		PlayerStats.add(lblLevel);
		
		JLabel lblRace = new JLabel("Race");
		lblRace.setBounds(0, 38, 70, 42);
		PlayerStats.add(lblRace);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(0, 91, 70, 40);
		PlayerStats.add(lblClass);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setBounds(0, 135, 70, 40);
		PlayerStats.add(lblSex);
		
		JLabel lblHead = new JLabel("Head");
		lblHead.setBounds(120, 53, 32, 29);
		PlayerStats.add(lblHead);
		
		JLabel lblRiarm = new JLabel("RiArm");
		lblRiarm.setBounds(81, 84, 32, 47);
		PlayerStats.add(lblRiarm);
		
		JLabel lblBody = new JLabel("Body");
		lblBody.setBounds(114, 88, 32, 47);
		PlayerStats.add(lblBody);
		
		JLabel lblLearm = new JLabel("LeArm");
		lblLearm.setBounds(145, 86, 36, 50);
		PlayerStats.add(lblLearm);
		
		JLabel lblLegs = new JLabel("Legs");
		lblLegs.setBounds(108, 139, 32, 33);
		PlayerStats.add(lblLegs);
		
		JPanel hand = new JPanel();
		hand.setBounds(190, 323, 352, 126);
		add(hand);
		hand.setLayout(null);
		
		JLabel lblHand = new JLabel("hand");
		lblHand.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHand.setBounds(151, 48, 34, 24);
		hand.add(lblHand);
		
		JPanel Player2 = new JPanel();
		Player2.setBounds(101, 0, 157, 94);
		add(Player2);
		Player2.setLayout(null);
		
		JLabel lblPlayer = new JLabel("PLAYER 2");
		lblPlayer.setBounds(52, 32, 46, 14);
		lblPlayer.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		Player2.add(lblPlayer);
		
		JPanel Player3 = new JPanel();
		Player3.setBounds(268, 0, 157, 94);
		add(Player3);
		Player3.setLayout(null);
		
		JLabel lblPlayer_1 = new JLabel("PLAYER 3");
		lblPlayer_1.setBounds(43, 11, 46, 14);
		Player3.add(lblPlayer_1);
		
		JPanel Player4 = new JPanel();
		Player4.setBounds(447, 0, 157, 94);
		add(Player4);
		Player4.setLayout(null);
		
		JLabel lblPlayer_2 = new JLabel("PLAYER4");
		lblPlayer_2.setBounds(39, 11, 46, 14);
		Player4.add(lblPlayer_2);
		
		JPanel Cards = new JPanel();
		Cards.setBounds(541, 323, 170, 126);
		add(Cards);
		Cards.setLayout(null);
		
		JLabel lblDecks = new JLabel("DECKS");
		lblDecks.setBounds(66, 44, 46, 14);
		Cards.add(lblDecks);
		
		JPanel Table = new JPanel();
		Table.setBounds(130, 105, 446, 147);
		add(Table);
		Table.setLayout(null);
		
		JLabel lblMonster = new JLabel("Monster");
		lblMonster.setBounds(10, 26, 82, 85);
		Table.add(lblMonster);
		
		JLabel lblDranwCard = new JLabel("DranwCard or  Played Card");
		lblDranwCard.setBounds(136, 25, 155, 86);
		Table.add(lblDranwCard);
		
		JPanel Player5 = new JPanel();
		Player5.setBounds(0, 114, 99, 138);
		add(Player5);
		Player5.setLayout(null);
		
		JLabel lblPlayerName_1 = new JLabel("PLAYER NAME5");
		lblPlayerName_1.setBounds(0, 0, 99, 20);
		Player5.add(lblPlayerName_1);
		
		JLabel lblLevel_1 = new JLabel("LEVEL5");
		lblLevel_1.setBounds(10, 31, 44, 20);
		Player5.add(lblLevel_1);
		
		textField = new JTextField();
		textField.setText("10");
		textField.setBounds(60, 31, 39, 20);
		Player5.add(textField);
		textField.setColumns(10);
		
		JLabel lblRace_1 = new JLabel("RACE5");
		lblRace_1.setBounds(0, 57, 46, 14);
		Player5.add(lblRace_1);
		
		JLabel lblClass_1 = new JLabel("CLASS 5");
		lblClass_1.setBounds(0, 82, 46, 14);
		Player5.add(lblClass_1);
		
		JLabel lblSex_1 = new JLabel("SEX5");
		lblSex_1.setBounds(0, 113, 46, 14);
		Player5.add(lblSex_1);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(55, 113, 44, 14);
		Player5.add(btnNewButton_1);
		
		JPanel Player6 = new JPanel();
		Player6.setBounds(612, 114, 99, 138);
		add(Player6);
		Player6.setLayout(null);
		
		JLabel lblPlayer_3 = new JLabel("PLAYER6");
		lblPlayer_3.setBounds(10, 40, 46, 14);
		Player6.add(lblPlayer_3);
		
		JPanel ZoomedPanel = new JPanel();
		ZoomedPanel.setVisible(false);
		ZoomedPanel.setBounds(212, 40, 310, 345);
		add(ZoomedPanel);
		ZoomedPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(287, 0, 23, 23);
		ZoomedPanel.add(btnNewButton);
		
		JLabel lblZoomedPanel = new JLabel("ZOOMED PANEL");
		lblZoomedPanel.setBounds(78, 226, 159, 23);
		ZoomedPanel.add(lblZoomedPanel);

	}
}
