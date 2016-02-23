package network.message;

public final class ChatMessage extends Message{
	private final String clientSender;
	private final String message;
	
	public ChatMessage(String clientSender, String message) {
		super(Message.CLT_CHAT_MESSAGE);
		this.clientSender = clientSender;
		this.message = message;
	}
	
	public String getSender(){
		return clientSender;
	}
	
	public String getMessage(){
		return message;
	}

	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(clientSender);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(message);
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}
	
}
