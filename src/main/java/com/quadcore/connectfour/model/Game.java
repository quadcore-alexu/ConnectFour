package com.quadcore.connectfour.model;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

public interface Game {

    int ROWS = 6;
    int COLUMNS = 7;

    void playPlayer(int col);

    int playAI();
    boolean isTerminalState();

    TreeNode getMinimaxTree();

    State.Score getCurrentScore();

    int getNumberOfNodesExpanded();

    double getAvgProcessingTime();

}
