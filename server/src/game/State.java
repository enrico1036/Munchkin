package game;

public enum State {
	Game(null),
		Begin(Game),
		Turn(Game),
			Draw(Turn),
				OpenTheDoor(Draw),
				LookForTrouble(Draw),
				LootTheRoom(Draw),
			Charity(Turn),
			Trading(Turn),
			Combat(Game),
				AskForHelp(Combat),
				InterferInCombat(Combat),
		End(Game)
	;
	private State parent = null;

    private State(State parent) {
        this.parent = parent;
    }
}
