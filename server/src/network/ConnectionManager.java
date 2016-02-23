package network;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import network.message.ConnectionRequestMessage;
import network.message.Message;


public class ConnectionManager implements Runnable{

	private final ServerSocket servSock;
	private final ExecutorService execService;
	private final ConcurrentHashMap<String, ClientConnection> connMap;
	private final Thread listenerThread;
	private final IOBuffer buffer;
	
	private boolean running;
	private int connTimeout;
	
	public ConnectionManager(int port, int maxConnections, final IOBuffer buffer) throws IOException{
		servSock = new ServerSocket(port);
		execService = Executors.newFixedThreadPool(maxConnections);
		connMap = new ConcurrentHashMap<>(maxConnections);
		listenerThread = new Thread(this);
		this.buffer = buffer;
		running = true;
		// Accept will throw SocketTimeoutException after 5 sec
		setAcceptTimeout(5000);
	}
	
	public int aliveConnections(){
		return connMap.size();
	}
	
	public void setAcceptTimeout(int millis){
		try {
			servSock.setSoTimeout(millis);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void setConnectionTimeout(int millis){
		connTimeout = millis;
	}
	
	public void startAsync(){
		listenerThread.start();
		
	}
	
	public void stop(){
		running = false;
		try {
			listenerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void run() {
		ClientConnection conn;
		// Loop until stop() is called or IOException occurs
		while(running){
			try {
				// Accept new socket and create a new runnable ClientConnection
				conn = new ClientConnection(servSock.accept(), connTimeout, buffer);
				// Read first message, and check if username is already in use
				ConnectionRequestMessage message = (ConnectionRequestMessage) Message.parse(conn.readLine());
				
				// Add connection to list
				
				// Execute connection as a new thread
				execService.execute(conn);
			} catch (SocketTimeoutException e) {
				continue;
			} catch (IOException e){
				e.printStackTrace();
				running = false;
			}
		}
		
		// Close server socket and wait until all connections are terminated
		try {
			servSock.close();
		} catch (IOException e) {}
		
		// Await termination of the threads still running
		// If not possible, force shutdown
		try {
			if(!execService.awaitTermination(10, TimeUnit.SECONDS)){
				ArrayList<Runnable> list = (ArrayList<Runnable>) execService.shutdownNow();
				for (Runnable r : list){
					((ClientConnection) r).stop();
				}
			}
		} catch (InterruptedException e) {
			execService.shutdown();
		}
		
	}
	
	
}
