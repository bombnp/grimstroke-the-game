package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;


public class GameOverPane extends VBox{
	public GameOverPane() {
		createPreset();
	}
	public void createPreset() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Game Over!");
		alert.setHeaderText("Your health points is below or equal zero");
		alert.setContentText("Choose your option.");
		ButtonType NewGame = new ButtonType("New Game");
		ButtonType ExitGame = new ButtonType("Exit Game");
		alert.getButtonTypes().setAll(NewGame,ExitGame);
		alert.show();
		//alert.showAndWait();
		
	}
}
