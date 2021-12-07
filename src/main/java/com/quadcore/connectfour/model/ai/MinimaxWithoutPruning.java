package com.quadcore.connectfour.model.ai;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

import java.util.Objects;

public class MinimaxWithoutPruning implements ConnectFourAI {

    private final int maxDepth;
    private TreeNode root = null;
    private int numberOfNodesExpanded = 0;


    public MinimaxWithoutPruning(int maxDepth) {
        if (maxDepth < 1) throw new IllegalArgumentException("Minimax depth can't be less than one");
        this.maxDepth = maxDepth;
    }

    @Override
    public State play(State state) {
        if (state == null) throw new NullPointerException("State is null");
        if (state.isTerminal()) throw new IllegalArgumentException("Can't play on a terminal state");
        numberOfNodesExpanded = 1;
        root = new TreeNode(state, null, TreeNode.Type.MAX);
        TreeNode optimalMove = null;

        double maxScore = Double.MIN_VALUE;

        for (State s : state.getNeighbours()) {
            TreeNode stateTreeNode = new TreeNode(s, root, TreeNode.Type.MIN);
            root.addChild(stateTreeNode);
            double childVal = minimize(maxDepth - 1, stateTreeNode);
            if (childVal > maxScore) {
                maxScore = childVal;
                optimalMove = stateTreeNode;
            }
        }
        root.setScore(maxScore);
        return Objects.requireNonNull(optimalMove).getState();
    }

    private double maximize(int depth, TreeNode node) {
        numberOfNodesExpanded++;
        if (depth == 0 || node.getState().isTerminal())
            return node.getState().evaluateHeuristic();

        double max = Double.MIN_VALUE;
        for (State s : node.getState().getNeighbours()) {
            TreeNode neighbourTreeNode = new TreeNode(s, node, TreeNode.Type.MIN);
            node.addChild(neighbourTreeNode);
            max = Math.max(max, this.minimize(depth - 1, neighbourTreeNode));
        }
        node.setScore(max);
        return max;
    }

    private double minimize(int depth, TreeNode node) {
        numberOfNodesExpanded++;
        if (depth == 0 || node.getState().isTerminal())
            return node.getState().evaluateHeuristic();

        double min = Double.MAX_VALUE;
        for (State s : node.getState().getNeighbours()) {
            TreeNode neighbourTreeNode = new TreeNode(s, node, TreeNode.Type.MAX);
            node.addChild(neighbourTreeNode);
            min = Math.max(min, this.maximize(depth - 1, neighbourTreeNode));
        }
        node.setScore(min);
        return min;
    }

    @Override
    public TreeNode getMinimaxTree() {
        if (root == null) throw new NullPointerException("Can't get minimax tree before making a play");
        return root;
    }

    @Override
    public int getNumberOfNodesExpanded() {
        return numberOfNodesExpanded;
    }
}
