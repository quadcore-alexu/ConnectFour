package com.quadcore.connectfour.model.ai;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

public class MinimaxWithPruning implements ConnectFourAI {

    private final int depth;

    public MinimaxWithPruning(int depth) {
        this.depth = depth;
    }

    @Override
    public TreeNode<State> play(State state) {
        return null;
    }

}
