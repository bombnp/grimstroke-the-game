package entity.building.base;

import database.TowerData;
import entity.Updatable;
import entity.minion.base.Minion;
import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;
import javafx.scene.layout.Pane;
import logic.GameController;
import logic.Vector2;

import java.util.ArrayList;

public abstract class Tower extends Building implements Updatable{
    protected int minDamage, maxDamage;
    protected double rateOfFire, range;
    protected Minion currentTarget;
    protected double cooldown = 0;

    protected Pane turret = new Pane();
    protected CellImage turretImage;

    public Tower(BoardCell cell, TowerData towerData) {
        super(cell, Sprite.getRandomTowerBase());
        extractData(towerData);

        turretImage = new CellImage(towerData.spriteIndex);
        this.getChildren().add(turret);
        turret.getChildren().add(turretImage);

        GameController.getUpdatables().add(this);
    }

    public void extractData(TowerData towerData) {
        this.minDamage = towerData.minDamage;
        this.maxDamage = towerData.maxDamage;
        this.rateOfFire = towerData.rateOfFire;
        this.range = towerData.range;
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

    public boolean isMinionInRange(Minion minion) {
        return Vector2.distance(this.getCenterPosition(), minion.getCurrentPosition()) <= range;
    }

    public void findNewTarget() {
        ArrayList<Minion> minions = GameController.getMinions();
        currentTarget = null;

        for (Minion minion : minions) {
            if (isMinionInRange(minion)) {
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

    public abstract void attack(Minion target);
}
