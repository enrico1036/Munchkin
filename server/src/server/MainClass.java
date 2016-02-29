package server;

import java.io.IOException;

import game.GameManager;
import network.ConnectionListener;
import network.ConnectionPool;
import network.MessageQueue;

public class MainClass {

	public static void main(String[] args) throws IOException {
		MessageQueue queue = new MessageQueue();
		ConnectionPool pool = new ConnectionPool(6, queue);
		
		ConnectionListener listener = new ConnectionListener(5061, pool);
		listener.setAcceptTimeout(120000);
		listener.setConnectionTimeout(60000);
		listener.start();
		
		while(listener.isRunning()){
		}
		
		
		GameManager.startGame();
		
	}

}
