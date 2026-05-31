package game;

import bots.Cabot;
import contexts.OpponentTurnContext;
import contexts.TurnContext;
import turn.Card;

import java.util.*;

public class CaboGame {

    private final List<BotInfo> botInfos = new ArrayList<>();
    private final Deck deck = new Deck();
    private Cabot calledCabo = null;

    private int rounds = 0;

    public CaboGame(Cabot... bots) {
        List<Cabot> botsList = Arrays.asList(bots);
        Collections.shuffle(botsList);
        for (Cabot bot : botsList) {
            botInfos.add(new BotInfo(bot, deck.getStartingHand()));
        }
    }


    public void run() {

        for (BotInfo botInfo : botInfos) {
            botInfo.getBot().startGame(botInfo.getHand().get(0), botInfo.getHand().get(1));
        }
        while (true) {
            rounds++;
            for (BotInfo botInfo : botInfos) {
                if (botInfo.getBot() == calledCabo) return;
                TurnContext turnContext = new TurnContext(deck.getDiscard());
                switch (botInfo.getBot().chooseTurnType(turnContext)) {
                    case TurnType.PICKUP_DECK -> {

                        int card = deck.getNextCard();

                        Card discard = botInfo.getBot().takePileCard(turnContext, card);

                        discardCard(discard, card, botInfo);

                    }
                    case TurnType.PICKUP_DISCARD -> {
                        Card discard = botInfo.getBot().takeDiscardCard(turnContext);

                        discardCard(discard, deck.getDiscard(), botInfo);

                    }
                    case TurnType.CALL_CABO -> {
                        calledCabo = botInfo.getBot();
                    }
                    default ->
                            throw new IllegalStateException("Unexpected value: " + botInfo.getBot().chooseTurnType(turnContext));
                }
                if (deck.cardsLeft() == 0) return;
                for (BotInfo otherBot : botInfos) {
                    if (otherBot == botInfo) continue;
                    otherBot.getBot().opponentTurn(new OpponentTurnContext());
                }
            }
        }
    }



    private void discardCard(Card discard, int newCard, BotInfo botInfo) {
        if (discard.positions().isEmpty()) {
            deck.discard(newCard);
        } else if (Collections.max(discard.positions()) < botInfo.getHand().size()) {
            if (discard.positions().size() == 1) {
                deck.discard(botInfo.getHand().set(discard.positions().getFirst(), newCard));
            } else {
                int number = botInfo.getHand().indexOf(discard.positions().getFirst());
                for (int i = 1; i < discard.positions().size(); i++) {
                    if (botInfo.getHand().indexOf(discard.positions().get(i)) != number) {
                        throw new IllegalMoveException("Tried to discard multiple cards that were the same", botInfo.getBot());
                    }
                }
                deck.discard(botInfo.getHand().set(discard.positions().getFirst(), newCard));
                for (int i = 1; i < discard.positions().size(); i++) {
                    deck.discard(botInfo.getHand().set(discard.positions().get(i), null));
                }
                botInfo.getHand().removeIf(Objects::isNull);
            }
        } else {
            throw new IllegalMoveException("Tried to switch a card from the deck with nothing", botInfo.getBot());
        }
    }





    public Map<String, Integer> getResults() {
        Map<String, Integer> resultMap = new HashMap<>();
        for (BotInfo botInfo : botInfos) {
            resultMap.put(botInfo.getBot().getName(), botInfo.getHand().stream().mapToInt(i -> i).sum());
        }
        return resultMap;
    }


    public List<BotInfo> getBotInfos() {
        return botInfos;
    }

    public int getRounds() {
        return rounds;
    }
}
