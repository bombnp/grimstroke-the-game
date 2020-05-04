package application;

import gui.ControlPane;
import gui.GUIController;
import gui.GamePane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.GameController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
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
