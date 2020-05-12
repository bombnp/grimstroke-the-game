package entity.tower;

import database.Database;
import entity.minion.base.Minion;
import entity.particle.RocketBullet;
import entity.tower.base.Tower;
import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;
import logic.Invoker;
import logic.Vector2;

public class RocketTower extends Tower {
    private RocketBullet rocketBullet;
    private RocketBullet rocketLeft, rocketRight;

    private CellImage rocketBulletImage;
    private CellImage rocketLeftImage, rocketRightImage;

    public enum RocketSide {
        LEFT,
        RIGHT
    }

    public RocketTower(BoardCell cell, int level) {
        super(cell, Database.Rocket[level-1], level);

        switch(this.level)
        {
            case 1:
                rocketBulletImage = new CellImage(Sprite.ROCKET[0]);
                rocketBulletImage.setTranslateY(-5);
                this.turret.getChildren().add(rocketBulletImage);
                new Invoker(this::setupRocket).startIn(1); // need to finish setting Tower of BoardCell before setting up rocket
                break;
            case 2:
                rocketLeftImage = new CellImage(Sprite.ROCKET[1]);
                rocketLeftImage.setTranslateX(-7);
                rocketLeftImage.setTranslateY(-5);

                rocketRightImage = new CellImage(Sprite.ROCKET[1]);
                rocketRightImage.setTranslateX(7);
                rocketRightImage.setTranslateY(-5);

                this.turret.getChildren().addAll(rocketLeftImage, rocketRightImage);
                new Invoker(() -> {
                    setupRocket(RocketSide.LEFT);
                    setupRocket(RocketSide.RIGHT);
                }).startIn(1); // need to finish setting Tower of BoardCell before setting up rocket
                break;
        }

    }

    public void setupRocket() {
        rocketBullet = new RocketBullet(this.level, this.getDamage());
        rocketBulletImage.setVisible(true);
    }

    public void setupRocket(RocketSide side) {
        switch (side) {
            case LEFT:
                rocketLeft = new RocketBullet(this.level, this.getDamage());
                rocketLeftImage.setVisible(true);
                break;
            case RIGHT:
                rocketRight = new RocketBullet(this.level, this.getDamage());
                rocketRightImage.setVisible(true);
                break;
        }
    }

    @Override
    public void attack(Minion target) {
        double ROCKET_ANGLE_ALPHA = 54.462;
        switch(this.level) {
            case 1:
                rocketBullet.fire(target, this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(5, this.turret.getRotate())), this.turret.getRotate());
                rocketBullet = null;
                rocketBulletImage.setVisible(false);

                new Invoker(this::setupRocket).startIn((long) ((1.0/2)*(1/rateOfFire)*(1000)));

                break;
            case 2:
                if (rocketLeft != null) {
                    rocketLeft.fire(target, this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(8.6023, this.turret.getRotate()- ROCKET_ANGLE_ALPHA)), this.turret.getRotate());
                    rocketLeft = null;
                    rocketLeftImage.setVisible(false);

                    new Invoker(() -> setupRocket(RocketSide.LEFT)).startIn((long) ((7.0/5)*(1/rateOfFire)*(1000)));
                } else if (rocketRight != null) {
                    rocketRight.fire(target, this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(8.6023, this.turret.getRotate()+ ROCKET_ANGLE_ALPHA)), this.turret.getRotate());
                    rocketRight = null;
                    rocketRightImage.setVisible(false);

                    new Invoker(() -> setupRocket(RocketSide.RIGHT)).startIn((long) ((7.0/5)*(1/rateOfFire)*(1000)));
                }
                break;
        }
    }
}
