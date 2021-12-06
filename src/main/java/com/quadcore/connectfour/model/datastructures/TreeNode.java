package com.quadcore.connectfour.model.datastructures;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> {

    private final T value;
    private final TreeNode<T> parent;
    private final List<TreeNode<T>> children = new LinkedList<>();

    public TreeNode(T value, TreeNode<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public T getValue() {
        return value;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

}
