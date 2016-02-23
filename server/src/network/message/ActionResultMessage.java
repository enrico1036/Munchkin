package network.message;

import java.util.List;
import java.util.regex.Pattern;

public final class ActionResultMessage extends Message{
	private final String result;
	
	public ActionResultMessage(String result) {
		super(Message.SRV_ACTION_RESULT);
		this.result = result;
	}
	
	public boolean isAllowed(){
		return result.equals(Message.ACTION_ALLOWED);
	}
	
	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(result);
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}
	
}
