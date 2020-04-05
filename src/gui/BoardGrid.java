package gui;

import javafx.scene.layout.GridPane;

public class BoardGrid extends GridPane {
    private BoardCell[][] boardCells;

    public BoardGrid() {

    }

    public BoardGrid initialize(int width, int height) {
        boardCells = new BoardCell[height][width];
        return this;
    }

    public BoardCell addCell(int bgSprite, int row, int col) {
        BoardCell boardCell = new BoardCell(bgSprite);
        this.getChildren().add(boardCell);
        boardCells[row][col] = boardCell;

        BoardGrid.setColumnIndex(boardCell, col);
        BoardGrid.setRowIndex(boardCell, row);

        return boardCell;
    }

    public BoardCell getCell(int row, int col) {
        return boardCells[row][col];
    }
}
