package gui;

import entity.building.base.Building;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

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

//        if (building instanceof Buildspot) {
//            this.setOnMouseClicked(mouseEvent -> {
//                if (!GameController.getSelectedTower().isTool()) {
//                    //BUILD
//                }
//            });
//        }

        this.getChildren().add(building);
    }
    public void setCellBG(Background BG) {
    	building.setBackground(BG);
    }
}
