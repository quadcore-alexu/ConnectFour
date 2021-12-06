package com.quadcore.connectfour.model.ai;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

import java.util.ArrayList;
import java.util.List;

public class MinimaxWithoutPruning implements ConnectFourAI {

    private final int maxDepth;
    private double maxScore = Double.MAX_VALUE;
    private TreeNode<State> root = null;


    public MinimaxWithoutPruning(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public TreeNode<State> play(State state) {
        int depth = maxDepth;
        root = new TreeNode<>(state, null);
        TreeNode<State> optimalMove = null;
        for (State s : state.getNeighbours()) {
            TreeNode<State> stateTreeNode = new TreeNode<>(s, root);
            double childVal = minimize(depth - 1, stateTreeNode);
            if (childVal > maxScore) {
                maxScore = childVal;
                optimalMove = stateTreeNode;
            }

        }

        return optimalMove;
    }

    private double maximize(int depth, TreeNode<State> node) {

        if (depth == 0 || node.getValue().isTerminal())
            return node.getValue().evaluate();

        List<State> nextStates = node.getValue().getNeighbours();
        double max = Double.MIN_VALUE;
        for (State s : nextStates) {
            TreeNode<State> neighbourTreeNode = new TreeNode<>(s, node);
            max = Math.max(max, this.minimize(depth - 1, neighbourTreeNode));

        }

        return max;
    }

    private double minimize(int depth, TreeNode<State> node) {
        if (depth == 0 || node.getValue().isTerminal())
            return node.getValue().evaluate();
        List<State> nextStates = node.getValue().getNeighbours();
        double min = Double.MAX_VALUE;
        List<TreeNode<State>> minLevel = new ArrayList<>();
        for (State s : nextStates) {
            TreeNode<State> neighbourTreeNode = new TreeNode<>(s, node);
            minLevel.add(neighbourTreeNode);
            min = Math.max(min, this.maximize(depth - 1, neighbourTreeNode));

        }

        return min;
    }


}
