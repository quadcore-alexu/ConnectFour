package com.quadcore.connectfour.view;

import com.quadcore.connectfour.model.GameFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    private Stage stage;
    private Scene scene;
    private GamePlayController gamePlayController;
    private int levelNumber;

    @FXML
    private Label errorLabel;
    @FXML
    private TextField levelTxt;

    @FXML
    protected void playButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConnectFourApplication.class.getResource("/com/quadcore/connectfour/setting-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StartController controller = fxmlLoader.getController();
        controller.setStage(stage);
        this.stage.setScene(scene);
    }

    @FXML
    protected void withPruningButtonClicked() throws IOException {
        if (validateLevelsInput()) {
            gamePlayController.setSetting(GameFactory.GameType.WITH_PRUNING, levelNumber, this.stage);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void noPruningButtonClicked() {
        if (validateLevelsInput()) {
            gamePlayController.setSetting(GameFactory.GameType.WITHOUT_PRUNING, levelNumber, this.stage);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private boolean validateLevelsInput() {
        try {
            levelNumber = Integer.parseInt(levelTxt.getText());
        } catch (Exception e) {
            errorLabel.setText("Invalid number of levels");
            errorLabel.setVisible(true);
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader fxmlLoader = new FXMLLoader(ConnectFourApplication.class.getResource("/com/quadcore/connectfour/game-play-view.fxml"));
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println("Error in loading scene");
        }
        gamePlayController= fxmlLoader.getController();
    }
}
