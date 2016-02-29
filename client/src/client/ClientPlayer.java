package client;

import java.util.ArrayList;


public class ClientPlayer {
	
	public String username;
	public int level;
	public int force;
	public ArrayList <ClientCard> table;
	public ClientCard head;
	public ClientCard hand1;
	public ClientCard hand2;
	public ClientCard body;
	public ClientCard feet;
	
	public ClientPlayer(String user){
		this.username=user;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public ArrayList<ClientCard> getTable() {
		return table;
	}
	public void setTable(ArrayList<ClientCard> table) {
		this.table = table;
	}
	public ClientCard getHead() {
		return head;
	}
	public void setHead(ClientCard head) {
		this.head = head;
	}
	public ClientCard getHand1() {
		return hand1;
	}
	public void setHand1(ClientCard hand1) {
		this.hand1 = hand1;
	}
	public ClientCard getHand2() {
		return hand2;
	}
	public void setHand2(ClientCard hand2) {
		this.hand2 = hand2;
	}
	public ClientCard getBody() {
		return body;
	}
	public void setBody(ClientCard body) {
		this.body = body;
	}
	public ClientCard getFeet() {
		return feet;
	}
	public void setFeet(ClientCard feet) {
		this.feet = feet;
	}
	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}
	
}
