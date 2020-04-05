package gui;

import javafx.scene.layout.AnchorPane;

public class GamePane extends AnchorPane {
    private BoardGrid boardGrid;

    public GamePane() {
        boardGrid = new BoardGrid();

        this.getChildren().addAll(boardGrid);
    }

    public BoardGrid getBoardGrid() {
        return boardGrid;
    }
}
