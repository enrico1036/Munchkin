package network;

import java.io.IOException;

import network.message.ActionResultMessage;
import network.message.ConnectionRequestMessage;
import network.message.Message;

public class PlayerConnection {
	private StreamConnection connection;
	private String playerName;

	public PlayerConnection(){
		connection = new StreamConnection();
		playerName = null;
	}
	
	public PlayerConnection(String hostname, int port, String playerName){
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
		connection.write(req.getFormattedMessage());
		
		// Wait for server response, and check for positive result
		Message result = Message.parse(connection.readLine());
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
		connection.write(message.getFormattedMessage());
	}
	
	public Message receive(){
		return Message.parse(connection.readLine());
	}
	
	public boolean isConnected(){
		return connection.isConnected() && (playerName != null);
	}
	

}
