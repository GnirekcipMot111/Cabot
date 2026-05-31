package game;

import bots.Cabot;

import java.util.List;

public class BotInfo {


    private final Cabot cabot;
    private final List<Integer> hand;

    public BotInfo(Cabot cabot, List<Integer> cards) {
        this.cabot = cabot;
        this.hand = cards;
    }

    public Cabot getBot() {
        return cabot;
    }

    public List<Integer> getHand() {
        return hand;
    }
}
