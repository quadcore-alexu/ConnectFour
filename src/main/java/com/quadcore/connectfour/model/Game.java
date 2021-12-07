package com.quadcore.connectfour.model;

import com.quadcore.connectfour.model.datastructures.TreeNode;

public interface Game {

    int ROWS = 6;
    int COLUMNS = 7;

    void playPlayer(int col);

    int playAI();

    TreeNode getMinimaxTree();

    int getNumberOfNodesExpanded();

}
