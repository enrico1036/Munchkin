package network.message;

public class TreasureRequestMessage extends Message{

	public TreasureRequestMessage() {
		super(Message.TREASURE_CARD_REQUEST);
		
	}

	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.MSG_TERMINATOR);
		return null;
	}

}
