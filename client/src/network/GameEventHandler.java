package network;

import client.MunchkinClient;
import network.message.ChatMessage;
import network.message.Message;
import network.message.TreasureRequestMessage;
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
							ChatMessage message = (ChatMessage)received;
							LobbyUI lobbyPanel = (LobbyUI)MunchkinClient.getPanel(1);
							lobbyPanel.getScrollList().add_Element(message.getSender() +": " +message.getMessage());
							break;
							
						case Message.DRAWN_CARD:
							// TODO
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

	}
}
