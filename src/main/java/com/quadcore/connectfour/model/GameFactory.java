package com.quadcore.connectfour.model;

import com.quadcore.connectfour.model.ai.ConnectFourAI;
import com.quadcore.connectfour.model.ai.MinimaxWithPruning;
import com.quadcore.connectfour.model.ai.MinimaxWithoutPruning;

public class GameFactory {

    public enum GameType {
        WITH_PRUNING,
        WITHOUT_PRUNING
    }

    private final GameFactory instance = new GameFactory();

    private GameFactory() {
    }

    public GameFactory getInstance() {
        return instance;
    }

    public static Game createGame(GameType type, int depth) {
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
