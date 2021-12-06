package com.quadcore.connectfour.view;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.quadcore.connectfour.view.UIConstants.*;

public class ConnectFourController {


    private static Pane discRoot = new Pane();
    private static boolean redMove = true;
    private static Disc[][] grid = new Disc[COLUMNS][ROWS];


    @FXML
    static Parent create() {
        Pane root = new Pane();
        root.getChildren().add(discRoot);

        Shape gridShape = makeGrid();
        root.getChildren().add(gridShape);
        root.getChildren().addAll(chooseColumn());

        return root;
    }

    @FXML
    private static Shape makeGrid() {
        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4);

                shape = Shape.subtract(shape, circle);
            }
        }
        Light.Distant light = new Light.Distant();
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        light.setElevation(35.0);
        lighting.setSurfaceScale(3.0);
        shape.setFill(Color.BLUE);
        shape.setEffect(lighting);

        return shape;
    }

    private static List<Rectangle> chooseColumn() {
        List<Rectangle> list = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(0, 0, 255, 0.1)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = x;
            rect.setOnMouseClicked(e -> insertDisc(new Disc(redMove), column));

            list.add(rect);
        }

        return list;
    }

    private static void insertDisc(Disc disc, int column) {
        int row = ROWS - 1;

        while (row >= 0) {
            if (getDisc(column, row).isEmpty())
                break;
            row--;
        }

        if (row < 0)
            return;

        grid[column][row] = disc;
        discRoot.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);
        animation.setOnFinished(e -> {
            redMove = !redMove;
        });
        animation.play();
    }

    private static Optional<Disc> getDisc(int column, int row) {
        if (column < 0 || column >= COLUMNS
                || row < 0 || row >= ROWS)
            return Optional.empty();

        return Optional.ofNullable(grid[column][row]);
    }


}
