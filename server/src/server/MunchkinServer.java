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

		
		
		// Communicate clients to switch to game scene
		GameManager.broadcastMessage(new PopUpMessage("SEEE WORKA DIO CAR", 5000));
		System.out.println("Lobby ended");
		GameManager.startGame();

	}
	

}
