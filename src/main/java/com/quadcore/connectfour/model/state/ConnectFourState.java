package com.quadcore.connectfour.model.state;

import com.quadcore.connectfour.model.heuristics.ConnectFourHeuristic;
import com.quadcore.connectfour.model.heuristics.Heuristic;

import java.util.ArrayList;
import java.util.List;

public class ConnectFourState implements State {

    private final Heuristic heuristic = new ConnectFourHeuristic();
    private final String state;

    public ConnectFourState(String state) {
        this.state = state;
    }

    @Override
    public Double evaluate() {
        return null;
    }

    @Override
    public ArrayList<ArrayList<Character>> to2dArray() {
        return null;
    }

    @Override
    public List<State> getNeighbours() {
        return null;
    }

    @Override
    public List<Integer> getAvailableColumns() {
        return null;
    }

    @Override
    public State getNextState(Integer col) {
        return null;
    }

    private boolean isTerminal() {
        return false;
    }

    private Double getScore() {
        return 0.0;
    }

}
