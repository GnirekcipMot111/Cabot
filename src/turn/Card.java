package turn;

import java.util.Arrays;
import java.util.List;

public record Card(List<Integer> positions) {

    public Card(Integer... positions) {
        this(Arrays.asList(positions));
    }
}
