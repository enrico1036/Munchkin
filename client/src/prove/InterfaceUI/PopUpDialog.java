package prove.InterfaceUI;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import network.GameEventHandler;

import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PopUpDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private final JSpinner spinner;
	private final JButton button1;
	private final JButton button2;
	private String buttonPressed;

	public PopUpDialog(String text, String okButtonText, String cancelButtonText, long autoCloseMillis, int spinMinVal, int spinMaxVal) {
		// Setup frame properties
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("PopUp Dialog");
		setBounds(100, 100, 315, 170);
		setResizable(false);
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);

		// Content layout
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// Text area
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setBounds(10, 11, 205, 76);
		textArea.setLineWrap(true);
		textArea.setText(text);
		contentPanel.add(textArea);

		// Spinner
		spinner = new JSpinner(new SpinnerNumberModel(spinMinVal, spinMinVal, spinMaxVal, 1));
		spinner.setBounds(224, 37, 75, 20);
		if(spinMinVal != spinMaxVal){
			contentPanel.add(spinner);
		}
		
		// Panel to hold Ok and Cancel buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// Ok button (add only if its name is passed)
		button1 = new JButton(okButtonText);
		button1.setActionCommand(button1.getText());
		if(okButtonText != null && !okButtonText.isEmpty()){
			button1.addActionListener(this);
			buttonPane.add(button1);
			getRootPane().setDefaultButton(button1);
		}
		
		// Cancel button (add only if its name is passed)
		button2 = new JButton(cancelButtonText);
		button2.setActionCommand(button2.getText());
		if(cancelButtonText != null && !cancelButtonText.isEmpty()){
			button2.addActionListener(this);
			buttonPane.add(button2);
		}
		
		// Start auto close timer
		if(autoCloseMillis > 0){
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					buttonPressed = null;
					setVisible(false);
				}
			}, autoCloseMillis);
		}
		
		buttonPressed = null;
	}
	
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
	}
	
	public boolean wasButton1Pressed(){
		return buttonPressed.equals(button1.getText());
	}
	
	public boolean wasButton2Pressed(){
		return buttonPressed.equals(button2.getText());
	}
	
	public boolean wasTimedOut(){
		return buttonPressed == null;
	}
	
	public String getButtonPressed(){
		return buttonPressed;
	}
	
	public int getSpinnerValue(){
		return (int) spinner.getValue();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonPressed = e.getActionCommand();
		setVisible(false);
	}
}
