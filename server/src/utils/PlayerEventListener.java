package utils;

import game.Player;
import network.ClientConnection;
import network.message.Message;

public interface PlayerEventListener {
	public void chatMessage(Message message);
	public void lobbyStatusChanged(Player player);
	public void playerConnected(String username, ClientConnection connection);
	public void playerDisconnected(Player player);
}
