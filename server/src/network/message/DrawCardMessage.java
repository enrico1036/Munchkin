package network.message;

public class DrawCardMessage extends Message{
	private String cardName;
	public DrawCardMessage(String cardName) {
		super(Message.DRAW_CARD);
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
