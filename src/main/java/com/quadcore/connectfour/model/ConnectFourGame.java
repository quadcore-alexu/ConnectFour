package com.quadcore.connectfour.model;

import com.quadcore.connectfour.model.ai.ConnectFourAI;
import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

public class ConnectFourGame implements Game {

    private final ConnectFourAI computer;

    public ConnectFourGame(ConnectFourAI computer) {
        this.computer = computer;
    }

    @Override
    public State getNextState(State state) {
        return null;
    }

    @Override
    public TreeNode<State> getMinimaxTree() {
        return null;
    }

    @Override
    public Integer getNumberOfNodesExpanded() {
        return null;
    }
}
