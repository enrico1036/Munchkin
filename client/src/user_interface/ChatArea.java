package user_interface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import javax.swing.border.BevelBorder;

public class ChatArea extends JPanel {

	public final JTextArea textArea;
	public final JTextField textField;
	public final JButton sendButton;
	public final JPanel bottomPanel;
	
	public ChatArea() {
		// Settings of chat area
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// TextArea
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea);
		
		//JPanel to hold input field and send button
		bottomPanel = new JPanel();
		FlowLayout bottomLayout = new FlowLayout();
		bottomLayout.setAlignment(FlowLayout.LEFT);
		bottomPanel.setLayout(bottomLayout);
		bottomPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		add(bottomPanel);
		
		// Text field
		textField = new JTextField();
		textField.setActionCommand("Send");
		textField.setColumns(30);
		bottomPanel.add(textField);
		
		// Send button
		sendButton = new JButton("Send");
		sendButton.setActionCommand("Send");
		bottomPanel.add(sendButton);
		
	}
	
	public void addActionListener(ActionListener listener){
		textField.addActionListener(listener);
		sendButton.addActionListener(listener);
	}
	
	
	public void appendLine(String line){
		textArea.append(line);
		textArea.setCaretPosition(textArea.getText().length());
	}
	
	public String getTextAndClear(){
		String text = textField.getText();
		textField.setText("");
		return text;
	}

}
