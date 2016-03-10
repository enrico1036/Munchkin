package network.message.server;

import network.message.Message;

public class AnswerHelpMessage extends Message {

	public static enum Answer {ACCEPT,REFUSE};
	private Answer ans;
	
	public AnswerHelpMessage(Answer ans) {
		super(Message.CLT_ANSWER_HELP);
		this.ans=ans;
	}

}
