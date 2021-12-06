package com.quadcore.connectfour.model.ai;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

public class MinimaxWithoutPruning implements ConnectFourAI {

    private final int depth;

    public MinimaxWithoutPruning(int depth) {
        this.depth = depth;
    }

    @Override
    public State play(State state) {
        return null;
    }

    @Override
    public TreeNode getMinimaxTree() {
        return null;
    }

    @Override
    public int getNumberOfNodesExpanded() {
        return 0;
    }
}
