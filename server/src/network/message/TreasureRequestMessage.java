package network.message;

public class TreasureRequestMessage extends Message{

	private String cardName;
	
	public TreasureRequestMessage(String card) {
		super(Message.TREASURE_CARD_REQUEST);
		this.cardName=card;
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
	public String getCardName() {
		return cardName;
	}
}
