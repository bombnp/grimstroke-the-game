package application;

import gui.GUIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.Database;
import logic.GameController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Database.initialize();

        HBox root = GUIController.initialize();
        GameController.initialize("map_1");

        primaryStage.setTitle("GrimStroke");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
