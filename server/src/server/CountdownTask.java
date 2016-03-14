package server;

import java.util.TimerTask;

import game.GameManager;
import network.message.client.ChatMessage;

public abstract class CountdownTask extends TimerTask{
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
	
	
	
}
