package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import logic.GameController;

public class GamePane extends AnchorPane{
    private final BoardGrid boardGrid;
    private Button nextWaveButton;
    private EntityInformationPane informationPane;

    public GamePane() {
        boardGrid = new BoardGrid();
        this.getChildren().add(boardGrid);
        createNextWaveButton();
        createEntityInformationPane();
    }

    public void createNextWaveButton() {
        nextWaveButton = new Button("NEXT");
        this.getChildren().add(nextWaveButton);
        nextWaveButton.setPrefSize(75, 50);
        setRightAnchor(nextWaveButton, 10.0);
        setBottomAnchor(nextWaveButton, 10.0);
        nextWaveButton.setOnAction(arg0 -> {
            GameController.getWaveController().generateNextWave();
            nextWaveButton.setDisable(true);
        });
    }

    public void createEntityInformationPane() {
    	informationPane = new EntityInformationPane();
    	this.getChildren().add(informationPane);
    }

    public BoardGrid getBoardGrid() {
        return boardGrid;
    }

    public Button getNextWaveButton() {
        return nextWaveButton;
    }
}
