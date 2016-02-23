package network;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;
import network.message.Message;
import sun.security.ssl.HandshakeMessage;

public class IOBuffer {
	private final LinkedList<Pair<String, Message>> inQueue;
	private final HashMap<String, LinkedList<Message>> outQueue;
	
	public IOBuffer(){
		inQueue = new LinkedList<Pair<String, Message>>();
		outQueue = new HashMap<String, LinkedList<Message>>();
	}
	
	public synchronized void pushOut(String recipient, final Message message){
		if(!outQueue.containsKey(recipient))
			outQueue.put(recipient, new LinkedList<Message>());
		outQueue.get(recipient).push(message);
	}
	
	public synchronized void pushIn(String sender, final Message message){
		inQueue.push(new Pair<String, Message>(sender, message));
	}
	
	public synchronized Message popOut(String recipient){
		if(outQueue.containsKey(recipient))
			return outQueue.get(recipient).pop();
		else
			return null;
	}
	
	public synchronized Pair<String, Message> popIn(String sender){
		if(inQueue.size() > 0)
			return inQueue.pop();
		else 
			return null;
	}
	
	public synchronized void clear(){
		inQueue.clear();
		outQueue.clear();
	}
}
