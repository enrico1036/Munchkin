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

import debug.Debug;
import game.GameManager;
import game.Player;
import network.message.ActionResultMessage;
import network.message.Message;
import network.message.client.ConnectionRequestMessage;
import utils.PlayerEventListener;

public class ConnectionListener implements Runnable {

	private final ServerSocket servSock;
	private final ConcurrentHashMap<ClientConnection, Thread> threadMap;
	private final Thread listenerThread;
	private boolean running;
	private int connTimeout;
	private PlayerEventListener eventListener;
	private NewMessageQueue queue;

	public ConnectionListener(int port, int maxConnections) throws IOException {
		// Initialize and bind socket on port
		servSock = new ServerSocket(port);
		// Create thread map
		threadMap = new ConcurrentHashMap<>();
		// Create thread for accept()
		listenerThread = new Thread(this);
		running = true;

		// Accept will throw SocketTimeoutException after 5 sec
		setAcceptTimeout(5000);
	}

	public void setAcceptTimeout(int millis) {
		try {
			servSock.setSoTimeout(millis);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void setConnectionTimeout(int millis) {
		connTimeout = millis;
	}
	
	public void setPlayerEventListener(PlayerEventListener listener){
		this.eventListener = listener;
	}
	
	public void setCommonMessageQueue(NewMessageQueue queue){
		this.queue = queue;
	}

	public boolean isRunning() {
		return running;
	}

	public void start() {
		listenerThread.start();
	}

	public void stop() {
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

	public void stopConnection(ClientConnection conn) {
		if (threadMap.containsKey(conn)) {
			try {
				conn.close();
				threadMap.get(conn).join(100);
				threadMap.get(conn).interrupt();
				threadMap.remove(conn);
			} catch (SecurityException | InterruptedException e) {
			}
		}
	}

	public void stopConnections() {
		for (ClientConnection conn : threadMap.keySet()) {
			stopConnection(conn);
		}
	}

	@Override
	public void run() {
		ClientConnection conn = null;
		// Loop until stop() is called or IOException occurs
		while (running) {
			try {
				// Accept new socket and create a new runnable ClientConnection
				conn = new ClientConnection(servSock.accept(), connTimeout);
				// Attach queue to connection
				conn.attachToQueue(queue);
				
				// Create an anonymous Player
				Player anonPlayer = new Player(eventListener);
				anonPlayer.setConnection(conn);
				
				// Start connection thread
				threadMap.put(conn, new Thread(conn));
				threadMap.get(conn).start();

			} catch (SocketTimeoutException e) {
				continue;
			} catch (IOException e) {
				Debug.log("Listener socket closed");
				running = false;
			}
		}

		// Close server socket and wait until all connections are terminated
		try {
			servSock.close();
		} catch (IOException e) {
		}
	}

	@Override
	public String toString() {
		return servSock.getLocalSocketAddress() + ":" + servSock.getLocalPort();
	}
}
