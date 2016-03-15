package utils;

import java.util.TimerTask;

import game.GameManager;
import network.message.client.ChatMessage;

public class CountdownTask extends TimerTask{
	protected int count;
	protected final int target;
	protected boolean complete;
	
	public CountdownTask(int target){
		count = 0;
		complete = false;
		this.target = target;
	}
	
	public boolean hasCompleted(){
		return complete;
	}
	
	public void reset(){
		count = 0;
	}
	
	@Override
	public void run() {
		GameManager.broadcastMessage(new ChatMessage("Server", "Game starting in " + (target - count) + " seconds"));
		if(this.count == this.target){
			this.complete = true;
	        this.cancel();
		}
		this.count++;
	}

}
