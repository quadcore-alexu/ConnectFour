package com.quadcore.connectfour.view;

import com.quadcore.connectfour.model.GameFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnectFourApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StartScene(stage);
        stage.show();
        stage.setResizable(false);
    }

    public static void StartScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConnectFourApplication.class.getResource("/com/quadcore/connectfour/start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        StartController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("Connect Four");
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(400);
    }

    public static void main(String[] args) {
        launch();
    }
}
