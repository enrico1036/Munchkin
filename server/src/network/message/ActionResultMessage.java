package network.message;

import java.util.List;
import java.util.regex.Pattern;

public final class ActionResultMessage extends Message{
	private final String result;
	private final String reason;
	public static final String ACTION_ALLOWED = "ACTION_ALLOWED";
	public static final String ACTION_DENIED = "ACTION_DENIED";
	
	public ActionResultMessage(String result, String reason) {
		super(Message.SRV_ACTION_RESULT);
		this.result = result;
		this.reason = reason;
	}
	
	public boolean isAllowed(){
		return result.equals(this.ACTION_ALLOWED);
	}
	
	public String getReason(){
		return reason;
	}
	
	@Override
	public String getFormattedMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageCode);
		builder.append(Message.ARG_SEPARATOR);
		builder.append(result);
		builder.append(Message.ARG_SEPARATOR);
		if(reason != null && !reason.isEmpty()){
			builder.append(reason);
		} else {
			builder.append("NULL_REASON");
		}
		builder.append(Message.MSG_TERMINATOR);
		return builder.toString();
	}
	
}
