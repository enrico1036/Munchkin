package network.message;

public final class ConnectionRequestMessage extends Message{
	private final String clientName;
	
	public ConnectionRequestMessage(String clientName) {
		super(Message.CLT_REQUEST_CONNECTION);
		this.clientName = clientName;
	}
	
	public String clientName(){
		return clientName;
	}

	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(clientName);
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}

}
