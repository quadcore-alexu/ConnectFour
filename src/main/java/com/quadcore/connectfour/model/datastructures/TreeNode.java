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
=======
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class TreeNode<T> {

    //todo add parent, children, value, type, getters, constructor, setters
    private T value;
    private TreeNode<T> parent;
    private final List<TreeNode<T>> children = new ArrayList<>();
    private final String type;

    /**
     * Creates a new tree node using the passed value.
     */
    public TreeNode(String type) {
        this.type = type;
    }

    /**
     * @return the value held by this node.
     */
    public T getValue() {
        return value;
    }

    /**
     * @return a list of tree nodes that are children for this node.
     */
    public List<TreeNode<T>> getChildren() {
        return children;
    }

    /**
     * As well as adding the passed child to this node, the method adds this node as a parent for the passed child.
     *
     * @param child tree node to be added.
     */
    public void addChild(TreeNode<T> child) {
        if (child == null) throw new NullPointerException("Cannot add null child to TreeNode");
        child.parent = this;
        children.add(child);
    }

    /**
     * @return the parent node for this node. Null if this node is the root.
     */
    public TreeNode<T> getParent() {
        return parent;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void print() {
        StringBuilder buffer = new StringBuilder();
        Stack<NodeContainer> printStack = new Stack<>();
        printStack.push(new NodeContainer(this, "", ""));
        while (!printStack.isEmpty()) {
            NodeContainer container = printStack.pop();
            buffer.append(container.getPrefix());
            TreeNode<T> node = container.getNode();
            String childrenPrefix = container.getChildrenPrefix();
            if (node.type != "")
                buffer.append(node.getValue()).append(" = ").append(node.type).append("(");
            else buffer.append(node.getValue());
            boolean flag = true;
            for (Iterator<TreeNode<T>> it = node.children.iterator(); it.hasNext(); ) {
                TreeNode<T> next = it.next();
                if (!flag) {
                    printStack.push(new NodeContainer(next, childrenPrefix + "├── ", childrenPrefix + "│   "));
                    if (it.hasNext()) { buffer.append(next.getValue()).append(","); }
                    else { buffer.append(next.getValue()).append(")"); }
                } else {
                    flag = false;
                    printStack.push(new NodeContainer(next, childrenPrefix + "└── ", childrenPrefix + "    "));
                    buffer.append(next.getValue()).append(",");
                }
            }
            buffer.append('\n');
        }
        System.out.println(buffer.toString());
    }

    class NodeContainer {
        private final TreeNode<T> node;
        private final String prefix;
        private final String childrenPrefix;

        NodeContainer(TreeNode<T> node, String prefix, String childrenPrefix) {
            this.node = node;
            this.prefix = prefix;
            this.childrenPrefix = childrenPrefix;
        }

        public TreeNode<T> getNode() { return node; }

        public String getPrefix() { return prefix; }

        public String getChildrenPrefix() { return childrenPrefix; }
    }
}
