package utils;

import java.util.Timer;

import game.GameManager;
import game.Player;
import javafx.util.Pair;
import network.message.Message;
import network.message.client.ChatMessage;
import network.message.client.ClientGeneralRequest;
import network.message.server.PlayerEquipmentMessage;
import network.message.server.PlayerFullStatsMessage;
import network.message.server.PlayerStatusRequest;
import network.message.server.ReadyLobbyMessage;

public class IncomingMessageHandler {

	/**
	 * Call right method depending on message, if no methods was called return false
	 * @param pair
	 * @return true if message was used, false otherwise
	 */
	public static boolean handle(Pair<String, Message> pair) {
		
		boolean messageUsed = true;
		
		CountdownTask task = new CountdownTask(5);

		if (pair.getValue() != null) {

			System.out.println("From: " + pair.getKey() + " code: " + pair.getValue().getMessageCode());

			switch (pair.getValue().getMessageCode()) {
			case Message.CLT_CHAT_MESSAGE:
				GameManager.broadcastMessage(pair.getValue());
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
				GameManager.broadcastMessage(new ReadyLobbyMessage(GameManager.getPlayers()));

				boolean allReady = true;
				for (Player p : GameManager.getPlayers())
					allReady &= p.isLobby_ready();

				if (allReady) {
					task = new CountdownTask(5);
					new Timer().schedule(task, 0, 1000);
				} else {
					task.cancel();
					GameManager.broadcastMessage(new ChatMessage("server", "Starting stopped"));
				}
				break;

			case Message.CLT_DISCONNECTION:
				GameManager.getPlayers().remove(GameManager.getPlayerByName(pair.getKey()));
				GameManager.broadcastMessage(new ReadyLobbyMessage(GameManager.getPlayers()));
				// pool.broadcast(new UpdateReadyPlayerMessage());
				break;
			case Message.PLAYER_STATUS_REQUEST:
				PlayerStatusRequest prequest = (PlayerStatusRequest) pair.getValue();
				if (prequest.getPlr_request().equals(PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT))
					GameManager.getPlayerByName(pair.getKey()).sendMessage(new PlayerEquipmentMessage());
				else
					GameManager.getPlayerByName(pair.getKey()).sendMessage(new PlayerFullStatsMessage(GameManager.getPlayerByName(prequest.getPlayer())));
				break;
			default:
				messageUsed = false;
			}
		}

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return messageUsed;
	}

}
