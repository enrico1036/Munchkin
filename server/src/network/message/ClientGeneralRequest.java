package network.message;



public class ClientGeneralRequest extends Message{
	
	private String cltRequest;
	
	public ClientGeneralRequest(final String request)
	{
		super(Message.CLT_GENERAL_REQUEST);
		this.cltRequest = request;
	}

	@Override
	public String getFormattedMessage() {

		switch(cltRequest){
		
		case "DOOR_CARDS":
			break;
		case "TREASURE_CARDS":
			break;
		case "PLAYERS_LIST":
			break;
		case "FIRST_DRAW":
			break;
		
		}
		return null;
	}
}
