package network.message.server;

import network.message.Message;

public class PlayCardMessage extends Message{
	private String cardName;
	private boolean showed;
	
	public PlayCardMessage(String cardName,boolean show) {
		super(Message.PLAY_CARD);
		this.cardName = cardName;
		this.showed=show;
	}
	
	
	
	public String getCardName() {
		return cardName;
	}
	public boolean getShowed(){
		return showed;
	}

	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(cardName);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(showed);
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}

}
