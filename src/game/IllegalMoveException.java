package game;

import bots.Cabot;

public class IllegalMoveException extends RuntimeException {

    private final Cabot cabot;

    public IllegalMoveException(String message, Cabot cabot) {
        super(message);
        this.cabot = cabot;
    }
}
