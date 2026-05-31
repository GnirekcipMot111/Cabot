package stats;

import bots.PickupDeckAlwaysCabot;
import bots.PickupDeckOrDiscardCabot;
import game.CaboGame;

import java.util.HashMap;
import java.util.Map;

public class StatRunner {


    public static void main(String[] args) {
        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            CaboGame game = new CaboGame(
                    new PickupDeckOrDiscardCabot("Deck or Discard", false),
                    new PickupDeckAlwaysCabot("always pickup 1", false),
                    new PickupDeckAlwaysCabot("always pickup 2", false),
                    new PickupDeckAlwaysCabot("always pickup 3", false)
            );
            for (var entry : game.getResults().entrySet()) {
                if (!results.containsKey(entry.getKey())) {
                    results.put(entry.getKey(), entry.getValue());
                } else {
                    results.put(entry.getKey(), results.get(entry.getKey()) + entry.getValue());
                }
            }
        }

        for (var entry : results.entrySet()) {
            double percentage = Double.valueOf(entry.getValue()) / results.values().stream().mapToInt(i -> i).sum();
            System.out.printf("%s: (%f) %d\n", entry.getKey(), percentage, entry.getValue());
        }
    }
}
