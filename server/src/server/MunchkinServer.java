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
import network.ClientConnection;
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
import utils.PlayerEventListener;

public class MunchkinServer implements PlayerEventListener{

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

		
		
		// Communicate clients to switch to game scene
		GameManager.broadcastMessage(new PopUpMessage("SEEE WORKA DIO CAR", 5000));
		System.out.println("Lobby ended");
		GameManager.startGame();

	}

	@Override
	public void chatMessage(Message message) {
		GameManager.broadcastMessage(message);		
	}

	@Override
	public void lobbyStatusChanged(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerConnected(String username, ClientConnection connection) {
		boolean refuseConnection = false;
		
		for(Player p: GameManager.getPlayers()) {
			if(p.getUsername() == username) {
				if(p.isConnected())
					refuseConnection = true;
				else
					p.setConnection(connection);
			}
		}
		
	}

	@Override
	public void playerDisconnected(Player player) {
		// TODO Auto-generated method stub
		
	}
	

}
