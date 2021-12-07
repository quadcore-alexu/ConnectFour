package com.quadcore.connectfour.view;

import com.quadcore.connectfour.model.datastructures.TreeNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ResourceBundle;

public class TreeController implements Initializable {
        private int[] levels;
        private int pivotalX;
        private AnchorPane treePane;
        protected TreeNode root;
        // TODO: max level needs adjustment
        private final int MAX_LEVEL = 5;

        @FXML
        private ScrollPane scrollPane;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            pivotalX = (int) UIConstants.MEDIAN_LENGTH;
            treePane = new AnchorPane();
            scrollPane.setContent(treePane);
            scrollPane.setPannable(true);
        }

        /**
         * Called after showing stage to adjust scroll pane size
         *
         * @param stage current stage
         */
        public void setStage(Stage stage) {
            scrollPane.setPrefHeight(stage.getHeight() - 50);
            scrollPane.setPrefWidth(stage.getWidth() - 50);
        }

        public void initializeRoot(TreeNode root, int maxDepth) {
            this.root = root;
            this.levels = new int[maxDepth + 1];
            buildSearchTree(root);
        }

        /**
         * Traverses the given tree and renders each of its nodes
         * Drawing lines between parent node and its children
         *
         * @param root current node to be rendered
         */
        public void buildSearchTree(TreeNode root) {
            double medianLength = UIConstants.MEDIAN_LENGTH;
            Queue<GraphicTreeNode> queue = new ArrayDeque<>();
            queue.add(new GraphicTreeNode(pivotalX, 0, root, 0, 0));
            while (!queue.isEmpty()) {
                GraphicTreeNode node = queue.remove();
                int nodeY = node.getParentY() + (int) medianLength * 2;
                int slot = Math.max(node.getParentSlot(), levels[node.getLevel()]);
                int nodeX = pivotalX + (int) (medianLength * 2 + 10) * slot;
                levels[node.getLevel()] = slot + 1;
                Line line = new Line(nodeX, nodeY - 0.7 * medianLength,
                                     node.getParentX(), node.getParentY() + 0.3 * medianLength);
                if (node.getLevel() > 0)
                    treePane.getChildren().add(line);
                drawState(nodeX, nodeY, node.getNode().getScore(), node.getNode().getType());

                for (int i = 0; i < node.getChildren().size(); i++) {
                    TreeNode next = node.getChildren().get(i);
                    if (node.getLevel() <= MAX_LEVEL)
                        queue.add(new GraphicTreeNode(nodeX, nodeY, next, node.getLevel() + 1, slot));
                }
            }
        }

        // TODO: remove this function
        /*public TreeNode buildDummyTree() {
            TreeNode n1 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n2 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n3 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n4 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n5 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n6 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n7 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n8 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n9 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n10 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n11 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n12 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n13 = new TreeNode(null, null, TreeNode.Type.MAX);
            TreeNode n14 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n15 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n16 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n17 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n18 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n19 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n20 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n21 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n22 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n23 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n24 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n25 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n26 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n27 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n28 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n29 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n30 = new TreeNode(null, null, TreeNode.Type.MIN);
            TreeNode n31 = new TreeNode(null, null, TreeNode.Type.MIN);
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
            return n1;
        }*/

        /**
         * Renders triangle representing minmax node
         * Computes its coordinates in tree view
         * And add it to treePane to be shown
         *
         * @param nodalX    triangle center X coordinate
         * @param nodalY    triangle center Y coordinate
         * @param score     node evaluation score
         * @param type      node's type MAX or MIN
         */
        private void drawState(double nodalX, double nodalY, double score, TreeNode.Type type) {
            Color color = UIConstants.SILVER;
            if (type.equals(TreeNode.Type.MAX)) color = UIConstants.GOLD;
            Polygon triangle = new Polygon();
            triangle.setFill(color);
            triangle.setStroke(Color.BLACK);
            Text text = new Text(score +"");
            text.setFill(Color.BLACK);
            text.setFont(Font.font("Sitka Banner", 12));
            triangle.getPoints().setAll(trianglePoints(nodalX, nodalY, type));
            StackPane pane = new StackPane(triangle, text);
            pane.setLayoutX(nodalX-0.5*UIConstants.MEDIAN_LENGTH);
            pane.setLayoutY(nodalY-0.7*UIConstants.MEDIAN_LENGTH);
            treePane.getChildren().add(pane);
        }

        /**
         * Takes node type pointing up or down
         * Then computes its points
         */
        private Double[] trianglePoints(double nodalX, double nodalY, TreeNode.Type type) {
            if (type.equals(TreeNode.Type.MAX))
                return new Double[] { nodalX-0.5*UIConstants.MEDIAN_LENGTH, nodalY+0.3*UIConstants.MEDIAN_LENGTH,
                                      nodalX+0.5*UIConstants.MEDIAN_LENGTH, nodalY+0.3*UIConstants.MEDIAN_LENGTH,
                                      nodalX, nodalY-0.7*UIConstants.MEDIAN_LENGTH };
            else
                return new Double[] { nodalX-0.5*UIConstants.MEDIAN_LENGTH, nodalY-0.7*UIConstants.MEDIAN_LENGTH,
                                      nodalX+0.5*UIConstants.MEDIAN_LENGTH, nodalY-0.7*UIConstants.MEDIAN_LENGTH,
                                      nodalX, nodalY+0.3*UIConstants.MEDIAN_LENGTH };
        }
}
