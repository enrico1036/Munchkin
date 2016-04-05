package network;

import java.io.IOException;

import network.message.ActionResultMessage;
import network.message.Message;
import network.message.client.ConnectionRequestMessage;

public class PlayerConnection {
	private ServerConnection connection;
	private String playerName;

	public PlayerConnection(){
		connection = new ServerConnection();
		playerName = null;
	}
	
	public PlayerConnection(String hostname, int port, String playerName){
		connection = new ServerConnection();
		this.playerName = playerName;
		establish(hostname, port, playerName);
	}
	
	public boolean establish(String hostname, int port, String playerName){
		// Establish lower level TCP connection to server
		try{
			connection.connect(hostname, port);
		} catch (IOException e){
			return false;
		}
		// Send Munchkin server a ConnectionRequestMessage with the given username
		ConnectionRequestMessage req = new ConnectionRequestMessage(playerName);
		connection.write(req);
		
		// Wait for server response, and check for positive result
		Message result = connection.read();
		if(result != null && result.getMessageCode().equals(Message.SRV_ACTION_RESULT)){
			if(((ActionResultMessage) result).isAllowed()){
				this.playerName = playerName;
				return true;
			}
		}
		
		return false;
	}
	
	public String getConnectedPlayerName(){
		return playerName;
	}
	
	public void send(Message message){
		connection.write(message);
	}
	
	public Message receive(){
		return (Message) (connection.read());
	}
	
	public boolean isConnected(){
		return connection.isConnected() && (playerName != null);
	}
	
	public void close(){
		connection.disconnect();
	}

}
