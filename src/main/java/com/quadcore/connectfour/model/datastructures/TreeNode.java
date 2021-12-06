package com.quadcore.connectfour.model.datastructures;

import com.quadcore.connectfour.model.state.State;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    private final State state;
    private final TreeNode parent;
    private final List<TreeNode> children = new LinkedList<>();

    public TreeNode(State state, TreeNode parent) {
        this.state = state;
        this.parent = parent;
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

}
