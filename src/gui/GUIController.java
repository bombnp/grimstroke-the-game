package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
public class GUIController {
    private static HBox root;

    private static ControlPane controlPane;
    private static GamePane gamePane;

    private static BoardGrid boardGrid;

    public static class BG {
        public static final Background BLANK = Background.EMPTY;

        public static final Background TOWER_UNSELECTED = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
        public static final Background TOWER_SELECTED = new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY));
        public static final Background TOWER_HOVER = new Background(new BackgroundFill(Color.LIMEGREEN.desaturate(), CornerRadii.EMPTY, Insets.EMPTY));

        public static final Background CELL_HOVER= new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY));
    }

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
