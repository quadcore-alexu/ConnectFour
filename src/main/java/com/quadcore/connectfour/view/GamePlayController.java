package com.quadcore.connectfour.view;

import com.quadcore.connectfour.model.ConnectFourGame;
import com.quadcore.connectfour.model.Game;
import com.quadcore.connectfour.model.GameFactory;
import com.quadcore.connectfour.model.state.ConnectFourState;
import com.quadcore.connectfour.model.state.State;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.quadcore.connectfour.view.UIConstants.*;

public class GamePlayController {
    private final Pane discRoot = new Pane();
    private boolean playerTurn = true;
    private final Disc[][] grid = new Disc[COLUMNS][ROWS];
    private boolean lock = false;
    private boolean firstMove = true;
    private Game game;
    private Stage stage;


    @FXML
    private AnchorPane root;
    @FXML
    private Label playerLbl;
    @FXML
    private Label computerLbl;

    @FXML
    private void onShowTreeBtnClicked() throws IOException {
        if (firstMove)
            return;
        FXMLLoader fxmlLoader = new FXMLLoader(ConnectFourApplication.class.getResource("/com/quadcore/connectfour/tree-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage = new Stage();
        stage.setTitle("Tree");
        stage.setScene(scene);
        stage.setMaximized(true);
        TreeController controller = fxmlLoader.getController();
        stage.show();
        controller.setStage(stage);
        //TODO: get max depth for the tree
        controller.initializeRoot(game.getMinimaxTree(), 10);
    }

    @FXML
    private void onBackBtnClicked() throws IOException {
        ConnectFourApplication.StartScene(stage);
    }

    @FXML
    private Shape makeGrid() {
        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(TILE_SIZE / 2.0);
                circle.setCenterX(TILE_SIZE / 2.0);
                circle.setCenterY(TILE_SIZE / 2.0);
                circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4.0);
                circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4.0);

                shape = Shape.subtract(shape, circle);
            }
        }
        Light.Distant light = new Light.Distant();
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        light.setElevation(35.0);
        lighting.setSurfaceScale(3.0);
        shape.setFill(Color.web("#202020"));
        shape.setEffect(lighting);

        return shape;
    }

    /**
     * The function to be called on loading to set game settings
     * As game type (With pruning or not)
     * Depth limit for minmax tree used
     * Stage showing the scene
     * */
    public void setSetting(GameFactory.GameType gameType, int depth, Stage stage) {
        game = GameFactory.getInstance().createGame(gameType, depth);
        this.stage = stage;
        root.getChildren().add(discRoot);
        Shape gridShape = makeGrid();
        root.getChildren().add(gridShape);
        root.getChildren().addAll(chooseColumn());
        root.setPrefHeight((ROWS + 1) * TILE_SIZE);
        root.setPrefWidth((COLUMNS + 1) * TILE_SIZE);
        this.stage.setWidth(root.getPrefWidth()+150);
        this.stage.setHeight(root.getPrefHeight()+30);
    }

    private List<Rectangle> chooseColumn() {
        List<Rectangle> list = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4.0);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(0, 0, 0, 0.1)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = x;
            rect.setOnMouseClicked(e -> playerMove(column));

            list.add(rect);
        }

        return list;
    }

    private void playerMove(int column) {
        firstMove = false;
        if (lock)
            return;
        lock = true;
        insertDisc(new Disc(playerTurn), column);
    }

    /**
     * Insert disc in a specified column making its transition
     * */
    private void insertDisc(Disc disc, int column) {
        int row = ROWS - 1;
        while (row >= 0 && getDisc(column, row).isPresent()) {
            row--;
        }
        if (row < 0)
            return;

        grid[column][row] = disc;
        discRoot.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4.0);

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4.0);
        int finalRow = row;
        animation.setOnFinished(e -> {
            playerTurn = !playerTurn;
            if (!playerTurn) {
                // Call computer turn and get computer move
                game.playPlayer(column);
                // update score
                State.Score score = game.getCurrentScore();
                playerLbl.setText(score.getPlayerScore()+"");
                computerLbl.setText(score.getAIScore()+"");
                // Call insert disc to the computer turn
                int computerColumn = game.playAI();
                game.getMinimaxTree().print();
                insertDisc(new Disc(playerTurn), computerColumn);
            } else {
                // update score
                State.Score score = game.getCurrentScore();
                playerLbl.setText(score.getPlayerScore()+"");
                computerLbl.setText(score.getAIScore()+"");
                // release lock
                lock = false;

                if (game.isTerminalState()){
                    FXMLLoader fxmlLoader = new FXMLLoader(ConnectFourApplication.class.getResource("/com/quadcore/connectfour/game-over-view.fxml"));
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load());
                    } catch (IOException ioException) {
                        System.err.println("Error in loading scene");
                    }
                    this.stage.setScene(scene);
                    stage.setTitle("Game Over");
                    stage.setScene(scene);
                    GameOverController controller = fxmlLoader.getController();
                    controller.showScore(Integer.parseInt(playerLbl.getText()), Integer.parseInt(computerLbl.getText()));
                    controller.setStage(stage);
                    this.stage.setWidth(600);
                    this.stage.setHeight(400);

                }
            }
        });
        animation.play();
    }

    private Optional<Disc> getDisc(int column, int row) {
        if (column < 0 || column >= COLUMNS
                || row < 0 || row >= ROWS)
            return Optional.empty();

        return Optional.ofNullable(grid[column][row]);
    }
}
