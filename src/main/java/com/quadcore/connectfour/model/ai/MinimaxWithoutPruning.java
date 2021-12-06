package com.quadcore.connectfour.model.ai;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

import java.util.ArrayList;
import java.util.List;

public class MinimaxWithoutPruning implements ConnectFourAI {

    private final int maxDepth;
    private double maxScore = Double.MAX_VALUE;
    private TreeNode root = null;


    public MinimaxWithoutPruning(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public TreeNode play(State state) {
        int depth = maxDepth;
        root = new TreeNode(state, null);
        TreeNode optimalMove = null;
        for (State s : state.getNeighbours()) {
            TreeNode stateTreeNode = new TreeNode(s, root);
            double childVal = minimize(depth - 1, stateTreeNode);
            if (childVal > maxScore) {
                maxScore = childVal;
                optimalMove = stateTreeNode;
            }

        }

        return optimalMove;
    }

    private double maximize(int depth, TreeNode node) {

        if (depth == 0 || node.getState().isTerminal())
            return node.getState().evaluate();

        List<State> nextStates = node.getState().getNeighbours();
        double max = Double.MIN_VALUE;
        for (State s : nextStates) {
            TreeNode neighbourTreeNode = new TreeNode(s, node);
            max = Math.max(max, this.minimize(depth - 1, neighbourTreeNode));

        }

        return max;
    }

    private double minimize(int depth, TreeNode node) {
        if (depth == 0 || node.getState().isTerminal())
            return node.getState().evaluate();
        List<State> nextStates = node.getState().getNeighbours();
        double min = Double.MAX_VALUE;
        for (State s : nextStates) {
            TreeNode neighbourTreeNode = new TreeNode(s, node);
            min = Math.max(min, this.maximize(depth - 1, neighbourTreeNode));

        }

        return min;
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
