package com.quadcore.connectfour.model.state;

import java.util.List;

public interface State {

    interface Score {
        int getPlayerScore();
        int getAIScore();
    }

    char PLAYER = 'P';
    char AI = 'C';
    char EMPTY = '0';

    double evaluateHeuristic();

    char[][] to2dArray();

    List<State> getNeighbours(char player);

    State getNextState(int col, char player);

    List<Integer> getAvailableColumns();

    boolean isTerminal();

    String getString();

    Score getScore();

}
