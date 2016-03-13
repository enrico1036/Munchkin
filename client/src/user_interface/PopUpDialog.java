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

public class PopUpDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JLabel lbltextValue,lblintValue;
	private JButton button1,button2;
	private boolean result;
	

	public PopUpDialog(String textValue,String button1Value,String button2Value,int timeOut,int minValue,int maxValue) {
		setResizable(false);
		setTitle("PopUp Dialog");
		setBounds(100, 100, 300, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lbltextValue = new JLabel(textValue);
		lbltextValue.setBounds(10, 11, 81, 14);
		contentPanel.add(lbltextValue);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				button1 = new JButton(button1Value);
				button1.setActionCommand("button1");
				button1.addActionListener(this);
				buttonPane.add(button1);
			}
			{
				button2 = new JButton(button2Value);
				button2.setActionCommand("button2");
				button2.addActionListener(this);
				buttonPane.add(button2);
			}
		}
		
		result = false;
	}

	public boolean positiveResult(){
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("button1")){
			
		else
		
		}
		
		setVisible(false);
		dispose();
	}
}