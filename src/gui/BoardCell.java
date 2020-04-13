package gui;

import application.Utility;
import entity.base.Building;
import exception.SpriteIndexOutOfBoundsException;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import logic.Coordinate;

public class BoardCell extends StackPane {

    private Building building;

    public BoardCell(int bgSprite) {
        this.setPrefWidth(48);
        this.setPrefHeight(48);

        addImage(bgSprite);
    }

    public void addImage(int spriteIndex) {
        this.getChildren().add(new CellImage(spriteIndex));
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building newBuilding) {
        if (building != null) {
            this.getChildren().remove(building);
        }
        building = newBuilding;
        this.getChildren().add(building);
    }
}
