package gui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import logic.GameController;

/**
 * The GameOverPane represents the pane when the player loses.
 */
public class GameOverPane extends VBox{
	/**
	 * The constructor of the GameOverPane. Initializes the pane, sets the buttons, and the listeners.
	 */
	public GameOverPane() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Game Over!");
		alert.setHeaderText("Your health point is below or equal zero");
		alert.setContentText("Choose your option:");
		ButtonType NewGame = new ButtonType("New Game");
		ButtonType ExitGame = new ButtonType("Exit Game");
		alert.getButtonTypes().setAll(NewGame,ExitGame);
		Platform.runLater(() -> alert.showAndWait().ifPresent(response -> {
			if (response == NewGame) {
				GameController.restart();
			} else if (response == ExitGame) {
				System.exit(0);
			}
		}));
	}
}
