package network.message.client;

import network.message.Message;

public class SelectedCardMessage extends Message {

	private String cardName;
	
	public SelectedCardMessage(String cardName) {
		super(Message.CLT_CARD_SELECTED);
		this.cardName = cardName;
	}
	
	public String getCardName() {
		return cardName;
	}

	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(cardName);
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}

}
