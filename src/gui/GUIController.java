package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import logic.GameController;

public class GUIController {
    private static HBox root;

    private static ControlPane controlPane;
    private static GamePane gamePane;

    private static BoardGrid boardGrid;

    public static HBox initialize() {
        controlPane = new ControlPane();
        gamePane = new GamePane();
        boardGrid = gamePane.getBoardGrid();

        root = new HBox(controlPane, gamePane);
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        return root;
    }

    public static HBox getRoot() { return root; }

    public static ControlPane getControlPane() {
        return controlPane;
    }

    public static GamePane getGamePane() {
        return gamePane;
    }

    public static BoardGrid getBoardGrid() {
        return boardGrid;
    }
}
