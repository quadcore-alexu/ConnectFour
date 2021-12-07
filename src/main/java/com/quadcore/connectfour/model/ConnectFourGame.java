package com.quadcore.connectfour.model;

import com.quadcore.connectfour.model.ai.ConnectFourAI;
import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConnectFourGame implements Game {

    public static final Logger LOGGER = LogManager.getLogger(ConnectFourGame.class);
    private final ConnectFourAI computer;

    public ConnectFourGame(ConnectFourAI computer) {
        this.computer = computer;
    }

    @Override
    public TreeNode playAI(State state) {
        return computer.play(state);
    }

    @Override
    public TreeNode getMinimaxTree() {
        return computer.getMinimaxTree();
    }

    @Override
    public int getNumberOfNodesExpanded() {
        return computer.getNumberOfNodesExpanded();
    }
}
