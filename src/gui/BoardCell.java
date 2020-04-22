package gui;

import entity.base.Building;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

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

    public void setBuildable(boolean bool) {
    	this.buildable = bool;
    }
    public boolean getBuildable() {
    	return this.buildable;
    }

    public void setBuilding(Building newBuilding) {
        if (building != null) {
            this.getChildren().remove(building);
        }
        building = newBuilding;
        setBuildable(true);
        this.getChildren().add(building);
    }
    public void SetCellBG(Background BG) {
    	building.setBackground(BG);
    }
}
