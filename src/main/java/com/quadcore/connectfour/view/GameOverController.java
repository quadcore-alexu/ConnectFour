package com.quadcore.connectfour.view;

import com.quadcore.connectfour.model.Game;
import com.quadcore.connectfour.model.GameFactory;
import com.quadcore.connectfour.model.state.ConnectFourState;
import com.quadcore.connectfour.model.state.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GameOverController{
    private Stage stage;
    private Scene scene;
    private Game game;

    @FXML
    private AnchorPane root;
    @FXML
    private Label playerLbl;
    @FXML
    private Label computerLbl;



    @FXML
    protected void showScore(int x, int y){
        //State.Score score = game.getCurrentScore();
        playerLbl.setText(x+"");
        computerLbl.setText(y+"");
    }

    @FXML
    protected void playAgainButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConnectFourApplication.class.getResource("/com/quadcore/connectfour/setting-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StartController controller = fxmlLoader.getController();
        controller.setStage(stage);
        this.stage.setScene(scene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
