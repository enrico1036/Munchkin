package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import game.Player;
import network.message.Message;
import network.message.client.ConnectionRequestMessage;
import utils.IncomingMessageHandler;

public class ClientConnection implements Runnable {
	private final Socket sock;
	private Player owner;
	private boolean alive;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private MessageQueue queue;

	public ClientConnection(Socket sock, int timeout) {
		this.sock = sock;
		owner = null;
		alive = true;

		try {
			// Set read timeout
			this.sock.setSoTimeout(timeout);

			output = new ObjectOutputStream(sock.getOutputStream());
			output.flush();

			input = new ObjectInputStream(sock.getInputStream());
			// reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			// writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			alive = false;
		}
	}

	public boolean isAlive() {
		return alive && sock.isConnected() && !sock.isInputShutdown() && !sock.isOutputShutdown();
	}

	public void attachToPlayer(Player player) {
		owner = player;
	}
	
	public void attachToQueue(MessageQueue queue){
		this.queue = queue;
	}

	@Override
	public void run() {

		// Terminate thread if the socket does not exist
		if (sock == null) {
			close();
			return;
		}
		// Loop until client disconnects or read/write buffers close
		while (alive && sock.isConnected() && !sock.isInputShutdown() && !sock.isOutputShutdown()) {
			// Read from network and append to own channel in IOBuffer
			Message msgread = read();
			if(!IncomingMessageHandler.handle(msgread, owner)){
				queue.append(owner.getUsername(), msgread);
			}
		}

		// Close buffers and socket
		close();
		// Signal thread death to own pool
	}

	public Message read() {
		Message line = null;
		try {
			try {
				line = (Message) input.readObject();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		} catch (IOException e) {
			alive = false;
		} finally {
			if (line == null)
				alive = false;
		}
		return line;
	}

	public void write(Message obj) {
		try {
			synchronized (output) {
				output.writeObject(obj);
				output.flush();
			}
			// Thread.sleep(100);
		} catch (IOException/* | InterruptedException */ e) {
			alive = false;
		}
	}

	public synchronized void close() {
		alive = false;
		try {
			output.close();
			input.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return sock.getRemoteSocketAddress() + ":" + sock.getPort();
	}

}
