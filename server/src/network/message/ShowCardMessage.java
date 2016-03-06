package network.message;

public class ShowCardMessage extends Message{
	
	private String cardname;
	
	public ShowCardMessage(String card){
		super(Message.SHOW_CARD);
		this.cardname = card;
	}

	@Override
	public String getFormattedMessage() {
			StringBuilder builder = new StringBuilder();
			builder.append(messageCode);
			builder.append(Message.ARG_SEPARATOR);
			builder.append(cardname);
			builder.append(Message.MSG_TERMINATOR);
			return builder.toString();
	}

}
