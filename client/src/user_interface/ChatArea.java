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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import dataStructure.Data;
import dataStructure.DataListener;

import javax.swing.border.BevelBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;

public class ChatArea extends JPanel implements DataListener{
	private JTextField textField;
	private JButton btnSend;
	private JTextArea textArea;
	
	public ChatArea() {
		// Settings of chat area
		setBorder(new EmptyBorder(2, 2, 2, 2));
		setOpaque(false);
		
		Data.getChat().addDataListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setActionCommand("Send");
		
		btnSend = new JButton("Send");
		btnSend.setActionCommand("Send");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSend))
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSend)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		
		// Text Area
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText("");
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		setLayout(groupLayout);
	}
	
	public void addActionListener(ActionListener listener){
		textField.addActionListener(listener);
		btnSend.addActionListener(listener);
	}
	
	
	public void appendLine(String line){
		textArea.append(line + "\n");
		textArea.setCaretPosition(textArea.getText().length());
	}
	
	public String getTextAndClear(){
		String text = textField.getText();
		textField.setText("");
		return text;
	}

	@Override
	public void dataChanged() {
		appendLine(Data.getChat().getLastLine());
	}
}
