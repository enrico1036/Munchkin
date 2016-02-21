package server;

import java.io.IOException;

import game.GameManager;
import network.ConnectionHandler;

public class MainClass {

	public static void main(String[] args) throws IOException {
//		ConnectionHandler handler = new ConnectionHandler(34569, 3);
//		handler.setAcceptTimeout(60000);
//		handler.setConnectionTimeout(10000);
//		handler.run();
		GameManager.StartGame();
		
	}

}
