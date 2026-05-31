import bots.PickupDeckAlwaysCabot;
import bots.PickupDeckOrDiscardCabot;
import game.BotInfo;
import game.CaboGame;

public class Main {


    public static void main(String[] args) {
        CaboGame caboGame = new CaboGame(
                new PickupDeckOrDiscardCabot("Deck or Discard", true),
                new PickupDeckAlwaysCabot("always pickup 1", false),
                new PickupDeckAlwaysCabot("always pickup 2", false));

        caboGame.run();

        System.out.println(caboGame.getRounds());
        for (BotInfo botInfo : caboGame.getBotInfos()) {
            System.out.println(botInfo.getBot().getName() + ": " + botInfo.getHand());
        }
        System.out.println();
        System.out.println(caboGame.getBotInfos());
        for (var entry : caboGame.getResults().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
