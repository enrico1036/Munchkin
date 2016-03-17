package utils;

import java.util.TimerTask;

import game.GameManager;
import network.message.client.ChatMessage;

public class CountdownTask extends TimerTask{
	
	public interface CountdownActions {
		public void onTick(final int target, final int count);
		public void onComplete(final int target, final int count);
		public void onCancel(final int target, final int count);
	}
	
	protected int count;
	protected final int target;
	protected boolean complete;
	protected final CountdownActions actions;
	
	public CountdownTask(int target, CountdownActions actions){
		count = 0;
		complete = false;
		this.target = target;
		this.actions = actions;
	}
	
	public boolean hasCompleted(){
		return complete;
	}
	
	public void reset(){
		count = 0;
	}
	
	@Override
	public boolean cancel() {
		actions.onCancel(target, count);
		return super.cancel();
	};
	
	@Override
	public void run() {
		actions.onTick(target, count);
		if(this.count == this.target){
			this.complete = true;
			actions.onComplete(target, count);
	        this.cancel();
		}
		this.count++;
	}

}
