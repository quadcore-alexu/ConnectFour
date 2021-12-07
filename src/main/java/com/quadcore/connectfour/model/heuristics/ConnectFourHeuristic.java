package com.quadcore.connectfour.model.heuristics;

import com.quadcore.connectfour.model.state.State;

public class ConnectFourHeuristic implements Heuristic{
    @Override
    public double evaluate(State state) {
        State.Score score = state.getScore();
        return (score.getAIScore() - score.getPlayerScore()) * 5000.0;
    }
}
