package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import logic.GameController;

public class GamePane extends AnchorPane{
    private final BoardGrid boardGrid;
    public GamePane() {
        boardGrid = new BoardGrid();
        this.getChildren().add(boardGrid);
        createCallWaveButton();
        createEntityInfomationBox();
    }
    public void createCallWaveButton() {
        Button button = new Button("NEXT");
        this.getChildren().add(button);
        button.setPrefSize(75, 50);
        setRightAnchor(button, 10.0);
        setBottomAnchor(button, 10.0);
        button.setOnAction(arg0 -> GameController.getWaveController().generateNextWave());
    }
    public void createEntityInfomationBox() {
    	EntityInformationPane Pane = new EntityInformationPane();
    	this.getChildren().add(Pane);
    	Pane.CreateBox();
    }
    public BoardGrid getBoardGrid() {
        return boardGrid;
    }
}
