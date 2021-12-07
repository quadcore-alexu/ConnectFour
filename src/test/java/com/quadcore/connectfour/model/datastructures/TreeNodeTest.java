package com.quadcore.connectfour.model.datastructures;

public class TreeNodeTest {
    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>("max");
        TreeNode<Integer> n2 = new TreeNode<>("min");
        TreeNode<Integer> n3 = new TreeNode<>("min");
        TreeNode<Integer> n4 = new TreeNode<>("min");
        TreeNode<Integer> n5 = new TreeNode<>("max");
        TreeNode<Integer> n6 = new TreeNode<>("max");
        TreeNode<Integer> n7 = new TreeNode<>("max");
        TreeNode<Integer> n8 = new TreeNode<>("max");
        TreeNode<Integer> n9 = new TreeNode<>("max");
        TreeNode<Integer> n10 = new TreeNode<>("max");
        TreeNode<Integer> n11 = new TreeNode<>("max");
        TreeNode<Integer> n12 = new TreeNode<>("max");
        TreeNode<Integer> n13 = new TreeNode<>("max");
        TreeNode<Integer> n14 = new TreeNode<>("");
        TreeNode<Integer> n15 = new TreeNode<>("");
        TreeNode<Integer> n16 = new TreeNode<>("");
        TreeNode<Integer> n17 = new TreeNode<>("");
        TreeNode<Integer> n18 = new TreeNode<>("");
        TreeNode<Integer> n19 = new TreeNode<>("");
        TreeNode<Integer> n20 = new TreeNode<>("");
        TreeNode<Integer> n21 = new TreeNode<>("");
        TreeNode<Integer> n22 = new TreeNode<>("");
        TreeNode<Integer> n23 = new TreeNode<>("");
        TreeNode<Integer> n24 = new TreeNode<>("");
        TreeNode<Integer> n25 = new TreeNode<>("");
        TreeNode<Integer> n26 = new TreeNode<>("");
        TreeNode<Integer> n27 = new TreeNode<>("");
        TreeNode<Integer> n28 = new TreeNode<>("");
        TreeNode<Integer> n29 = new TreeNode<>("");
        TreeNode<Integer> n30 = new TreeNode<>("");
        TreeNode<Integer> n31 = new TreeNode<>("");
        n1.setValue(5);
        n2.setValue(5);
        n3.setValue(2);
        n4.setValue(-9);
        n5.setValue(10);
        n6.setValue(9);
        n7.setValue(5);
        n8.setValue(4);
        n9.setValue(3);
        n10.setValue(2);
        n11.setValue(-3);
        n12.setValue(-9);
        n13.setValue(-2);
        n14.setValue(-8);
        n15.setValue(10);
        n16.setValue(9);
        n17.setValue(-3);
        n18.setValue(3);
        n19.setValue(5);
        n20.setValue(4);
        n21.setValue(3);
        n22.setValue(3);
        n23.setValue(-1);
        n24.setValue(-5);
        n25.setValue(2);
        n26.setValue(-3);
        n27.setValue(-5);
        n28.setValue(-10);
        n29.setValue(-9);
        n30.setValue(-2);
        n31.setValue(-3);


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
