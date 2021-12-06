package com.quadcore.connectfour.model.datastructures;

import com.quadcore.connectfour.model.state.State;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    public enum Type {
        MAX,
        MIN
    }

    private final State state;
    private final TreeNode parent;
    private final List<TreeNode> children = new LinkedList<>();
    private final Type type;
    private double score;

    public TreeNode(State state, TreeNode parent, Type type) {
        this.state = state;
        this.parent = parent;
        this.type = type;
    }

    public State getState() {
        return state;
    }

    public TreeNode getParent() {
        return parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public Type getType() {
        return type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
