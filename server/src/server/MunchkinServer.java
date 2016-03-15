package server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import cards.Card;
import cards.Consumable;
import cards.Monster;
import game.Decks;
import game.GameManager;
import game.Player;
import javafx.util.Pair;
import network.ConnectionListener;
import network.ConnectionPool;
import network.MessageQueue;
import network.message.Message;
import network.message.client.ChatMessage;
import network.message.client.ClientGeneralRequest;
import network.message.client.UpdateReadyPlayerMessage;
import network.message.server.ReadyLobbyMessage;
import network.message.server.PlayCardMessage.Action;
import network.message.server.PlayCardMessage;
import network.message.server.PlayerEquipmentMessage;
import network.message.server.PlayerFullStatsMessage;
import network.message.server.PlayerStatusRequest;
import network.message.server.PopUpMessage;
import utils.CountdownTask;
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
		

		CountdownTask task = new CountdownTask(5);
		
		while (listener.isRunning() && !task.hasCompleted()) {

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
						
						boolean allReady = true;
						for(Player p : GameManager.getPlayers())
							allReady &= p.isLobby_ready();
						
						if(allReady){
							task = new CountdownTask(5);
							new Timer().schedule(task, 0, 1000);
						} else {
							task.cancel();
							GameManager.broadcastMessage(new ChatMessage("server", "Starting stopped"));
						}
						break;

					case Message.CLT_DISCONNECTION:
						GameManager.getPlayers().remove(GameManager.getPlayerByName(pair.getKey()));
						pool.broadcast(new ReadyLobbyMessage(GameManager.getPlayers()));
						//pool.broadcast(new UpdateReadyPlayerMessage());
						break;
					case Message.PLAYER_STATUS_REQUEST:
						PlayerStatusRequest prequest = (PlayerStatusRequest) pair.getValue();
						if(prequest.getPlr_request().equals(PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT))
							GameManager.getPlayerByName(pair.getKey()).sendMessage(new PlayerEquipmentMessage());
						else
							GameManager.getPlayerByName(pair.getKey()).sendMessage(new PlayerFullStatsMessage(GameManager.getPlayerByName(prequest.getPlayer())));
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
		
		// Communicate clients to switch to game scene
		GameManager.broadcastMessage(new PopUpMessage("SEEE WORKA DIO CAR", 5000));
		System.out.println("Lobby ended");
		GameManager.startGame();

	}
	

}
