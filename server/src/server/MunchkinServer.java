package server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cards.Card;
import game.Decks;
import game.GameManager;

import javafx.util.Pair;
import network.ConnectionListener;
import network.ConnectionPool;
import network.MessageQueue;
import network.message.Message;
import network.message.client.ClientGeneralRequest;
import network.message.server.DrawCardMessage;
import network.message.server.PlayerListMessage;
import network.message.server.TreasureRequestMessage;
import utils.Debug;

public class MunchkinServer {

	public static void main(String[] args) throws Exception {
		// Generate decks loaded from xml file
		try {
			Decks.loadDecks("Resources/cards.xml");
			System.out.println("Loaded cards from xml file");
		} catch (Exception e) {
			System.err.println("Error: couldn't load cards from xml file");
			e.printStackTrace(System.err);
			System.exit(0);
		}
		
		// Setup server connection listener
		MessageQueue queue = new MessageQueue();
		ConnectionPool pool = new ConnectionPool(6, queue, GameManager.getPlayers());

		ConnectionListener listener = new ConnectionListener(5061, pool);
		System.out.println("Listening on " + listener.toString());
		listener.setAcceptTimeout(120000);
		listener.setConnectionTimeout(60000);
		listener.start();

		
		while (listener.isRunning()) {
			
			if (!queue.isEmpty()) {
				System.out.println("Player num: " + GameManager.getPlayers().size());
				
				Pair<String, Message> pair = queue.remove();
				if (pair.getValue() == null)
					continue;

				switch (pair.getValue().getMessageCode()) {
				case Message.CLT_CHAT_MESSAGE:
					pool.broadcast(pair.getValue());
					break;
				case Message.CLT_GENERAL_REQUEST:
					ClientGeneralRequest req = (ClientGeneralRequest) pair.getValue();
					switch(req.getRequestType()){
					case ClientGeneralRequest.REQUEST_DRAWN_CARD:
						GameManager.getCurrentPlayer().sendMessage(new DrawCardMessage(Decks.getDoorCard().getTitle(),true));
						break;
					case ClientGeneralRequest.REQUEST_PLAYERS_LIST:
						GameManager.getCurrentPlayer().sendMessage(new PlayerListMessage(GameManager.getPlayers()));
						break;
					case ClientGeneralRequest.REQUEST_TREASURE_CARDS:
						GameManager.getCurrentPlayer().sendMessage(new TreasureRequestMessage(Decks.getTreasureCard().getTitle()));
						break;
					}
					break;
				}
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		GameManager.startGame();

	}

}
