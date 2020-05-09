package gui;

import entity.building.Buildspot;
import entity.building.base.Building;
import exception.InvalidTowerException;
import javafx.scene.layout.StackPane;
import logic.GameController;

public class BoardCell extends StackPane {

    private Building building;

    private final int row, col;

    private final CellImage hoverImage;

    public BoardCell(int bgSprite, int row, int col) {
        this.setPrefWidth(48);
        this.setPrefHeight(48);

        this.row = row;
        this.col = col;

        addImage(bgSprite);
        hoverImage = addImage(new CellImage("images/hoverImage.png"));
        hoverImage.setMouseTransparent(true);
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
            GUIController.getGamePane().getChildren().remove(building);
        }
        building = newBuilding;

        building.setOnMouseEntered(mouseEvent -> hoverImage.enable());
        building.setOnMouseExited(mouseEvent -> hoverImage.disable());

        if (building instanceof Buildspot) {
            building.setOnMouseClicked(mouseEvent -> {
                try {
                    this.setBuilding(GameController.generateSelectedTower(this));
                } catch (InvalidTowerException ignored) {

                }
            });
        } else {
            building.setOnMouseClicked(mouseEvent -> {
                if (GameController.getSelectedTower().isTool()) {
                    this.setBuilding(new Buildspot(this));
                }
            });
        }

        GUIController.getGamePane().getChildren().add(building);

        // make hoverImage always on top
        reAddHoverImage();
    }

    public void reAddHoverImage() {
        this.getChildren().remove(hoverImage);
        this.getChildren().add(hoverImage);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
