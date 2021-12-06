package com.quadcore.connectfour.model.ai;

import com.quadcore.connectfour.model.state.State;

public class MinimaxWithPruning implements ConnectFourAI {

    private final int DEPTH;

    public MinimaxWithPruning(int depth) {
        this.DEPTH = depth;
    }

    @Override
    public State play(State state) {
        return null;
    }

}
