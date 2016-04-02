package dataStructure;

public class TurnData extends SharedData {
	
	private String currentPlayer;
	private GamePhase phase;
	
	public enum GamePhase {Equip, Draw, Trading, Charity, OpenDoor, LookForTrouble, LootTheRoom, AskForHelp, InterferInCombat}

	public TurnData() {
		
	}

	/**
	 * @return the currentPlayer
	 */
	public String getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
		update();
	}

	/**
	 * @return the phase
	 */
	public GamePhase getPhase() {
		return phase;
	}

	/**
	 * @param phase the phase to set
	 */
	public void setPhase(GamePhase phase) {
		this.phase = phase;
		update();
	}
	

}
