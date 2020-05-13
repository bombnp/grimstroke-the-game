package gui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class WinGamePane {
	public WinGamePane() {
		createPreset();
	}
	public void createPreset() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Congratulations!");
		alert.setHeaderText(null);
		alert.setContentText("There're no more waves. You win!");
		ButtonType NewGame = new ButtonType("New Game");
		ButtonType ExitGame = new ButtonType("Close Game");
		alert.getButtonTypes().setAll(NewGame,ExitGame);
		Platform.runLater(() -> alert.showAndWait().ifPresent(response -> {
			if (response == NewGame) {
				System.out.println("New Game");
			} else if (response == ExitGame) {
				System.exit(0);
			}
		}));
	}
}
