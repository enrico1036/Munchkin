package network.message.client;

import network.message.Message;

//Card name can be the name of the card that a player select or the selected deck



	public class SelectedCardMessage extends Message {
		public static final String DOOR_DECK = "DOOR_DECK";
		public static final String TREASURE_DECK = "TREASURE_DECK";

		private String cardName;
	
		public SelectedCardMessage(String cardName) {
			super(Message.CLT_CARD_SELECTED);
			this.cardName = cardName;
		}
	
		public String getCardName() {
			return cardName;
		}

		@Override
		public String getFormattedMessage() {
			StringBuilder builder = new StringBuilder();
			builder.append(messageCode);
			builder.append(Message.ARG_SEPARATOR);
			builder.append(cardName);
			builder.append(Message.MSG_TERMINATOR);
			return builder.toString();
		}

}
