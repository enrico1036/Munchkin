package network;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ConnectionHandler implements Runnable{

	private final ServerSocket servSock;
	private final ExecutorService execService;
	private boolean onRun;
	
	private int connTimeout;
	
	public ConnectionHandler(int port, int maxConnections) throws IOException{
		this.servSock = new ServerSocket(port);
		this.execService = Executors.newFixedThreadPool(maxConnections);
		this.onRun = true;
		setAcceptTimeout(5000);
	}
	
	public int aliveConnections(){
		return 0; // TODO
	}
	
	public void setAcceptTimeout(int millis){
		try {
			this.servSock.setSoTimeout(millis);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void setConnectionTimeout(int millis){
		connTimeout = millis;
	}
	
	public void stop(){
		onRun = false;
	}

	@Override
	public void run() {
		ClientConnection conn;
		// Loop until stop() is called or IOException occurs
		while(onRun){
			try {
				// Accept new socket and create a new runnable ClientConnection
				conn = new ClientConnection(servSock.accept(), connTimeout);
				// Execute connection as a new thread
				execService.execute(conn);
			} catch (SocketTimeoutException e) {
				continue;
			} catch (IOException e){
				e.printStackTrace();
				onRun = false;
			}
		}
		
		// Close server socket and wait until all connections are terminated
		try {
			servSock.close();
		} catch (IOException e) {}
		
		// Await termination of the threads still running
		// If not possible, force shutdown
		try {
			if(!execService.awaitTermination(10, TimeUnit.SECONDS)){
				ArrayList<Runnable> list = (ArrayList<Runnable>) execService.shutdownNow();
				for (Runnable r : list){
					((ClientConnection) r).stop();
				}
			}
		} catch (InterruptedException e) {
			execService.shutdown();
		}
	}
	
	
}
