package turn;

import java.util.Arrays;
import java.util.List;

public enum Ability implements Turn {
    PEEK(7, 8),
    SPY(9, 10),
    SWAP(11, 12);


    private final List<Integer> cards;
    Ability(Integer... cards) {
        this.cards = Arrays.asList(cards);
    }

    public List<Integer> getCards() {
        return cards;
    }
}
