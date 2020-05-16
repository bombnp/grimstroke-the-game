package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import logic.MinionWaveController;

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
        createPlayerAndTowerPane();
    }
    public void createNextWaveButton() {
        nextWaveButton = new Button("NEXT");
        this.getChildren().add(nextWaveButton);
        nextWaveButton.setPrefSize(75, 50);
        setRightAnchor(nextWaveButton, 10.0);
        setBottomAnchor(nextWaveButton, 10.0);
        nextWaveButton.setOnAction(arg0 -> {
            MinionWaveController.generateNextWave();
            nextWaveButton.setDisable(true);
        });
    }

    public void createEntityInformationPane() {
    	informationPane = new EntityInformationPane();
    	setLeftAnchor(informationPane, 10.0);
    	setBottomAnchor(informationPane, 10.0);
    	this.getChildren().add(informationPane);
    }
    
    public void createPlayerAndTowerPane() {
    	playerStatusPane = new PlayerInformationPane();
        HBox box = new HBox(playerStatusPane, new TowerConstructionPane());
        box.setSpacing(10);
        setLeftAnchor(box, 10.0);
        setTopAnchor(box, 10.0);
        this.getChildren().add(box);
    }
    public BoardGrid getBoardGrid() {
        return boardGrid;
    }

    public Button getNextWaveButton() {
        return nextWaveButton;
    }
}
