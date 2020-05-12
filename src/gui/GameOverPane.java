package gui;

import java.util.Optional;

import javax.smartcardio.ResponseAPDU;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.layout.VBox;


public class GameOverPane extends VBox{
	public GameOverPane() {
		CreatePreset();
	}
	public void CreatePreset() {
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
