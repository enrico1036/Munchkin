package network;

import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.util.Pair;
import network.message.Message;

public class MessageQueue {

	private ConcurrentLinkedQueue<Pair<String, Message>> queue;

	public MessageQueue() {
		queue = new ConcurrentLinkedQueue<>();
	}

	public void append(String clientId, Message message) {
		queue.add(new Pair<String, Message>(clientId, message));
		queue.notify();
	}

	public Pair<String, Message> remove() {
		if (!queue.isEmpty())
			return queue.poll();
		return null;
	}

	public boolean waitForData() {
		try {
			if (queue.isEmpty())
				queue.wait();
			else 
				return false;
		} catch (InterruptedException e) {
			return false;
		}
		return true;
	}

	public Pair<String, Message> waitForMessage(String playerName, Message message) {
		Pair<String, Message> pair = null;
		// Check if desired message is in queue
		synchronized (queue) {
			for (int i = 0; i < queue.size(); i++) {
				pair = queue.poll();
				if(pair.getKey().equals(playerName) && 
						pair.getValue().equals(message)){
					return pair;
				} else {
					queue.add(pair);
				}
			}
		}
		// Wait for it to be appended
		while(waitForData()){
			pair = queue.poll();
			if(pair.getKey().equals(playerName) && 
					pair.getValue().equals(message)){
				return pair;
			} else {
				queue.add(pair);
			}
		}
		
		return pair;
	}
	
	public Pair<String, Message> waitForMessage(String playerName, String messageCode) {
		Pair<String, Message> pair = null;
		// Check if desired message is in queue
		synchronized (queue) {
			for (int i = 0; i < queue.size(); i++) {
				pair = queue.poll();
				if(pair.getKey().equals(playerName) && 
						pair.getValue().getMessageCode().equals(messageCode)){
					return pair;
				} else {
					queue.add(pair);
				}
			}
		}
		// Wait for it to be appended
		while(waitForData()){
			pair = queue.poll();
			if(pair.getKey().equals(playerName) && 
					pair.getValue().getMessageCode().equals(messageCode)){
				return pair;
			} else {
				queue.add(pair);
			}
		}
		
		return pair;
	}

	public int size() {
		return queue.size();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

}
