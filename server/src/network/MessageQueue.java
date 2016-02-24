package network;

import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.util.Pair;
import network.message.Message;

public class MessageQueue {
	
	private ConcurrentLinkedQueue<Pair<String, Message>> queue;
	
	public MessageQueue(){
		queue = new ConcurrentLinkedQueue<>();
	}
	
	public void append(String clientId, Message message){
		queue.add(new Pair<String, Message>(clientId, message));
	}
	
	public Pair<String, Message> remove(){
		if(!queue.isEmpty())
			return queue.poll();
		return null;
	}
	
	public int size(){
		return queue.size();
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
}
