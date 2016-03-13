package network;

import client.ClientCard;
import client.MunchkinClient;
import network.message.Message;
import network.message.client.ChatMessage;
import network.message.client.ClientGeneralRequest;
import network.message.client.DisconnectionMessage;
import network.message.server.PlayCardMessage;
import network.message.server.PlayCardMessage.Action;
import network.message.server.PlayerStatusRequest;
import network.message.server.ReadyLobbyMessage;
import network.message.server.ReadyStatusMessage;
import user_interface.GameUI;
import user_interface.LobbyUI;

public class GameEventHandler {
	private static PlayerConnection connection;
	private static Thread thread;
	private static String[] players;
	private static String[] readyPlayers;
	private static boolean[] readyStatus;
	
	


	public static void initialize(PlayerConnection connection){
		GameEventHandler.connection = connection;
		GameEventHandler.thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				do {
					Message received = GameEventHandler.connection.receive();
					
					if(received != null){
						GameUI gamepanel = (GameUI)MunchkinClient.getPanel("GameUI");
						LobbyUI lobbyPanel = (LobbyUI)MunchkinClient.getPanel("LobbyUI");
						switch(received.getMessageCode()){
						case Message.CLT_CHAT_MESSAGE:
							ChatMessage chatMessage = (ChatMessage)received;
							lobbyPanel.getScrollList().add_Element(chatMessage.getSender() +": " +chatMessage.getMessage());
							break;
						case Message.PLAY_CARD:
							PlayCardMessage playCardMessage=(PlayCardMessage) received;
							ClientCard carddrawn= new ClientCard(playCardMessage.getCardName());
							if(playCardMessage.getShowed()!=Action.SHOW)
								gamepanel.getHandCards().drawCard(carddrawn);
							else
								gamepanel.getDrawnCard().setImage(MunchkinClient.getImage(carddrawn.getName()));
							break;
						case Message.CLT_READY_STATUS:
							ReadyLobbyMessage readyPlayerList = (ReadyLobbyMessage)received;
							readyPlayers = readyPlayerList.getPlayers();
							readyStatus = readyPlayerList.getStatus();
							lobbyPanel.showPlayer();
						/*case Message.CLT_UPDATE_READY_PLAYER_MESSAGE:
							break;*/
						}	
					}
				} while(GameEventHandler.connection.isConnected());
				
			}
		});
		GameEventHandler.thread.start();
	}
	public static String[] getPlayers(){
		return players;
	}
	public static String[] getReadyPlayer(){
		return readyPlayers;
	}
	
	public static boolean[] getReadyStatus(){
		return readyStatus;
	}
	
	public static void sendMessage(Message message){
		connection.send(message);
	}
	
	public static final PlayerConnection getConnection(){
		return connection;
	}
	
	public static void sendChatMessage(String clientSender,String message){
		connection.send(new ChatMessage(clientSender, message));
	}
	
	public static void setReadyStatus(){
		connection.send(new ReadyStatusMessage());
	}
	
		
	public static void getPlayCard() {
		connection.send(new ClientGeneralRequest(ClientGeneralRequest.REQUEST_PLAY_CARD));
	}
	
	public static void getReadyPlayerList(){
		connection.send(new ClientGeneralRequest(ClientGeneralRequest.REQUEST_READY_PLAYER_LIST));
	}
	
	public static void getPlayerStatistics(String Player){
		connection.send(new PlayerStatusRequest(Player,PlayerStatusRequest.REQUEST_PLAYER_FULL_STATS));
	}

	public static void getPlayerEquipment(String Player){
		connection.send(new PlayerStatusRequest(Player,PlayerStatusRequest.REQUEST_PLAYER_EQUIPMENT));
	}
	
	public static void signalExit(){
		if(connection != null && connection.isConnected()){
			connection.send(new DisconnectionMessage());
			connection.close();
		}
	}
	
}
