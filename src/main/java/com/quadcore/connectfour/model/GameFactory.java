package com.quadcore.connectfour.model;

import com.quadcore.connectfour.model.ai.ConnectFourAI;
import com.quadcore.connectfour.model.ai.MinimaxWithPruning;
import com.quadcore.connectfour.model.ai.MinimaxWithoutPruning;

public class GameFactory {

    public enum GameType {
        WITH_PRUNING,
        WITHOUT_PRUNING
    }

    private static final GameFactory instance = new GameFactory();

    private GameFactory() {
    }

    public static GameFactory getInstance() {
        return instance;
    }

    public Game createGame(GameType type, int depth) {
        if (type == null) throw new NullPointerException("Type is null");
        ConnectFourAI computer;
        switch (type) {
            case WITH_PRUNING:
                computer = new MinimaxWithPruning(depth);
                break;
            case WITHOUT_PRUNING:
                computer = new MinimaxWithoutPruning(depth);
                break;
            default:
                throw new IllegalArgumentException("Invalid game type");
        }
        return new ConnectFourGame(computer);
    }
}
