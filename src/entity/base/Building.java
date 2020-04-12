package entity.base;

import application.Utility;
import exception.SpriteIndexOutOfBoundsException;
import gui.BoardCell;
import gui.CellImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class Building extends StackPane {
    private BoardCell cell;
    private CellImage baseImage;

    public Building(BoardCell cell, int baseSprite) {
        this.cell = cell;
        baseImage = new CellImage(baseSprite);
        this.getChildren().add(baseImage);
    }
}
