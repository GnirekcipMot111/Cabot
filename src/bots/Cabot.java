package bots;

import contexts.OpponentTurnContext;
import contexts.TurnContext;
import turn.Card;
import game.TurnType;

public abstract class Cabot {

    private final String name;

    protected Cabot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void startGame(int card1, int card2);

    public abstract TurnType chooseTurnType(TurnContext turnContext);
    public abstract Card takePileCard(TurnContext turnContext, int card);
    public abstract Card takeDiscardCard(TurnContext turnContext);

    public abstract void opponentTurn(OpponentTurnContext context);
}
