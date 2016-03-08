package network;

import java.awt.image.BufferedImage;

import client.ClientCard;
import client.MunchkinClient;
import network.message.ChatMessage;
import network.message.ClientGeneralRequest;
import network.message.DrawCardMessage;
import network.message.Message;
import network.message.PlayerListMessage;
import network.message.ShowCardMessage;
import network.message.TreasureRequestMessage;
import user_interface.GameUI;
import user_interface.LobbyUI;

public class GameEventHandler {
	private static PlayerConnection connection;
	private static Thread thread;
	
	private static final String REQUEST_DOOR_CARDS = "DOOR_CARDS";
	private static final String REQUEST_TREASURE_CARDS = "TREASURE_CARDS";
	private static final String REQUEST_PLAYERS_LIST = "PLAYERS_LIST";
	private static final String REQUEST_FIRST_DRAWN_CARD ="FIRST_DRAW";
	

	public static void initialize(PlayerConnection connection){
		GameEventHandler.connection = connection;
		GameEventHandler.thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				GameUI gamepanel = (GameUI)MunchkinClient.getPanel(0);
				do {
					Message received = GameEventHandler.connection.receive();
					if(received != null){
						switch(received.getMessageCode()){
						case Message.CLT_CHAT_MESSAGE:
							ChatMessage chatMessage = (ChatMessage)received;
							LobbyUI lobbyPanel = (LobbyUI)MunchkinClient.getPanel(1);
							lobbyPanel.getScrollList().add_Element(chatMessage.getSender() +": " +chatMessage.getMessage());
							break;
						case Message.DRAW_CARD:
							DrawCardMessage drawnCardMessage=(DrawCardMessage) received;
							ClientCard carddrawn= new ClientCard(drawnCardMessage.getCardName());
							gamepanel.getHandCards().drawCard(carddrawn);
							break;
						case Message.PLAYER_LIST:
							PlayerListMessage playerList = (PlayerListMessage) received;
							String[] players = playerList.getPlayers();
							break;
						case Message.SHOW_CARD:
							ShowCardMessage showCardMessage=(ShowCardMessage)received;
							ClientCard cardshowed = new ClientCard(showCardMessage.getCardName());
							gamepanel.getDrawnCard().setImage(MunchkinClient.getImage(cardshowed.getName()));
						case Message.TREASURE_CARD_REQUEST:
							TreasureRequestMessage treasureRequestMessage=(TreasureRequestMessage)received;
							ClientCard treasureshowed = new ClientCard(treasureRequestMessage.getCardName());
							gamepanel.getHandCards().drawCard(treasureshowed);
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
		connection.send(new ClientGeneralRequest(REQUEST_DOOR_CARDS));
	}

	public static void getDoorCard() {
		connection.send(new ClientGeneralRequest(REQUEST_TREASURE_CARDS));
	}
	public static void getPlayerList() {
		connection.send(new ClientGeneralRequest(REQUEST_PLAYERS_LIST));
	}
	public static void getFirstDrawnCard() {
		connection.send(new ClientGeneralRequest(REQUEST_FIRST_DRAWN_CARD));
	}
}
