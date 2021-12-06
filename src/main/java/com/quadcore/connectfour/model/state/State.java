package com.quadcore.connectfour.model.state;

import java.util.ArrayList;
import java.util.List;

public interface State {

    char PLAYER = 'P';
    char AI = 'C';
    char EMPTY = '0';

    Double evaluate();
    ArrayList<ArrayList<Character>> to2dArray();
    List<State> getNeighbours();
    State getNextState(Integer col);
    List<Integer> getAvailableColumns();

}
