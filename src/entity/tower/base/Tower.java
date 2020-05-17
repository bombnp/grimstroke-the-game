package entity.tower.base;

import database.TowerData;
import entity.Updatable;
import entity.minion.Minion;
import entity.tower.MachineGunTower;
import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import logic.GameController;
import logic.Vector2;

import java.util.ArrayList;

/**
 * The Tower class represents the base class for all types of Towers, namely {@link MachineGunTower Machine Gun Towers},
 * {@link entity.tower.RocketTower Rocket Towers}, and {@link entity.tower.CannonTower Cannon Towers}. It contains all
 * the necessary field and methods used by all types of Towers. when created, it extracts the given {@link TowerData}
 * and adds itself to the {@link gui.GamePane GamePane}. <br>
 * <br>
 * This class also implements the {@link Updatable} interface. Every frame, it finds a new target if it doesn't have
 * one, set the rotation accordingly, and fire if it's ready. {@link Minion Minions} that leaves its range is no longer
 * targeted, and the Tower must find a new target. <br>
 * <br>
 * Each type of Tower has its own damage, rate, range, and cost.
 */
public abstract class Tower extends StackPane implements Updatable{
    /**
     * The {@link BoardCell} this tower is bound to.
     */
    protected BoardCell cell;

    /**
     * The damage the tower can deal.
     */
    protected double damage;

    /**
     * The rate in which the tower fires, measured in shots per second.
     */
    protected double rate;

    /**
     * The range of the tower, measured in pixels.
     */
    protected double range;

    /**
     * The time until the tower can fire again.
     */
    protected double cooldown = 0;

    /**
     * The cost of the tower.
     */
    protected int cost;

    /**
     * The level of the tower.
      */
    protected int level;

    /**
     * The {@link Vector2 coordinate} of the center position of the tower.
     */
    protected final Vector2 centerPosition;

    /**
     * The current {@link Minion} the tower is targeting.
     */
    protected Minion currentTarget;

    /**
     * The group of {@link javafx.scene.Node Nodes} that represents the turret part of the tower.
     */
    protected Pane turret = new Pane();

    /**
     * The {@link CellImage} that represents the turret image of the tower.
     */
    protected CellImage turretImage;

    /**
     * The constructor for the Tower class. It instantiates the class, extracts the data from the given
     * {@link TowerData} and positions the image accordingly.
     * @param cell The cell the tower is bound to.
     * @param towerData The data of the tower.
     * @param level The level of the tower.
     */
    public Tower(BoardCell cell, TowerData towerData, int level) {
        extractData(towerData);

        this.centerPosition = new Vector2(cell.getCol()*48 + 24, cell.getRow()*48 + 24);
        this.level = level;
        this.cell = cell;

        GameController.addGold(-1*cost);

        this.setLayoutX(centerPosition.getX()-24);
        this.setLayoutY(centerPosition.getY()-24);

        this.getChildren().add(new CellImage(Sprite.getRandomTowerBase()));
        if (towerData.name.contains("Rocket Tower"))
            turretImage = new CellImage(Sprite.ROCKET_TURRET[level-1]);
        else
            turretImage = new CellImage(towerData.spriteIndex);
        turret.getChildren().add(turretImage);
        this.getChildren().add(turret);
        this.setMouseTransparent(true);

        GameController.getUpdatables().add(this);
    }

    /**
     * Extracts the data from the given {@link TowerData}
     * @param towerData The data of the tower.
     */
    public void extractData(TowerData towerData) {
        this.damage = towerData.damage;
        this.rate = towerData.rate;
        this.range = towerData.range;
        this.cost = towerData.cost;
    }

