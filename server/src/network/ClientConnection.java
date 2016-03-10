package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.sun.corba.se.pept.encoding.InputObject;
import com.sun.corba.se.pept.encoding.OutputObject;
import com.sun.corba.se.pept.protocol.MessageMediator;

import network.message.Message;
import network.message.client.ConnectionRequestMessage;
import utils.Debug;

public class ClientConnection implements Runnable{
	private final Socket sock;
	//private BufferedReader reader;
	//private BufferedWriter writer;

	private final ConnectionPool pool;

	private final String clientId;
	private boolean alive;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private InputStream in;
	private OutputStream out;

	public ClientConnection(Socket sock, int timeout, final ConnectionPool pool) {
		this.sock = sock;
		this.pool = pool;
		alive = true;

		try {
			// Set read timeout
			this.sock.setSoTimeout(timeout);
			in=new InputStream() {
				
				@Override
				public int read() throws IOException {
					// TODO Auto-generated method stub
					return 0;
				}
			};
			out = new OutputStream() {
				
				@Override
				public void write(int b) throws IOException {
					// TODO Auto-generated method stub
					
				}
			};
			input = new ObjectInputStream(in);
			output = new ObjectOutputStream(out);
			//reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			//writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			alive = false;
		}

		// Receive connection request message to retrieve client id
		ConnectionRequestMessage message = (ConnectionRequestMessage) read();
		clientId = message.getClientName();
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
			Message msgread = read();
			queue.append(clientId,msgread);
		}

		// Close buffers and socket
		close();
		// Signal thread death to own pool
		pool.signalEnd(this);
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
			output.writeObject(obj);
			output.flush();
		} catch (IOException e) {
			alive = false;
		}
	}

	public void close() {
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
	public String toString(){
		return getClientId() + " @ " + sock.getRemoteSocketAddress() + ":" + sock.getPort();
	}



}
