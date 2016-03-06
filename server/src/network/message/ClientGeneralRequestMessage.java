package network.message;



public class ClientGeneralRequestMessage extends Message{
	
	private String cltRequest;
	
	public ClientGeneralRequestMessage(final String request)
	{
		super(Message.CLT_GENERAL_REQUEST);
		this.cltRequest = request;
	}

	@Override
	public String getFormattedMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
