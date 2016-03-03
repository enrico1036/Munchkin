package network.message;

public class DrawnCardMessage extends Message{
	private String cardName;
	public DrawnCardMessage(String cardName) {
		super(Message.DRAWN_CARD);
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
		return null;
	}

}
