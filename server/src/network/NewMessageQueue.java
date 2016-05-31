package network;

import java.util.LinkedList;

import debug.Debug;
import network.message.Message;

public class NewMessageQueue {
	private static long MAX_WAIT_MILLIS = 30000; 
	
	private LinkedList<Message> queue;
	private Message asyncMessage;
	private boolean onWait;
	private Object lock;
	

	public NewMessageQueue() {
		queue = new LinkedList<>();
		onWait = false;
		lock = new Object();
	}

	public Message removeHead() {
		synchronized (queue) {
			if (!queue.isEmpty()) {
				return queue.removeFirst();
			} else {
				return null;
			}
		}
	}

	public void append(Message message) {
		if (onWait) {
			asyncMessage = message;
			synchronized (lock) {
				lock.notifyAll();
			}
		} else {
			synchronized (queue) {
				queue.addLast(message);
			}
		}
	}

	public Message waitFor(String messageType) {
		onWait = true;
		// Loop through current elements
		synchronized (messageType) {
			for (Message msg : queue) {
				if (msg.getMessageCode().equals(messageType)) {
					queue.remove(msg);
					return msg;
				}
			}
		}

		asyncMessage = null;
		long millis = System.currentTimeMillis();
		// Wait for that message to come
		while (asyncMessage == null || !asyncMessage.getMessageCode().equals(messageType)) {
			try {
				synchronized (lock) {
					// Exit in case wait exits before timeout
					//if(System.currentTimeMillis() - millis > MAX_WAIT_MILLIS)
						//break;
					// Lock for 30 sec max
					lock.wait();
				}
			} catch (InterruptedException e) {
				continue;
			}
			// Discard unwanted messages
		}
		onWait = false;

		return asyncMessage;
	}
	
	public void clearQueue() {
		queue.clear();
	}

}
