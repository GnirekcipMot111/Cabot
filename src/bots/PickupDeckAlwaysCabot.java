package bots;

import contexts.OpponentTurnContext;
import contexts.TurnContext;
import turn.Card;
import game.TurnType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PickupDeckAlwaysCabot extends Cabot {


    private final List<Integer> cards = new ArrayList<>();

    private final boolean debugInfo;


    public PickupDeckAlwaysCabot(String name, boolean debugInfo) {
        super(name);
        this.debugInfo = debugInfo;
    }


    @Override
    public void startGame(int card1, int card2) {
        cards.add(card1);
        cards.add(card2);
        cards.add(99);
        cards.add(99);
    }

    @Override
    public TurnType chooseTurnType(TurnContext turnContext) {
        return TurnType.PICKUP_DECK;
    }

    @Override
    public Card takePileCard(TurnContext turnContext, int card) {
        if (debugInfo) System.out.println("Picked up " + card + ", hand: " + cards);
        if (card < Collections.max(cards)) {
            int index = cards.indexOf(Collections.max(cards));
            cards.set(index, card);
            return new Card(index);
        }
        return new Card();
    }

    @Override
    public Card takeDiscardCard(TurnContext turnContext) {
        if (turnContext.discardCard() < Collections.max(cards)) {
            return new Card(cards.indexOf(Collections.max(cards)));
        }
        return new Card();
    }

    @Override
    public void opponentTurn(OpponentTurnContext context) {

    }





}
