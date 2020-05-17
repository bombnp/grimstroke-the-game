package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import logic.MinionWaveController;

/**
 * The GamePane class represents the entirety of the game area.
 */
public class GamePane extends AnchorPane{
    /**
     * The {@link BoardGrid} that contains all the cells.
     */
    private final BoardGrid boardGrid;

    /**
     * The {@link Button} that calls in the next wave.
     */
    private Button nextWaveButton;

    /**
     * The pane that shows information about the selected {@link entity.minion.Minion Minion}.
     */
    public static EntityInformationPane entityInformationPane;

    /**
     * The pane that shows information about the player.
     */
    public static PlayerInformationPane playerInformationPane;

    /**
     * The constructor of the GamePane class. Initializes all the elements in the GamePane.
     */
    public GamePane() {
        boardGrid = new BoardGrid();
        this.getChildren().add(boardGrid);
        createNextWaveButton();
        createEntityInformationPane();
        createPlayerAndTowerPane();
    }

    /**
     * Creates the {@link #nextWaveButton} and sets the listeners.
     */
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

    /**
     * Creates the {@link EntityInformationPane} and sets its position.
     */
    public void createEntityInformationPane() {
    	entityInformationPane = new EntityInformationPane();
    	setLeftAnchor(entityInformationPane, 10.0);
    	setBottomAnchor(entityInformationPane, 10.0);
    	this.getChildren().add(entityInformationPane);
    }

    /**
     * Creates the {@link PlayerInformationPane} and the {@link TowerConstructionPane} and sets their positions.
     */
    public void createPlayerAndTowerPane() {
    	playerInformationPane = new PlayerInformationPane();
        HBox box = new HBox(playerInformationPane, new TowerConstructionPane());
        box.setSpacing(10);
        setLeftAnchor(box, 10.0);
        setTopAnchor(box, 10.0);
        this.getChildren().add(box);
    }

    /**
     * Gets the {@link #boardGrid}.
     * @return THe {@link #boardGrid}.
     */
    public BoardGrid getBoardGrid() {
        return boardGrid;
    }

    /**
     * Gets the {@link #nextWaveButton}.
     * @return The {@link #nextWaveButton}.
     */
    public Button getNextWaveButton() {
        return nextWaveButton;
    }
}
