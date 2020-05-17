package gui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import logic.GameController;

/**
 * The WinGamePane represents the pane when the player wins.
 */
public class WinGamePane {
	/**
	 * The constructor of the WinGamePane. Initializes the pane, sets the buttons, and the listeners.
	 */
	public WinGamePane() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Congratulations!");
		alert.setHeaderText(null);
		alert.setContentText("There're no more waves. You win!");
		ButtonType NewGame = new ButtonType("New Game");
		ButtonType ExitGame = new ButtonType("Close Game");
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
