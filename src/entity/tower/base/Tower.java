package entity.tower.base;

import database.TowerData;
import debug.Debug;
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
import java.util.Random;

public abstract class Tower extends StackPane implements Updatable{
    protected double minDamage, maxDamage;
    protected double rateOfFire, range;
    protected double cooldown = 0;
    protected int cost;
    protected final Vector2 centerPosition;
    protected Minion currentTarget;

    protected Pane turret = new Pane();
    protected CellImage turretImage;

    protected int level;

    public Tower(BoardCell cell, TowerData towerData, int level) {
        extractData(towerData);

        this.centerPosition = new Vector2(cell.getCol()*48 + 24, cell.getRow()*48 + 24);
        this.level = level;

        GameController.addMoney(-1*cost);

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

        Debug.drawTowerRange(this);
    }

    public void extractData(TowerData towerData) {
        this.minDamage = towerData.minDamage;
        this.maxDamage = towerData.maxDamage;
        this.rateOfFire = towerData.rate;
        this.range = towerData.range;
        this.cost = towerData.cost;
    }

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
            cooldown = 1/rateOfFire;
        }
    }

    public boolean isMinionTargetable(Minion minion) {
        if (Vector2.distance(this.getCenterPosition(), minion.getCurrentPosition()) <= range) {
            if (minion.isFlying()) {
                return this instanceof MachineGunTower;
            }
            return true;
        }
        return false;
    }

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

    public double getDamage() {
        Random randomizer = new Random();
        return randomizer.nextDouble() * (maxDamage-minDamage) + minDamage;
    }

    public Minion getCurrentTarget() {
        return currentTarget;
    }

    public void setCurrentTarget(Minion currentTarget) {
        this.currentTarget = currentTarget;
    }

    public abstract void attack(Minion target);

    public int getLevel() {
        return level;
    }

    public double getRange() {
        return range;
    }

    public Vector2 getCenterPosition() {
        return centerPosition;
    }
}
