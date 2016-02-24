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

import game.GameManager;
import network.message.ActionResultMessage;
import network.message.ConnectionRequestMessage;
import network.message.Message;


public class ConnectionListener implements Runnable{

	private final ServerSocket servSock;
	private final Thread listenerThread;
	
	private final ConnectionPool pool;
	
	private boolean running;
	private int connTimeout;
	
	public ConnectionListener(int port, final ConnectionPool pool) throws IOException{
		servSock = new ServerSocket(port);
		listenerThread = new Thread(this);
		this.pool = pool;
		running = true;
		
		// Accept will throw SocketTimeoutException after 5 sec
		setAcceptTimeout(5000);
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
	
	public boolean isRunning(){
		return running;
	}
	
	public void start(){
		listenerThread.start();
	}
	
	public void stop(){
		running = false;
		try {
			servSock.close();
			listenerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
				conn = new ClientConnection(servSock.accept(), connTimeout, pool);
				pool.add(conn);
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
	}
	
	
}
