package network;

import java.awt.image.BufferedImage;

import client.MunchkinClient;
import dataStructure.Data;
import javafx.stage.Popup;
import network.message.Message;
import network.message.client.ChatMessage;
import network.message.client.ClientGeneralRequest;
import network.message.client.DisconnectionMessage;
import network.message.client.PlayerStatusRequest;
import network.message.client.PopUpResultMessage;
import network.message.client.SelectedCardMessage;
import network.message.server.PlayCardMessage;
import network.message.server.PopUpMessage;
import network.message.server.ReadyLobbyMessage;
import network.message.server.ReadyStatusMessage;
import network.message.server.StateUpdateMessage;
import network.message.server.PlayCardMessage.Action;
import network.message.server.PlayerEquipmentMessage;
import network.message.server.PlayerFullStatsMessage;
import user_interface.ClientCard;
import user_interface.ConnectionDialog;
import user_interface.GameUI;
import user_interface.LobbyUI;
import user_interface.PopUpDialog;

public class GameEventHandler {
	private static PlayerConnection connection;
	private static Thread thread;
	private static String[] players;
	private static boolean[] readyStatus;
	 

	public static void initialize(PlayerConnection connection) {
		GameEventHandler.connection = connection;
		GameEventHandler.thread = new Thread(new Runnable() {

			@Override
			public void run() {

				do {
					Message received = GameEventHandler.connection.receive();
					System.out.println(received.getMessageCode());

					if (received != null) {
						//GameUI gameUIpanel = (GameUI) MunchkinClient.getPanel("GameUI");
						LobbyUI lobbyPanel = (LobbyUI) MunchkinClient.getPanel("LobbyUI");
						switch (received.getMessageCode()) {
						case Message.CLT_CHAT_MESSAGE:
							ChatMessage chatMessage = (ChatMessage) received;
							lobbyPanel.getChatArea().appendLine(chatMessage.getSender() + ": " + chatMessage.getMessage());
							break;
						case Message.PLAY_CARD:
							PlayCardMessage playCardMessage = (PlayCardMessage) received;
							// Create new HandCard from its name
							ClientCard carddrawn = new ClientCard(playCardMessage.getCardName());
							switch (playCardMessage.getAction()) {
							case SHOW:
								Data.getTable().addCard(carddrawn.getTitle());
								break;
							case DRAW:
								Data.getHand().addCard(carddrawn.getTitle());
								break;
							case DISCARD:
								Data.getDiscardDeck().getCards().set(0,carddrawn.getName());
								break;
							case REMOVE:
								Data.getHand().removeCard(carddrawn.getTitle());
								break;
							}
							break;
						case Message.CLT_READY_STATUS:
							ReadyLobbyMessage readyPlayerList = (ReadyLobbyMessage) received;
							players = readyPlayerList.getPlayers();
							readyStatus = readyPlayerList.getStatus();
							lobbyPanel.showPlayer();
							break;

						case Message.POPUP:
							PopUpMessage popup = (PopUpMessage) received;

							// Show popup dialog and wait for response
							PopUpDialog dialog = new PopUpDialog(popup.getText(), popup.getButton1(), popup.getButton2(), popup.getTimeout_ms(), popup.getMin_val(), popup.getMax_val());
							dialog.setVisible(true);

							// Send response message based on user's choice
							if (dialog.wasTimedOut()) {
								sendMessage(new PopUpResultMessage(connection.getConnectedPlayerName()));
							} else {
								sendMessage(new PopUpResultMessage(dialog.wasButton1Pressed(), dialog.wasButton2Pressed(), dialog.getSpinnerValue(), connection.getConnectedPlayerName()));
							}
							break;

						case Message.PLAYER_FULL_STATS:
							PlayerFullStatsMessage statistics = (PlayerFullStatsMessage) received;
							Data.getPlayer(statistics.getPlayerName()).setStats(statistics.getHandSize(),
																				statistics.getClassCard(),
																				statistics.getRaceCard(),
																				statistics.getCombatLevel(),
																				statistics.getLevel());

							break;
						case Message.PLAYER_EQUIPMENT:
							PlayerEquipmentMessage equip = (PlayerEquipmentMessage) received;
							Data.getPlayer(equip.getPlayerName()).setEquipments(equip.getHead().getTitle(),
																				equip.getHand1().getTitle(),
																				equip.getHand2().getTitle(), 
																				equip.getBody().getTitle(),
																				equip.getFeet().getTitle());
							break;
						case Message.STATE_UPDATE:
							StateUpdateMessage update = (StateUpdateMessage) received;
							if (update.getState().equals("begin")) {
								for (String player : players) {
									Data.addPlayer(player);
								}
								MunchkinClient.getPanels().put("GameUI", new GameUI(MunchkinClient.getWindow(),MunchkinClient.getImage("panel_background")));
								MunchkinClient.getWindow().SetActivePanel(MunchkinClient.getPanel("GameUI"));
								break;

							}
						}
					}
				} while (GameEventHandler.connection.isConnected());

			}

		});
	}

	public static void startListening() {
		GameEventHandler.thread.start();
	}

	public static String[] getPlayers() {
		return players;
	}

	public static boolean[] getReadyStatus() {
		return readyStatus;
	}

	public static void sendMessage(Message message) {
		connection.send(message);
	}

	public static final PlayerConnection getConnection() {
		return connection;
	}

	public static void sendChatMessage(String clientSender, String message) {
		connection.send(new ChatMessage(clientSender, message));
	}

	public static void setReadyStatus() {
		connection.send(new ReadyStatusMessage());
	}

	public static void selectCard(String cardName) {
		connection.send(new SelectedCardMessage(cardName));
	}

	public static void getReadyPlayerList() {
		connection.send(new ClientGeneralRequest(ClientGeneralRequest.REQUEST_READY_PLAYER_LIST));
	}

	public static void getPlayerStatistics(String player) {
		connection.send(new PlayerStatusRequest(player, PlayerStatusRequest.REQUEST_PLAYER_FULL_STATS));
	}

	public static void getPlayerEquipment(String player) {
		connection.send(new PlayerStatusRequest(player, PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT));
	}

	public static void signalExit() {
		if (connection != null && connection.isConnected()) {
			connection.send(new DisconnectionMessage());
			connection.close();
		}
	}

}
