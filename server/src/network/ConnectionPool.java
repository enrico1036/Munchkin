package network;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import javafx.util.Pair;
import network.message.ActionResultMessage;
import network.message.Message;

public class ConnectionPool {
	private final ConcurrentHashMap<String, ClientConnection> connections;
	private final ExecutorService executor;
	private final MessageQueue inQueue;
	
	public ConnectionPool(int capacity, MessageQueue inQueue){
		connections = new ConcurrentHashMap<>(capacity);
		executor =Executors.newFixedThreadPool(capacity);
		this.inQueue = inQueue;
	}
	
	public void add(final ClientConnection connection){
		// Prevent the existence of two or more different connections
		// with the same client id (each player corresponds to only one connection)
		if(connections.containsKey(connection.getClientId())){
			// Deny connection and close it
			ActionResultMessage result = new ActionResultMessage(ActionResultMessage.ACTION_DENIED, "Player name already in use");
			connection.write(result.getFormattedMessage());
			connection.close();
		}
		// Confirm successful connection
		ActionResultMessage result = new ActionResultMessage(ActionResultMessage.ACTION_ALLOWED, null);
		connection.write(result.getFormattedMessage());
		// Add to map
		connections.put(connection.getClientId(), connection);
		// Execute worker run() method
		executor.execute(connection);
		
		System.out.println("Connection started: " + connection.toString());
	}
	
	public int size(){
		return connections.size();
	}
	
	public void signalEnd(ClientConnection connection){
		connections.remove(connection.getClientId());
		System.out.println("Connection ended: " + connection.toString());
	}

	public final MessageQueue getInputQueue() {
		return inQueue;
	}
	
	public void broadcast(Message message){
		for(Map.Entry<String, ClientConnection> pair : connections.entrySet()){
			ClientConnection conn = pair.getValue();
			conn.write(message.getFormattedMessage());
		}
	}
	
	public void closeAll(){
		// Await termination of the threads still running
		// If not possible, force shutdown
		try {
			if(!executor.awaitTermination(10, TimeUnit.SECONDS)){
				ArrayList<Runnable> list = (ArrayList<Runnable>) executor.shutdownNow();
				for (Runnable r : list){
					((ClientConnection) r).close();
				}
			}
		} catch (InterruptedException e) {
			executor.shutdown();
		}
	}
}
