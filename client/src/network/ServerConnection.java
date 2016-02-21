package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerConnection {
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

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
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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
	
	public String readLine(){
		String line = null;
		try {
			line = reader.readLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	public void writeLine(String line){
		try {
			writer.write(line);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
