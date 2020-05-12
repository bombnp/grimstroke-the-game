package gui;

import debug.Debug;
import entity.tower.CannonTower;
import entity.tower.MachineGunTower;
import entity.tower.RocketTower;
import entity.tower.base.Tower;
import exception.InvalidTowerException;
import javafx.scene.layout.StackPane;
import logic.GameController;

public class BoardCell extends StackPane {

    private Tower tower;

    private final int row, col;

    private CellImage hoverImage;

    public BoardCell(int bgSprite, int row, int col, boolean buildable) {
        this.setPrefWidth(48);
        this.setPrefHeight(48);

        this.row = row;
        this.col = col;

        addImage(bgSprite);

        if (buildable) {
            hoverImage = addImage(new CellImage("images/hoverImage.png"));
            hoverImage.setMouseTransparent(true);
            hoverImage.setVisible(false);

            this.setOnMouseEntered(mouseEvent -> hoverImage.setVisible(true));
            this.setOnMouseExited(mouseEvent -> hoverImage.setVisible(false));

            this.setOnMouseClicked(mouseEvent -> {
                try {
                    this.setTower(GameController.generateSelectedTower(this));
                } catch (InvalidTowerException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void addImage(int spriteIndex) {
        this.getChildren().add(new CellImage(spriteIndex));
    }

    public CellImage addImage(CellImage cellImage) {
        this.getChildren().add(cellImage);
        return cellImage;
    }

    public void removeBuilding() {
        GameController.removeUpdatable(tower);
        GUIController.getGamePane().getChildren().remove(tower);
        Debug.removeTowerRange(tower);
    }

    public void setTower(Tower newTower) {
        if (tower != null) {
            removeBuilding();
        }

        tower = newTower;

        this.setOnMouseClicked(mouseEvent -> {
            if (GameController.getSelectedTower().getName().equals("Sell Tool")) {
                removeBuilding();
            } else if (GameController.getSelectedTower().getName().equals("Upgrade Tool") && tower.getLevel() == 1) {
                switch (tower.getClass().getName()) {
                    case "entity.tower.MachineGunTower" :
                        setTower(new MachineGunTower(this, 2));
                        break;
                    case "entity.tower.RocketTower" :
                        setTower(new RocketTower(this, 2));
                        break;
                    case "entity.tower.CannonTower" :
                        setTower(new CannonTower(this, 2));
                        break;
                }
            }
        });

        GUIController.getGamePane().getChildren().add(tower);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
