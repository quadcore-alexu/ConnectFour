package com.quadcore.connectfour.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.quadcore.connectfour.view.UIConstants.TILE_SIZE;

public class Disc extends Circle {
    private final boolean playerTurn;
    public Disc(boolean playerTurn) {
        super(TILE_SIZE / 2, playerTurn ? UIConstants.GOLD : UIConstants.SILVER);
        this.playerTurn = playerTurn;

        setCenterX(TILE_SIZE / 2);
        setCenterY(TILE_SIZE / 2);
    }
}
