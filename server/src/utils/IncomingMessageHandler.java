package utils;

import cards.Card;
import cards.Category;
import game.GameManager;
import game.Player;
import network.message.Message;
import network.message.client.ClientGeneralRequest;
import network.message.client.ConnectionRequestMessage;
import network.message.client.SelectedCardMessage;
import network.message.server.PlayerEquipmentMessage;
import network.message.server.PlayerFullStatsMessage;
import network.message.server.PlayerStatusRequest;
import network.message.server.ReadyLobbyMessage;

public class IncomingMessageHandler {

	/**
	 * Call right method depending on message, if no methods was called return false
	 * 
	 * @param pair
	 * @return true if message was used, false otherwise
	 */
	public static boolean handle(Message message, Player player) {

		boolean messageUsed = true;

		if (message == null)
			return true;

		System.out.println("From: " + player.getUsername() + " code: " + message.getMessageCode());

		switch (message.getMessageCode()) {
		case Message.CLT_CHAT_MESSAGE:
			player.getEventListener().chatMessage(message);
			break;

		case Message.CLT_REQUEST_CONNECTION:
			ConnectionRequestMessage connReq = (ConnectionRequestMessage) message;
			player.setUsername(connReq.getClientName());
			player.getEventListener().playerConnected(player);
			break;

		case Message.CLT_GENERAL_REQUEST:
			ClientGeneralRequest req = (ClientGeneralRequest) message;
			switch (req.getRequestType()) {
			case ClientGeneralRequest.REQUEST_READY_PLAYER_LIST:
				player.sendMessage(new ReadyLobbyMessage(GameManager.getPlayers()));
				break;
			}
			break;

		case Message.CLT_SET_LOBBY_STATUS:
			player.getEventListener().lobbyStatusChanged(player);
			break;

		case Message.CLT_DISCONNECTION:
			player.getEventListener().playerDisconnected(player);
			break;

		case Message.PLAYER_STATUS_REQUEST:
			PlayerStatusRequest playerStatRequest = (PlayerStatusRequest) message;
			if (playerStatRequest.getStatusRequest().equals(PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT))
				player.sendMessage(new PlayerEquipmentMessage(player));
			else
				player.sendMessage(new PlayerFullStatsMessage(GameManager.getPlayerByName(playerStatRequest.getPlayer())));
			break;
			
		case Message.CLT_CARD_SELECTED:
			SelectedCardMessage selectedCard = (SelectedCardMessage) message;
			Card card = player.getHandCard(selectedCard.getCardName());
			if(card.getCategory() == Category.Curse){
				card.effect(null);
			}
			break;

		default:
			messageUsed = false;
			break;
		}

		return messageUsed;
	}

}
