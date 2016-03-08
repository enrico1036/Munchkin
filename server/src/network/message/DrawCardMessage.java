package network.message;

public class DrawCardMessage extends Message{
	private String cardName;
	private boolean showed;
	
	public DrawCardMessage(String cardName,boolean show) {
		super(Message.DRAW_CARD);
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
		return null;
	}

}
