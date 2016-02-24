package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

import network.message.ConnectionRequestMessage;
import network.message.Message;
import utils.Debug;

public class ClientConnection implements Runnable {
	private final Socket sock;
	private BufferedReader reader;
	private BufferedWriter writer;

	private final ConnectionPool pool;

	private final String clientId;
	private boolean alive;

	public ClientConnection(Socket sock, int timeout, final ConnectionPool pool) {
		this.sock = sock;
		this.pool = pool;
		alive = true;

		try {
			// Set read timeout
			this.sock.setSoTimeout(timeout);
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			alive = false;
		}

		// Receive connection request message to retrieve client id
		ConnectionRequestMessage message = (ConnectionRequestMessage) Message.parse(readLine());
		clientId = message.getClientName();

		System.out.println(getClientId() + " created");
	}

	public String getClientId() {
		return clientId;
	}

	public boolean isAlive() {
		return alive;
	}

	@Override
	public void run() {
		final MessageQueue queue = pool.getInputQueue();

		// Terminate thread if the socket does not exist
		if (sock == null) {
			close();
			return;
		}
		// Loop until client disconnects or read/write buffers close
		while (alive && sock.isConnected() && !sock.isInputShutdown() && !sock.isOutputShutdown()) {
			// Read from network and append to own channel in IOBuffer
			String read = readLine();
			queue.append(clientId, Message.parse(read));

			System.out.println(getClientId() + ": " + read);
		}

		// Close buffers and socket
		close();
		// Signal thread death to own pool
		pool.signalEnd(this);

		System.out.println(getClientId() + " ended");
	}

	public String readLine() {
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			alive = false;
		} finally {
			if (line == null)
				alive = false;
		}
		return line;
	}

	public void write(String str) {
		try {
			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			alive = false;
		}
	}

	public void close() {
		alive = false;
		try {
			writer.close();
			reader.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
