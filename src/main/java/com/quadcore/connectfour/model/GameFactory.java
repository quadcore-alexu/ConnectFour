package com.quadcore.connectfour.model;

public class GameFactory {

    private final GameFactory instance = new GameFactory();

    private GameFactory() {
    }

    public GameFactory getInstance() {
        return instance;
    }
}
