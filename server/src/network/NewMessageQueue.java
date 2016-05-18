package network;

import java.util.LinkedList;

import debug.Debug;
import network.message.Message;

public class NewMessageQueue {
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
		Debug.log("Appended: " + message.getMessageCode());
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
		// Wait for that message to come
		while (asyncMessage == null || !asyncMessage.getMessageCode().equals(messageType)) {
			try {
				synchronized (lock) {
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

}
