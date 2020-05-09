package entity.building.base;

import gui.BoardCell;
import gui.CellImage;
import javafx.scene.layout.StackPane;
import logic.Vector2;

public abstract class Building extends StackPane{
    private BoardCell cell;
    private CellImage baseImage;

    private final Vector2 centerPosition;
    public Building(BoardCell cell, int baseSprite) {
        this.cell = cell;
        baseImage = new CellImage(baseSprite);
        this.centerPosition = new Vector2(cell.getCol()*48 + 24, cell.getRow()*48 + 24);
        this.setLayoutX(centerPosition.getX()-24);
        this.setLayoutY(centerPosition.getY()-24);
        this.getChildren().add(baseImage);
    }

    public Vector2 getCenterPosition() {
        return centerPosition;
    }

}
