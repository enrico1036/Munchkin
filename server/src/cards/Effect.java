package cards;

import java.util.ArrayList;
import java.util.HashMap;

import com.sun.org.apache.xpath.internal.axes.WalkingIterator;

import game.Decks;
import game.GameManager;
import network.message.server.PlayCardMessage;
import network.message.server.PlayCardMessage.Action;
import utils.StateMachine;

public class Effect {
	 
	 public static void runEffect(StateMachine gamePhase, String effectID, HashMap<String, String> parameters) {
		 switch(effectID){
		 //call right method with gamePhase and needed parameters
		 case "loseLevel":
			 loseLevel(Integer.parseInt((parameters.get("number_level"))));
			 break;
		 case "drawDoor":
			 drawDoor(Integer.parseInt(parameters.get("number")), Boolean.getBoolean(parameters.get("show")));
			 break;
		 }
     }
	 
	 //???? non so se serve
//	 runEffect() {
//	 switch(effectString){
//	 call right method with needed parameters
//	 }
//	}
	 
	  //dropobject(tipeObject)
	
	  
	  //dropRace()
	  //dropClass()
	  
	  //bonus_combat(boolean choose,tesoro extra)
	  
	  //clone()
	  
	  private static void loseLevel(int number_level) {
		  GameManager.getCurrentPlayer().leveleUp(-number_level);
	  }
	  
	 
	 private static void drawDoor(int number, boolean show) {
		 for (int i = 0; i < number; i++) {
			 Card card = Decks.getDoorCard();
			 GameManager.getCurrentPlayer().draw(card);
			 if(show) {
				 GameManager.broadcastMessage(new PlayCardMessage(card, Action.SHOW));
				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 }
		}
	 }
	 //drawTreasure(int number, boolean show)
	 
	 //bonusEscape(int bonus)
	 
	 
	 //bonus/malusRace
	 
	 //bonus/malusClass
	 
	 //dead
	 
	 //dropCard(tipe card, number,boolean onlyHand)
	 
	 //dropallhand
	 
	 //mostroOcchiutoBadThings{cyborg fuggono, altri lose 2 level}
	 
	 
	 //upLevelAll() per il grande cthulu
	 
	 //uplevel(class)
	 
	 //uplevel(race)
	 
	 
	 //grande-cthulhu_brutte cose(){uplevel per ogni giocatore che non combatte}  me sa che non serve
	 
	 //EscapeAutomatic(race,treasure,levelEarned)
	 //EscapeAutomatic(class,treasure,levelEarned)
	 //EscapeAutomatic(object,treasure,levelEarned)
	 //EscapeAutomatic(object,treasure,levelEarned)
	 //EscapeAutomatic(treasure,levelEarned)  // per le carte tesoro
	 
	 //FailEscape
	 
	 //tettonaBionica(calzatura)  pure questo si potrebbe evitare visti gli escape automatic
	 
	 //dieWeighted(int resultsRoll)
	  
	 //addHand
}
