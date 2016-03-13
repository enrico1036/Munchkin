package user_interface;

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
	private final JButton okButton;
	private final JButton cancelButton;
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
		okButton = new JButton(okButtonText);
		okButton.setActionCommand(okButton.getText());
		if(okButtonText != null && !okButtonText.isEmpty()){
			okButton.addActionListener(this);
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		
		// Cancel button (add only if its name is passed)
		cancelButton = new JButton(cancelButtonText);
		cancelButton.setActionCommand(cancelButton.getText());
		if(cancelButtonText != null && !cancelButtonText.isEmpty()){
			cancelButton.addActionListener(this);
			buttonPane.add(cancelButton);
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
	
	public boolean wasOkPressed(){
		return buttonPressed.equals(okButton.getText());
	}
	
	public boolean wasCancelPressed(){
		return buttonPressed.equals(cancelButton.getText());
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
