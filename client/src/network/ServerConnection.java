package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import network.message.Message;

public class ServerConnection {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;

	public ServerConnection() {
	}

	public ServerConnection(String hostname, int port) throws IOException {
		connect(hostname, port);
	}

	public void connect(String hostname, int port) throws IOException {
		// Terminate any alive connection
		if (isConnected())
			socket.close();
		// Create new socket
		socket = new Socket(hostname, port);
		// Create read write buffers
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
		
	}

	public boolean isConnected() {
		return (socket != null) && (socket.isConnected());
	}

	public void disconnect() {
		try {
			if (isConnected())
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Message read() {
		Message obj = null;
		try {
			try {
				
				obj = (Message) input.readObject();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public void write(Message obj) {
		try {
			output.writeObject(obj);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
