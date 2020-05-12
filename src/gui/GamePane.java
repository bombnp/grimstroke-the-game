package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import logic.GameController;

public class GamePane extends AnchorPane{
    private final BoardGrid boardGrid;
    private Button nextWaveButton;
    public static EntityInformationPane informationPane;
    public static PlayerInformationPane playerStatusPane;
    public GamePane() {
        boardGrid = new BoardGrid();
        this.getChildren().add(boardGrid);
        createNextWaveButton();
        createEntityInformationPane();
        createPlayerStatusPane();
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
    	setLeftAnchor(informationPane, 10.0);
    	setBottomAnchor(informationPane, 10.0);
    	this.getChildren().add(informationPane);
    }
    
    public void createPlayerStatusPane() {
    	playerStatusPane = new PlayerInformationPane();
    	setLeftAnchor(playerStatusPane, 10.0);
    	setTopAnchor(playerStatusPane, 10.0);
    	this.getChildren().add(playerStatusPane);
    }
    public BoardGrid getBoardGrid() {
        return boardGrid;
    }

    public Button getNextWaveButton() {
        return nextWaveButton;
    }
}
