package server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import game.GameManager;

import javafx.util.Pair;
import network.ConnectionListener;
import network.ConnectionPool;
import network.MessageQueue;
import network.message.Message;
import utils.Debug;

public class MunchkinServer {

	private static HashMap<String, String> cards;
	
	public static void main(String[] args) throws IOException {
		MessageQueue queue = new MessageQueue();
		ConnectionPool pool = new ConnectionPool(6, queue);
		
		ConnectionListener listener = new ConnectionListener(5061, pool);
		Debug.print("Listening on " + listener.toString());
		listener.setAcceptTimeout(120000);
		listener.setConnectionTimeout(60000);
		listener.start();
		
		while(listener.isRunning()){
			if(!queue.isEmpty()){
				Pair<String, Message> pair = queue.remove();
				if(pair.getValue() == null)
					continue;
				
				switch(pair.getValue().getMessageCode()){
				case Message.CLT_CHAT_MESSAGE:
					pool.broadcast(pair.getValue());
					break;
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		
			XmlCardLoader loader = null;
			try{
				loader = new XmlCardLoader(new File("Resources/cards.xml"));
				loader.load();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			cards = new HashMap<String,String>();
			for(Pair<String, String> pair : loader.getCards()){
				cards.put(pair.getKey(), pair.getValue());
			}
		
		GameManager.startGame();
		
	}

}
