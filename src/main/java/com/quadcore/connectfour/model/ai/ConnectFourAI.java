package com.quadcore.connectfour.model.ai;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

public interface ConnectFourAI {

    State play(State state);
    TreeNode getMinimaxTree();
    int getNumberOfNodesExpanded();

}
