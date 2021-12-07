package com.quadcore.connectfour.view;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import com.quadcore.connectfour.model.state.State;

import java.util.List;

public class GraphicTreeNode {
    private final int parentX;
    private final int parentY;
    private final TreeNode node;
    private final int level;
    private final int parentSlot;

    public GraphicTreeNode(int parentX, int parentY, TreeNode node, int level, int parentSlot) {
        this.parentX = parentX;
        this.parentY = parentY;
        this.node = node;
        this.level = level;
        this.parentSlot = parentSlot;
    }

    public int getParentX() {
        return parentX;
    }

    public int getParentY() {
        return parentY;
    }

    public TreeNode getNode() {
        return node;
    }

    public int getLevel() {
        return level;
    }

    public State getState() {
        return node.getState();
    }

    public List<TreeNode> getChildren() {
        return node.getChildren();
    }

    public int getParentSlot() {
        return parentSlot;
    }
}
