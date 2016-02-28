package network.message;

public class DoorRequestMessage extends Message{

	public DoorRequestMessage() {
		super(Message.DOOR_CARD_REQUEST);
	}

	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.MSG_TERMINATOR);
		return null;
	}

}
