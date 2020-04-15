package gui;

import application.Utility;
import entity.base.Building;
import exception.SpriteIndexOutOfBoundsException;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import logic.Coordinate;

public class BoardCell extends StackPane {

    private Building building;
    private boolean buildable;
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
    public void setbuildable(boolean bool) {
    	this.buildable = bool;
    }
    public boolean getbuildable() {
    	return this.buildable;
    }

    public void setBuilding(Building newBuilding) {
        if (building != null) {
            this.getChildren().remove(building);
        }
        building = newBuilding;
        setbuildable(true);
        this.getChildren().add(building);
    }
    public void SetCellBG(Background BG) {
    	building.setBackground(BG);
    }
}
