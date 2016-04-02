package utils;


import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class Debug {
	private static JTextArea output;
	
	public static void setOutputDestination(JTextArea component){
		output = component;
	}
	
	public static void log(String str){
		DateFormat format = new SimpleDateFormat("[HH:mm:ss]");
		Date date = new Date();
		output.append(format.format(date) + " " + str + "\r\n");
	}
	
	public static void err(String str){
		DateFormat format = new SimpleDateFormat("[HH:mm:ss]");
		Date date = new Date();
		output.append(format.format(date) + " ERROR: " + str + "\r\n");
	}
	
}
