package user_interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConnectionDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField addressField;
	private JTextField portField;
	private JTextField playerField;
	private boolean result;
	

	public ConnectionDialog() {
		setResizable(false);
		setTitle("Connect to server...");
		setBounds(100, 100, 300, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblServerAddress = new JLabel("Server address:");
		lblServerAddress.setBounds(10, 11, 81, 14);
		contentPanel.add(lblServerAddress);
		
		addressField = new JTextField();
		addressField.setBounds(10, 28, 179, 20);
		contentPanel.add(addressField);
		addressField.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(199, 11, 46, 14);
		contentPanel.add(lblPort);
		
		portField = new JTextField();
		portField.setBounds(199, 28, 86, 20);
		contentPanel.add(portField);
		portField.setColumns(10);
		
		JLabel lblPlayerName = new JLabel("Player name:");
		lblPlayerName.setBounds(10, 59, 81, 14);
		contentPanel.add(lblPlayerName);
		
		playerField = new JTextField();
		playerField.setBounds(10, 75, 179, 20);
		contentPanel.add(playerField);
		playerField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Connect");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		
		result = false;
	}
	
	public String getServerAddress(){
		return addressField.getText();
	}
	
	public int getPort(){
		return Integer.parseInt(portField.getText());
	}
	
	public String getPlayerName(){
		return playerField.getText();
	}
	
	public boolean positiveResult(){
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "OK":
			result = true;
			break;
		case "CANCEL":
			result = false;
			break;
		}
		
		setVisible(false);
		dispose();
	}
}
