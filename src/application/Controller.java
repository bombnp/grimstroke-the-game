package application;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class Controller {
    public StackPane root;
    public Button quitButton;

    public void quit(ActionEvent actionEvent) {
        Main.quit();
    }


    public void handleKeyPress(KeyEvent keyEvent) {
    }
}
