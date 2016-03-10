package network.message.client;

import cards.CardType;
import network.message.Message;

//Card name can be the name of the card that a player select or the selected deck



	public class SelectedCardMessage extends Message {
		public static final String DOOR_DECK = "DOOR_DECK";
		public static final String TREASURE_DECK = "TREASURE_DECK";

		private String cardName;
		private CardType type;
	
		public SelectedCardMessage(String cardName,CardType type) {
			super(Message.CLT_CARD_SELECTED);
			this.cardName = cardName;
			this.type=type;
		}
	
		public String getCardName() {
			return cardName;
		}
		
		public CardType getType(){
			return type;
		}
/*
		@Override
		public String getFormattedMessage() {
			StringBuilder builder = new StringBuilder();
			builder.append(messageCode);
			builder.append(Message.ARG_SEPARATOR);
			builder.append(cardName);
			builder.append(Message.MSG_TERMINATOR);
			return builder.toString();
		}*/

}
