package network;

import client.MunchkinClient;
import javafx.stage.Popup;
import network.message.Message;
import network.message.client.ChatMessage;
import network.message.client.ClientGeneralRequest;
import network.message.client.DisconnectionMessage;
import network.message.client.PopUpResultMessage;
import network.message.client.SelectedCardMessage;
import network.message.server.PlayCardMessage;
import network.message.server.PlayerStatusRequest;
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
import user_interface.HandCards;
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
						GameUI gamepanel = (GameUI) MunchkinClient.getPanel("GameUI");
						LobbyUI lobbyPanel = (LobbyUI) MunchkinClient.getPanel("LobbyUI");
						switch (received.getMessageCode()) {
						case Message.CLT_CHAT_MESSAGE:
							ChatMessage chatMessage = (ChatMessage) received;
							lobbyPanel.getChatArea().appendLine(chatMessage.getSender() + ": " + chatMessage.getMessage());
							break;
						case Message.DRAW_CARD:
							PlayCardMessage playCardMessage = (PlayCardMessage) received;
							HandCards carddrawn = new HandCards(playCardMessage.getCard().getTitle());
							switch (playCardMessage.getAction()) {
							case SHOW:
								gamepanel.getDrawnCard().setImage(MunchkinClient.getImage(carddrawn.getName()));
								break;
							case DRAW:
								gamepanel.getHandCards().drawCard(carddrawn);
								break;
							case DISCARD:
								// TODO CASE DISCARD
								break;
							case REMOVE:
								// TODO CASE REMOVE
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
							if (statistics.getPlayerName().equals(GameEventHandler.getConnection().getConnectedPlayerName())) {
								gamepanel.changeStatistics(statistics.getLevel(), statistics.getPower(), statistics.getPlayerClass().getTitle(), statistics.getPlayerRace().getTitle(), statistics.getPlayerNumCards());
							} else {
								int i = 0;
								boolean founded = false;
								while (!founded) {
									if (!(gamepanel.getOpponentPlayers()[i].getPlayerName().equals(statistics.getPlayerName())))
										i++;
									else {
										founded = true;
										gamepanel.getOpponentPlayers()[i].changeStatistics(statistics.getLevel(), statistics.getPower(), statistics.getPlayerClass().getTitle(), statistics.getPlayerRace().getTitle(), statistics.getPlayerNumCards());

									}

								}
							}

							break;
						case Message.PLAYER_EQUIPMENT:
							PlayerEquipmentMessage equip = (PlayerEquipmentMessage) received;
							if (equip.getPlayerName().equals(GameEventHandler.getConnection().getConnectedPlayerName())) {
								gamepanel.changeEquipment(equip.getHead(), equip.getHand1(), equip.getHand2(), equip.getBody(), equip.getFeet());

							} else {
								int i = 0;
								boolean founded = false;
								while (!founded) {
									if (!(gamepanel.getOpponentPlayers()[i].getPlayerName().equals(equip.getPlayerName())))
										i++;
									else {
										founded = true;
										gamepanel.getOpponentPlayers()[i].getDetailsPanel().changeEquipment(equip.getHead(), equip.getHand1(), equip.getHand2(), equip.getBody(), equip.getFeet());
									}
								}
							}
							break;
						case Message.STATE_UPDATE:
							StateUpdateMessage update = (StateUpdateMessage) received;
							if (update.getState().equals("begin")) {
								MunchkinClient.getPanels().put("GameUI", new GameUI(MunchkinClient.getWindow()));
								MunchkinClient.getWindow().SetActivePanel(MunchkinClient.getPanel("GameUI"));
								break;
								// TODO @GAMBI DEVO AGGIUNGERE DICERESULTMESSAGE? A: SI e quando arriva far vedere il risultato dei dadi
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

	public static void getPlayerStatistics(String Player) {
		connection.send(new PlayerStatusRequest(Player, PlayerStatusRequest.REQUEST_PLAYER_FULL_STATS));
	}

	public static void getPlayerEquipment(String Player) {
		connection.send(new PlayerStatusRequest(Player, PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT));
	}

	public static void signalExit() {
		if (connection != null && connection.isConnected()) {
			connection.send(new DisconnectionMessage());
			connection.close();
		}
	}

}
