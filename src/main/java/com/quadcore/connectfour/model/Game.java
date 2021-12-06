package com.quadcore.connectfour.model;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

public interface Game {

    int ROWS = 6;
    int COLUMNS = 7;

    State playAI(State state);

    TreeNode getMinimaxTree();

    int getNumberOfNodesExpanded();

}
