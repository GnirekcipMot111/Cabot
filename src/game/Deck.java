package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Integer> deckPile = getDeckPile();
    private final List<Integer> discardPile = new ArrayList<>();



    public int getNextCard() {
        return deckPile.removeFirst();
    }

    public void discard(int card) {
        discardPile.add(card);
    }

    public Integer getDiscard() {
        if (discardPile.isEmpty()) return null;
        return discardPile.getLast();
    }

    public int cardsLeft() {
        return deckPile.size();
    }


    public List<Integer> getStartingHand() {
        List<Integer> hand = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            hand.add(getNextCard());
        }
        return hand;
    }



    private List<Integer> getDeckPile() {
        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i < 14; i++) {
            for (int j = 0; j < 4; j++) deck.add(i);
        }
        deck.add(0);
        deck.add(0);
        Collections.shuffle(deck);
        return deck;
    }


}
