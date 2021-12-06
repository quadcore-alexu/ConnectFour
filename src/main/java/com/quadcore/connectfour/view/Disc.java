package com.quadcore.connectfour.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.quadcore.connectfour.view.UIConstants.TILE_SIZE;

public class Disc extends Circle {
    private final boolean red;
    public Disc(boolean red) {
        super(TILE_SIZE / 2, red ? Color.RED : Color.YELLOW);
        this.red = red;

        setCenterX(TILE_SIZE / 2);
        setCenterY(TILE_SIZE / 2);
    }
}
