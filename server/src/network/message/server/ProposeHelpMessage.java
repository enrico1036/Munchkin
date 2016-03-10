package network.message.server;

import network.message.Message;

public class ProposeHelpMessage extends Message {

	private String helperName;
	private int helperForce;
	private int promisedTreasure;
	
	public ProposeHelpMessage(String helperName,int helperForce,int promisedTreasure) {
		super(Message.CLT_PROPOSE_HELP);
		
		this.helperName=helperName;
		this.helperForce=helperForce;
		this.promisedTreasure=promisedTreasure;

	}

	public String getHelperName() {
		return helperName;
	}

	public int getHelperForce() {
		return helperForce;
	}

	public int getPromisedTreasure() {
		return promisedTreasure;
	}
	
	

}
