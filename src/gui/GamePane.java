package gui;

import entity.Updatable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import logic.GameController;

public class GamePane extends AnchorPane{
    private final BoardGrid boardGrid;
    
    public GamePane() {
        boardGrid = new BoardGrid();
        this.getChildren().add(boardGrid);
        createCallWaveButton();
    }
    public void createCallWaveButton() {
        Button button = new Button("NEXT");
        this.getChildren().add(button);
        button.setPrefSize(75, 50);
        this.setRightAnchor(button, 10.0);
        this.setBottomAnchor(button, 10.0);
        button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GameController.getWaveController().nextWave();
			}
		});
    }
    public BoardGrid getBoardGrid() {
        return boardGrid;
    }
}
