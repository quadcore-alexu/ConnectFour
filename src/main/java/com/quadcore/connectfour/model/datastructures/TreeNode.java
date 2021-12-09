package com.quadcore.connectfour.model.datastructures;

import com.quadcore.connectfour.model.state.State;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    public enum Type {
        MAX,
        MIN
    }

    private final State state;
    private final List<TreeNode> children = new LinkedList<>();
    private final Type type;
    private double score;

    public TreeNode(State state, Type type) {
        this.state = state;
        this.type = type;
    }

    public State getState() {
        return state;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        if (child == null) throw new NullPointerException("Cannot add null child to TreeNode");
        children.add(child);
    }

    public Type getType() {
        return type;
    }

    public double getScore() {
        return (double) Math.round(score * 100) / 100;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void print() {
        StringBuilder buffer = new StringBuilder();
        Deque<NodeContainer> printStack = new LinkedList<>();
        printStack.push(new NodeContainer(this, "", ""));
        while (!printStack.isEmpty()) {
            NodeContainer container = printStack.pop();
            buffer.append(container.getPrefix());
            TreeNode node = container.getNode();
            String childrenPrefix = container.getChildrenPrefix();
            if (!node.getChildren().isEmpty())
                buffer.append(node.getScore()).append(" = ").append(node.type).append("(");
            else buffer.append(node.getScore());
            boolean flag = true;
            for (Iterator<TreeNode> it = node.children.iterator(); it.hasNext(); ) {
                TreeNode next = it.next();
                if (!flag) {
                    printStack.push(new NodeContainer(next, childrenPrefix + "├── ", childrenPrefix + "│   "));
                    if (it.hasNext()) {
                        buffer.append(next.getScore()).append(",");
                    } else {
                        buffer.append(next.getScore()).append(")");
                    }
                } else {
                    flag = false;
                    printStack.push(new NodeContainer(next, childrenPrefix + "└── ", childrenPrefix + "    "));
                    if (it.hasNext()) {
                        buffer.append(next.getScore()).append(",");
                    } else {
                        buffer.append(next.getScore()).append(")");
                    }
                }
            }
            buffer.append('\n');
            System.out.print(buffer);
            buffer.setLength(0);
        }
    }

    static class NodeContainer {
        private final TreeNode node;
        private final String prefix;
        private final String childrenPrefix;

        NodeContainer(TreeNode node, String prefix, String childrenPrefix) {
            this.node = node;
            this.prefix = prefix;
            this.childrenPrefix = childrenPrefix;
        }

        public TreeNode getNode() {
            return node;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getChildrenPrefix() {
            return childrenPrefix;
        }
    }
}