    /**
     * Sets the rotation so that the forward axis points to the given {@link Vector2 coordinate}.
     * @param target The target to have the forward axis point to.
     */
    public void lookAt(Vector2 target) {
        double dx = target.getX() - this.getCenterPosition().getX();
        double dy = target.getY() - this.getCenterPosition().getY();

        if (dx == 0) { // on axis Y
            if (dy > 0) { // target is below centerPosition
                turret.setRotate(180);
            } else { //target is above centerPosition
                turret.setRotate(0);
            }
        }
        else if (dy == 0) { // on axis X
            if (dx > 0) { // target is to the right of centerPosition
                turret.setRotate(90);
            } else { // target is to the left of centerPosition
                turret.setRotate(270);
            }
        }
        else { // on quadrants
            double alpha = Math.toDegrees(Math.atan(dx /dy));
            if (dy > 0) { // target is below centerPosition, quadrant 3,4
                turret.setRotate(180 - alpha);
            } else { // target is above centerPosition, quadrant 1,2
                turret.setRotate(360 - alpha);
            }
        }
    }

    /**
     * The implementation of the {@link Updatable#update(double) update} method of the {@link Updatable} interface.
     * This method finds a new target if the tower doesn't have one, sets the rotation, and fire if the cooldown is zero.
     * @param deltaTime The time since the last frame, measured in seconds.
     */
    @Override
    public void update(double deltaTime) {
        if (currentTarget == null || Vector2.distance(this.getCenterPosition(), currentTarget.getCurrentPosition()) > range) {
            findNewTarget();
        }

        if (currentTarget != null) {
            lookAt(currentTarget.getCurrentPosition());
        }

        cooldown -= deltaTime;
        if (cooldown < 0)
            cooldown = 0;

        if (cooldown == 0 && currentTarget != null) {
            this.attack(currentTarget);
            cooldown = 1/ rate;
        }
    }

    /**
     * Checks if the tower can target the given {@link Minion}. A tower can target a minion if it's
     * in range and, if the minion is flying, the tower must be {@link MachineGunTower Machine Gun Tower}.
     * @param minion The minion to be checked if it's targetable.
     * @return Returns true if the given {@link Minion} is targetable, false otherwise.
     */
    public boolean isMinionTargetable(Minion minion) {
        if (Vector2.distance(this.getCenterPosition(), minion.getCurrentPosition()) <= range) {
            if (minion.isFlying()) {
                return this instanceof MachineGunTower;
            }
            return true;
        }
        return false;
    }

    /**
     * Finds all targetable {@link Minion Minions} and select a new target based on the minions' progress on the path.
     */
    public void findNewTarget() {
        ArrayList<Minion> minions = GameController.getMinions();
        currentTarget = null;

        for (Minion minion : minions) {
            if (isMinionTargetable(minion)) {
                if (currentTarget == null) {
                    currentTarget = minion;
                } else if (minion.getDestinationIndex() > currentTarget.getDestinationIndex()) {
                    currentTarget = minion;
                } else if (minion.getDestinationIndex() == currentTarget.getDestinationIndex() && minion.distanceToDestination() < currentTarget.distanceToDestination()) {
                    currentTarget = minion;
                }
            }
        }
    }

    /**
     * Called when the tower attempts to attack. Different types of Towers have different implementations of the method.
     * @param target The target of the tower.
     */
    public abstract void attack(Minion target);

    /**
     * Gets the {@link #damage}.
     * @return The {@link #damage}.
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Gets the {@link #currentTarget}.
     * @return The {@link #currentTarget}.
     */
    public Minion getCurrentTarget() {
        return currentTarget;
    }

    /**
     * Sets the {@link #currentTarget}.
     * @param currentTarget The minion to be targeted.
     */
    public void setCurrentTarget(Minion currentTarget) {
        this.currentTarget = currentTarget;
    }

    /**
     * Gets the {@link #level}.
     * @return The {@link #level}.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the {@link #range}.
     * @return The {@link #range}.
     */
    public double getRange() {
        return range;
    }

    /**
     * Gets the {@link #cost}.
     * @return The {@link #cost}.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets the {@link #centerPosition}.
     * @return The {@link #centerPosition}.
     */
    public Vector2 getCenterPosition() {
        return centerPosition;
    }

    /**
     * Gets the {@link #cell}.
     * @return The {@link #cell}
     */
    public BoardCell getCell() {
        return cell;
    }
}
