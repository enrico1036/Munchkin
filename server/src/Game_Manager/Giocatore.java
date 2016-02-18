package Game_Manager;

import java.util.ArrayList;

import cards.Card;
import cards.Race;

public class Giocatore {
	
	private String username;
	private int level;
	private Race Razza_player;
	private ArrayList <Card> carte_in_mano;
	private ArrayList <Card> carte_in_tavola;
	
	public Giocatore() {
		// TODO Auto-generated constructor stub
		carte_in_mano = new ArrayList <Card>();
		carte_in_tavola = new ArrayList <Card>();
		Razza_player = new Race();
		
	}
	
	public void dead()
	{
		
	}
	
	public void inizializza_giocatore()
	{
		
	}
	
	public void levele_up(int x)
	{
		level += x;
	}
	
	public void controllo_carte()
	{
		
	}
	
	public void pesca_carta()
	{
		
	}

}
