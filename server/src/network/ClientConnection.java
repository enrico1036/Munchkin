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
	private final IOBuffer buffer;
	private boolean alive;
	
	public ClientConnection(Socket sock, int timeout, final IOBuffer buffer){
		this.sock = sock;
		this.buffer = buffer;
		alive = true;
		try {
			this.sock.setSoTimeout(timeout);
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}

	@Override
	public void run() {
		// Terminate thread if the socket does not exist
		if(sock == null){
			stop();
			return;
		}
		// Loop until client disconnects
		while(alive && sock.isConnected()){
			try {
				System.out.println(reader.readLine());
			} 
			catch (IOException e) {
				alive = false;
			}
		}
		
		stop();
	}
	
	public String readLine(){
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			alive = false;
		}
		return line;
	}
	
	public void write(String str){
		try {
			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			alive = false;
		}
	}

	public void stop() {
		alive = false;
		try{
			sock.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
}
