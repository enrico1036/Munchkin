package user_interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import network.GameEventHandler;

import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

public class PopUpDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JLabel lbltextValue,lblintValue,lblValue,lblCountDown;
	private JButton button1,button2;
	private JSpinner spinner;
	private SpinnerNumberModel model;
	private boolean result;
	

	public PopUpDialog(String textValue,String button1Value,String button2Value,int timeOut,int minValue,int maxValue) {
		setResizable(false);
		setTitle("PopUp Dialog");
		setBounds(100, 100, 300, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//contentPanel width and height
		int panew = contentPanel.getWidth();
		int paneh = contentPanel.getHeight();
				
		lbltextValue = new JLabel(textValue);
		lbltextValue.setBounds(10, 11, 81, 14);
		contentPanel.add(lbltextValue);
		
		lblValue = new JLabel("Value");
		lblValue.setBounds(199, 11, 46, 14);
		contentPanel.add(lblValue);
		
		lblCountDown = new JLabel(String.valueOf(10));
		lblCountDown.setBounds(199, 11, 46, 14);
		contentPanel.add(lblCountDown);
		
		
		Timer timer = new Timer(true);
		timer.schedule( new TimerTask() {
			int k=10;
			@Override
			public void run() {
				if(k==0){
					timer.cancel();
				}else
					k--;
				lblCountDown.setText(String.valueOf(k));
				
				
				
			}
		},0,1000);
		

		 model =
		        new SpinnerNumberModel(minValue, //initial value
		        					   minValue, //min
		                               maxValue, //max
		                               1);                //step
		 spinner = new JSpinner();
			spinner.setBounds(199, 28, 86, 20);
			spinner.setModel(model);
			contentPanel.add(spinner);
		
				button1 = new JButton(button1Value);
				button1.setActionCommand("button1");
				button1.setBounds(199, 28, 86, 20);
				button1.addActionListener(this);
				contentPanel.add(button1);
			
			
				button2 = new JButton(button2Value);
				button2.setActionCommand("button2");
				button2.setBounds(199, 28, 86, 20);
				button2.addActionListener(this);
				contentPanel.add(button2);
			
		
		
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("button1"))
			GameEventHandler.sendPopUpResult(true, false, ((Integer)spinner.getValue()).intValue());
		else if(e.getActionCommand().equals("button2"))
			GameEventHandler.sendPopUpResult(false, true, ((Integer)spinner.getValue()).intValue());
		
	}
	
}
