package network;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import network.message.ActionResultMessage;

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
		// Add to map
		connections.put(connection.getClientId(), connection);
		// Execute worker run() method
		executor.execute(connection);
	}
	
	public int size(){
		return connections.size();
	}
	
	public void signalEnd(ClientConnection conn){
		connections.remove(conn.getClientId());
	}

	public final MessageQueue getInputQueue() {
		return inQueue;
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
