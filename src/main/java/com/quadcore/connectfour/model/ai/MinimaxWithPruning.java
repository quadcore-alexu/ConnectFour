package com.quadcore.connectfour.model.ai;

import com.quadcore.connectfour.model.state.State;

public class MinimaxWithPruning implements ConnectFourAI {

    private final int depth;

    public MinimaxWithPruning(int depth) {
        this.depth = depth;
    }

    @Override
    public State play(State state) {
        return null;
    }

}
