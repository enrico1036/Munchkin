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
	private JLabel lbltextValue,lblValue,lblCountDown;
	private JButton button1,button2;
	private JSpinner spinner;
	private SpinnerNumberModel model;

	

	public PopUpDialog(String textValue,String button1Value,String button2Value,int timeOut,int minValue,int maxValue) {
		setResizable(false);
		setTitle("PopUp Dialog");
		setBounds(100, 100, 300, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//contentPanel width and height
		int pw = contentPanel.getWidth();
		int ph = contentPanel.getHeight();
				
		lbltextValue = new JLabel(textValue);
		lbltextValue.setBounds(pw/9, ph/9, pw/3, pw/3);
		contentPanel.add(lbltextValue);
		
		lblValue = new JLabel("Value");
		lblValue.setBounds(pw*5/9, ph/9,  pw/3, pw/9);
		contentPanel.add(lblValue);
		
		lblCountDown = new JLabel(String.valueOf(timeOut*1000));
		lblCountDown.setBounds(pw*8/9, ph*8/9,  pw/9, pw/9);
		contentPanel.add(lblCountDown);
		
		
		Timer timer = new Timer(true);
		timer.schedule( new TimerTask() {
			int k=timeOut*1000;
			@Override
			public void run() {
				if(k==0){
					timer.cancel();
					contentPanel.setVisible(false);
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
			spinner.setBounds(pw*5/9, ph/9,  pw/3, pw*2/9);
			spinner.setModel(model);
			contentPanel.add(spinner);
		
				button1 = new JButton(button1Value);
				button1.setActionCommand("button1");
				button1.setBounds(pw/9, ph/9, pw/3, pw/3);
				button1.addActionListener(this);
				contentPanel.add(button1);
			
			
				button2 = new JButton(button2Value);
				button2.setActionCommand("button2");
				button2.setBounds(pw*5/9, ph/9, pw/3, pw/3);
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



	public JButton getButton1() {
		return button1;
	}



	public JButton getButton2() {
		return button2;
	}



	public JSpinner getSpinner() {
		return spinner;
	}



	public JLabel getLblValue() {
		return lblValue;
	}
	
}
