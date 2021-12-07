package com.quadcore.connectfour.model.state;

import java.util.List;

public interface State {

    char PLAYER = 'P';
    char AI = 'C';
    char EMPTY = '0';

    double evaluate();

    char[][] to2dArray();

    List<State> getNeighbours();

    State getNextState(int col, char player);

    List<Integer> getAvailableColumns();

    boolean isTerminal();

    String getString();

}
