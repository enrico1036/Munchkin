package server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import cards.Card;
import game.Decks;
import game.GameManager;
import game.Player;
import javafx.util.Pair;
import network.ConnectionListener;
import network.ConnectionPool;
import network.MessageQueue;
import network.message.Message;
import network.message.client.ClientGeneralRequest;
import network.message.client.UpdateReadyPlayerMessage;
import network.message.server.PlayCardMessage;
import network.message.server.ReadyLobbyMessage;
import network.message.server.PlayCardMessage.Action;
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
		MessageQueue queue = GameManager.getInQueue();
		ConnectionPool pool = new ConnectionPool(6, queue, GameManager.getPlayers());

		ConnectionListener listener = new ConnectionListener(35267, pool);
		System.out.println("Listening on " + listener.toString());
		listener.setAcceptTimeout(0);
		listener.setConnectionTimeout(0);
		listener.start();

		while (listener.isRunning()) {

			if (!queue.isEmpty()) {
				System.out.println("Player num: " + GameManager.getPlayers().size());

				Pair<String, Message> pair = queue.remove();
				if (pair.getValue() != null) {

					System.out.println("From: " + pair.getKey() + " code: " + pair.getValue().getMessageCode());

					switch (pair.getValue().getMessageCode()) {
					case Message.CLT_CHAT_MESSAGE:
						pool.broadcast(pair.getValue());
						break;
					case Message.CLT_GENERAL_REQUEST:
						ClientGeneralRequest req = (ClientGeneralRequest) pair.getValue();
						switch (req.getRequestType()) {
						case ClientGeneralRequest.REQUEST_READY_PLAYER_LIST:
							GameManager.getPlayerByName(pair.getKey()).sendMessage(new ReadyLobbyMessage(GameManager.getPlayers()));
							break;
						}
						break;
					case Message.CLT_SET_LOBBY_STATUS:
						GameManager.getPlayerByName(pair.getKey()).setLobby_ready(!GameManager.getPlayerByName(pair.getKey()).isLobby_ready());
						pool.broadcast(new ReadyLobbyMessage(GameManager.getPlayers()));
						//pool.broadcast(new UpdateReadyPlayerMessage());
						break;

					case Message.CLT_DISCONNECTION:
						GameManager.getPlayers().remove(GameManager.getPlayerByName(pair.getKey()));
						pool.broadcast(new ReadyLobbyMessage(GameManager.getPlayers()));
						//pool.broadcast(new UpdateReadyPlayerMessage());
						break;
					}
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
