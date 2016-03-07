package network.message;

public class ShowCardMessage extends Message{
	
	private String cardName;
	
	public ShowCardMessage(String card){
		super(Message.SHOW_CARD);
		this.cardName = card;
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
