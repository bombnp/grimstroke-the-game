package gui;

import database.Database;
import database.TowerData;
import debug.Debug;
import entity.tower.CannonTower;
import entity.tower.MachineGunTower;
import entity.tower.RocketTower;
import entity.tower.base.Tower;
import exception.InvalidTowerException;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import logic.GameController;

public class BoardCell extends StackPane {
    private Tower tower;

    private final int row, col;

    private CellImage hoverImage;

    private Tooltip upgradeTooltip;

    public BoardCell(int bgSprite, int row, int col, boolean buildable) {
        this.setPrefWidth(48);
        this.setPrefHeight(48);

        this.row = row;
        this.col = col;

        addImage(bgSprite);
        setupTooltip();

        if (buildable) {
            hoverImage = addImage(new CellImage("images/hoverImage.png"));
            hoverImage.setMouseTransparent(true);
            hoverImage.setVisible(false);

            this.setOnMouseEntered(mouseEvent -> hoverImage.setVisible(true));

            this.setOnMouseMoved(mouseEvent -> {
                TowerCell selectedTower = GameController.getSelectedTower();
                if (selectedTower != null && selectedTower.isUpgradeTool() && tower != null && tower.getLevel() == 1)
                    upgradeTooltip.show(this, mouseEvent.getScreenX() + 10, mouseEvent.getScreenY() + 10);
            });

            this.setOnMouseExited(mouseEvent -> {
                hoverImage.setVisible(false);
                upgradeTooltip.hide();
            });

            this.setOnMouseClicked(mouseEvent -> {
                try {
                	if (GameController.canBuy(GameController.getSelectedTower())) {
                        this.setTower(GameController.generateSelectedTower(this));
                    } else {
                        GamePane.playerStatusPane.InvokeInsufficientGold();
                    }
                } catch (InvalidTowerException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void setupTooltip() {
        upgradeTooltip = new Tooltip("test");
        upgradeTooltip.setFont(new Font(12));

    }

    public void setTooltipText() {
        TowerData upgradedTower;
        switch (tower.getClass().getName()) {
            case "entity.tower.MachineGunTower" :
                upgradedTower = Database.MG[1];
                break;
            case "entity.tower.RocketTower" :
                upgradedTower = Database.Rocket[1];
                break;
            case "entity.tower.CannonTower" :
                upgradedTower = Database.Cannon[1];
                break;
            default:
                throw new RuntimeException("Current tower name is correct.");
        }
        upgradeTooltip.setText(
                String.format("Upgrade to %s\n", upgradedTower.name) +
                String.format("Damage: %d-%d\n", (int)upgradedTower.minDamage, (int)upgradedTower.maxDamage) +
                String.format("Rates: %.1f shots/sec\n", upgradedTower.rate) +
                String.format("Range: %d\n", (int)upgradedTower.range) +
                String.format("Cost: %d\n", upgradedTower.cost)
        );
    }

    public void addImage(int spriteIndex) {
        this.getChildren().add(new CellImage(spriteIndex));
    }

    public CellImage addImage(CellImage cellImage) {
        this.getChildren().add(cellImage);
        return cellImage;
    }

    public void removeTower() {
        this.setOnMouseClicked(mouseEvent -> {
            try {
                if (GameController.canBuy(GameController.getSelectedTower())) {
                    this.setTower(GameController.generateSelectedTower(this));
                } else {
                    GamePane.playerStatusPane.InvokeInsufficientGold();
                }
            } catch (InvalidTowerException e) {
                e.printStackTrace();
            }
        });

        GameController.removeUpdatable(tower);
        GUIController.getGamePane().getChildren().remove(tower);
        Debug.removeTowerRange(tower);
    }

    public void setTower(Tower newTower) {
        if (tower != null) {
            removeTower();
        }

        tower = newTower;

        this.setOnMouseClicked(mouseEvent -> {
            System.out.println();
            if (GameController.getSelectedTower().isSellTool()) {
                GameController.addMoney(tower.getCost()/2);
                removeTower();
            } else if (GameController.getSelectedTower().isUpgradeTool() && tower.getLevel() == 1) {
                if (GameController.canBuy(tower, 2)) {
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
                    upgradeTooltip.hide();
                } else {
                    GamePane.playerStatusPane.InvokeInsufficientGold();
                }
            }
        });

        setTooltipText();

        GUIController.getGamePane().getChildren().add(tower);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Tower getTower() {
        return tower;
    }
}
