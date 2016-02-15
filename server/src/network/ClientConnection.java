package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

import utils.Debug;

public class ClientConnection implements Runnable{
	private final Socket sock;
	private BufferedReader reader;
	private BufferedWriter writer;
	private boolean onRun;
	
	public ClientConnection(Socket sock, int timeout){
		this.sock = sock;
		this.onRun = true;
		
		try {
			this.sock.setSoTimeout(timeout);
			this.reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			this.writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			onRun = false;
		}
	}
	
	public boolean isRunning(){
		return onRun;
	}

	@Override
	public void run() {
		
		if(sock == null)
			stop();
		
		Debug.print("Connection " + sock.getPort() + " started");
		
		while(onRun && sock.isConnected()){
			try {
				System.out.println(reader.readLine());
			} 
			catch (IOException e) {
				onRun = false;
			}
		}
		
		Debug.print("Connection " + sock.getPort() + " ended");
	}

	public void stop() {
		onRun = false;
	}
	
}
