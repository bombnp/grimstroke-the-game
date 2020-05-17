package gui;

import database.Database;
import database.TowerData;
import entity.tower.CannonTower;
import entity.tower.MachineGunTower;
import entity.tower.RocketTower;
import entity.tower.base.Tower;
import exception.InvalidTowerException;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import logic.GameController;

/**
 * The BoardCell class represents each individual cell in the {@link BoardGrid}. This class
 * contains all the fields and methods to interact with {@link Tower} construction and removal.
 */
public class BoardCell extends StackPane {
    /**
     * The {@link Tower} the cell is bound to.
     */
    private Tower tower;

    /**
     * The row of the cell in the {@link BoardGrid}.
     */
    private final int row;

    /**
     * The row of the cell in the {@link BoardGrid}.
     */
    private final int col;

    /**
     * The white {@link CellImage} that appears when hovered.
     */
    private CellImage hoverImage;

    /**
     * The tooltip that displays the context of Lv. 2 towers before upgrading.
     */
    private Tooltip upgradeTooltip;

    /**
     * The constructor of the BoardCell class. It sets the size of the cell and the listeners of the cell.
     * @param bgSprite The sprite used for the background of the cell.
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param buildable Indicates whether or not a {@link Tower} can be built on this cell.
     */
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
                    }
                } catch (InvalidTowerException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * Sets up the {@link #upgradeTooltip}.
     */
    public void setupTooltip() {
        upgradeTooltip = new Tooltip();
        upgradeTooltip.setFont(new Font(12));

    }

    /**
     * Sets the text of {@link #upgradeTooltip} based on the type of {@link #tower}.
     */
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
                String.format("Damage: %d\n", (int)upgradedTower.damage) +
                String.format("Rates: %.1f shots/sec\n", upgradedTower.rate) +
                String.format("Range: %d\n", (int)upgradedTower.range) +
                String.format("Cost: %d\n", upgradedTower.cost)
        );
    }

    /**
     * Adds a sprite with the given {@link Sprite spriteIndex} to the cell.
     * @param spriteIndex The sprite to be added.
     */
    public void addImage(int spriteIndex) {
        this.getChildren().add(new CellImage(spriteIndex));
    }

    /**
     * Adds the given {@link CellImage} to the cell.
     * @param cellImage The {@link CellImage} to be added.
     * @return The given {@link CellImage}.
     */
    public CellImage addImage(CellImage cellImage) {
        this.getChildren().add(cellImage);
        return cellImage;
    }

    /**
     * Removes the current {@link Tower} bound to the cell.
     */
    public void removeTower() {
        this.setOnMouseClicked(mouseEvent -> {
            try {
                if (GameController.canBuy(GameController.getSelectedTower())) {
                    this.setTower(GameController.generateSelectedTower(this));
                }
            } catch (InvalidTowerException e) {
                e.printStackTrace();
            }
        });

        GameController.removeUpdatable(tower);
        GUIController.getGamePane().getChildren().remove(tower);
    }

    /**
     * Sets the {@link Tower} of the cell.
     * @param newTower The {@link Tower} to be set on the cell.
     */
    public void setTower(Tower newTower) {
        if (tower != null) {
            removeTower();
        }

        tower = newTower;

        this.setOnMouseClicked(mouseEvent -> {
            System.out.println();
            if (GameController.getSelectedTower().isSellTool()) {
                GameController.addGold(tower.getCost()/2);
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
                }
            }
        });

        setTooltipText();

        GUIController.getGamePane().getChildren().add(tower);
    }

    /**
     * Gets the {@link #row}.
     * @return The {@link #row}.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the {@link #col}.
     * @return The {@link #col}.
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the {@link #tower}.
     * @return The {@link #tower}.
     */
    public Tower getTower() {
        return tower;
    }
}
