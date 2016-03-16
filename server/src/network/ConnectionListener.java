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
import game.Player;
import network.message.ActionResultMessage;
import network.message.Message;
import network.message.client.ConnectionRequestMessage;
import utils.PlayerEventListener;

public class ConnectionListener implements Runnable {

	private final ServerSocket servSock;
	private final ExecutorService execService;
	private final Thread listenerThread;
	private boolean running;
	private int connTimeout;
	private final PlayerEventListener eventListener;

	public ConnectionListener(int port, int maxConnections, PlayerEventListener eventListener) throws IOException {
		servSock = new ServerSocket(port);
		execService = Executors.newFixedThreadPool(maxConnections);
		listenerThread = new Thread(this);
		running = true;
		this.eventListener = eventListener;
		
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

	public void stopConnections() {
		// Await termination of the threads still running
		// If not possible, force shutdown
		try {
			if (!execService.awaitTermination(10, TimeUnit.SECONDS)) {
				ArrayList<Runnable> list = (ArrayList<Runnable>) execService.shutdownNow();
				for (Runnable r : list) {
					((ClientConnection) r).close();
				}
			}
		} catch (InterruptedException e) {
			execService.shutdown();
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
				// Start connection thread
				execService.execute(conn);
				
			} catch (SocketTimeoutException e) {
				continue;
			} catch (IOException e) {
				e.printStackTrace();
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
