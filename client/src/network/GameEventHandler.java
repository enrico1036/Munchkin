package network;

import client.ClientCard;
import client.MunchkinClient;
import network.message.ChatMessage;
import network.message.DoorRequestMessage;
import network.message.DrawnCardMessage;
import network.message.Message;
import network.message.PlayerListMessage;
import network.message.TreasureRequestMessage;
import user_interface.GameUI;
import user_interface.LobbyUI;

public class GameEventHandler {
	private static PlayerConnection connection;
	private static Thread thread;

	public static void initialize(PlayerConnection connection){
		GameEventHandler.connection = connection;
		GameEventHandler.thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				do {
					Message received = GameEventHandler.connection.receive();
					if(received != null){
						switch(received.getMessageCode()){
						case Message.CLT_CHAT_MESSAGE:
							ChatMessage chatMessage = (ChatMessage)received;
							LobbyUI lobbyPanel = (LobbyUI)MunchkinClient.getPanel(1);
							lobbyPanel.getScrollList().add_Element(chatMessage.getSender() +": " +chatMessage.getMessage());
							break;
						case Message.DRAWN_CARD:
							DrawnCardMessage drawnCardMessage=(DrawnCardMessage) received;
							ClientCard card= new ClientCard(drawnCardMessage.getCardName());
							GameUI gamepanel = (GameUI)MunchkinClient.getPanel(0);
							gamepanel.getHandCards().drawCard(card);
							break;
						case Message.PLAYER_LIST:
							PlayerListMessage playerList = (PlayerListMessage) received;
							String[] players = playerList.getPlayers();
							break;
						}
					}
				} while(GameEventHandler.connection.isConnected());
				
			}
		});
		GameEventHandler.thread.start();
	}
	
	public static void sendMessage(Message message){
		connection.send(message);
	}
	public static final PlayerConnection getConnection(){
		return connection;
	}
	public static void getTreasureCard() {
		connection.send(new TreasureRequestMessage());
	}

	public static void getDoorCard() {
		connection.send(new DoorRequestMessage());
	}
	public static void getPlayerList() {
		connection.send(new PlayerListMessage());
	}
	
}
