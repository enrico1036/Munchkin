package utils;

import java.util.TimerTask;

import game.GameManager;
import network.message.client.ChatMessage;

public class CountdownTask extends TimerTask {

	public interface CountdownActions {
		public void onTick(final int target, final int count);

		public void onComplete(final int target, final int count);

		public void onCancel(final int target, final int count);
	}

	protected int count;
	protected final int target;
	protected boolean complete;
	protected boolean running;
	protected CountdownActions actions;

	public CountdownTask(int target, CountdownActions actions) {
		count = 0;
		complete = false;
		this.target = target;
		this.actions = actions;
		running = false;
	}

	public boolean hasCompleted() {
		return complete;
	}
	
	public boolean isRunning(){
		return running;
	}

	public void reset() {
		count = 0;
	}

	@Override
	public boolean cancel() {
		if (actions != null)
			actions.onCancel(target, count);
		return super.cancel();
	};

	@Override
	public void run() {
		running = true;
		
		if (actions != null)
			actions.onTick(target, count);
		
		if (count == target) {
			complete = true;
			
			if (actions != null)
				actions.onComplete(target, count);
			
			running = false;
			cancel();
		}
		count++;
	}

}
