package com.quadcore.connectfour.model.datastructures;

import org.junit.jupiter.api.Test;

public class TreeNodeTest {
    @Test
    void printTree() {
        TreeNode n1 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n2 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n3 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n4 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n5 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n6 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n7 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n8 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n9 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n10 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n11 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n12 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n13 = new TreeNode(null, TreeNode.Type.MAX);
        TreeNode n14 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n15 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n16 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n17 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n18 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n19 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n20 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n21 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n22 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n23 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n24 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n25 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n26 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n27 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n28 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n29 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n30 = new TreeNode(null, TreeNode.Type.MIN);
        TreeNode n31 = new TreeNode(null, TreeNode.Type.MIN);
        n1.setScore(5);
        n2.setScore(5);
        n3.setScore(2);
        n4.setScore(-9);
        n5.setScore(10);
        n6.setScore(9);
        n7.setScore(5);
        n8.setScore(4);
        n9.setScore(3);
        n10.setScore(2);
        n11.setScore(-3);
        n12.setScore(-9);
        n13.setScore(-2);
        n14.setScore(-8);
        n15.setScore(10);
        n16.setScore(9);
        n17.setScore(-3);
        n18.setScore(3);
        n19.setScore(5);
        n20.setScore(4);
        n21.setScore(3);
        n22.setScore(3);
        n23.setScore(-1);
        n24.setScore(-5);
        n25.setScore(2);
        n26.setScore(-3);
        n27.setScore(-5);
        n28.setScore(-10);
        n29.setScore(-9);
        n30.setScore(-2);
        n31.setScore(-3);


        n1.addChild(n2);
        n1.addChild(n3);
        n1.addChild(n4);

        n2.addChild(n5);
        n2.addChild(n6);
        n2.addChild(n7);

        n3.addChild(n8);
        n3.addChild(n9);
        n3.addChild(n10);

        n4.addChild(n11);
        n4.addChild(n12);
        n4.addChild(n13);

        n5.addChild(n14);
        n5.addChild(n15);

        n6.addChild(n16);
        n6.addChild(n17);

        n7.addChild(n18);
        n7.addChild(n19);

        n8.addChild(n20);
        n8.addChild(n21);

        n9.addChild(n22);
        n9.addChild(n23);

        n10.addChild(n24);
        n10.addChild(n25);

        n11.addChild(n26);
        n11.addChild(n27);

        n12.addChild(n28);
        n12.addChild(n29);

        n13.addChild(n30);
        n13.addChild(n31);

        n1.print();
    }
}
