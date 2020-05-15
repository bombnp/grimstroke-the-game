package gui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import logic.GameController;


public class GameOverPane extends VBox{
	public GameOverPane() {
		createPreset();
	}
	public void createPreset() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Game Over!");
		alert.setHeaderText("Your health point is below or equal zero");
		alert.setContentText("Choose your option:");
//		ButtonType NewGame = new ButtonType("New Game");
		ButtonType ExitGame = new ButtonType("Exit Game");
//		alert.getButtonTypes().setAll(NewGame,ExitGame);
		alert.getButtonTypes().setAll(ExitGame);
		Platform.runLater(() -> alert.showAndWait().ifPresent(response -> {
//			if (response == NewGame) {
//				System.out.println("New Game");
//			} else if (response == ExitGame) {
//				System.exit(0);
//			}
			if (response == ExitGame) {
				System.exit(0);
			}
		}));
	}
}
