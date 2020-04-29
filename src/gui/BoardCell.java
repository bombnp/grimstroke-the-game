package gui;

import entity.building.Buildspot;
import entity.building.base.Building;
import exception.InvalidTowerException;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import logic.GameController;

public class BoardCell extends StackPane {

    private Building building;

    private int row, col;

    private final CellImage hoverImage;

    public BoardCell(int bgSprite, int row, int col) {
        this.setPrefWidth(48);
        this.setPrefHeight(48);

        this.row = row;
        this.col = col;

        addImage(bgSprite);
        hoverImage = addImage(new CellImage("images/hoverImage.png"));
        hoverImage.disable();
    }

    public void addImage(int spriteIndex) {
        this.getChildren().add(new CellImage(spriteIndex));
    }

    public CellImage addImage(CellImage cellImage) {
        this.getChildren().add(cellImage);
        return cellImage;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building newBuilding) {
        if (building != null) {
            this.getChildren().remove(building);
        }
        building = newBuilding;

        this.setOnMouseEntered(mouseEvent -> hoverImage.enable());
        this.setOnMouseExited(mouseEvent -> hoverImage.disable());

        if (building instanceof Buildspot) {
            this.setOnMouseClicked(mouseEvent -> {
                try {
                    this.setBuilding(GameController.generateSelectedTowerEntity(this));
                } catch (InvalidTowerException ignored) {

                }
            });
        } else {
            this.setOnMouseClicked(mouseEvent -> {
                if (GameController.getSelectedTower().isTool()) {
                    this.setBuilding(new Buildspot(this));
                }
            });
        }

        this.getChildren().add(building);

        // make hoverImage always on top
        reAddHoverImage();
    }

    public void reAddHoverImage() {
        this.getChildren().remove(hoverImage);
        this.getChildren().add(hoverImage);
    }

    public void setCellBG(Background BG) {
    	building.setBackground(BG);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
