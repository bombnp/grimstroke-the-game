package entity.building.base;

import gui.BoardCell;
import gui.CellImage;
import javafx.scene.layout.StackPane;
import logic.Coordinate;

public abstract class Building extends StackPane {
    private BoardCell cell;
    private CellImage baseImage;

    private Coordinate centerPosition;

    public Building(BoardCell cell, int baseSprite) {
        this.cell = cell;
        baseImage = new CellImage(baseSprite);
        this.centerPosition = new Coordinate(cell.getCol()*48 + 24, cell.getRow()*48 + 24);

        this.getChildren().add(baseImage);
    }

    public Coordinate getCenterPosition() {
        return centerPosition;
    }
}
