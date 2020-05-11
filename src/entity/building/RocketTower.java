package entity.building;

import database.Database;
import entity.building.base.Tower;
import entity.minion.base.Minion;
import entity.particle.RocketBullet;
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

    private final double ROCKET_ALPHA = 54.462;

    enum RocketSide {
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
                setupRocket();
                break;
            case 2:
                rocketLeftImage = new CellImage(Sprite.ROCKET[1]);
                rocketLeftImage.setTranslateX(-7);
                rocketLeftImage.setTranslateY(-5);

                rocketRightImage = new CellImage(Sprite.ROCKET[1]);
                rocketRightImage.setTranslateX(7);
                rocketRightImage.setTranslateY(-5);

                this.turret.getChildren().addAll(rocketLeftImage, rocketRightImage);
                setupRocket(RocketSide.LEFT);
                setupRocket(RocketSide.RIGHT);
                break;
        }

    }

    private void setupRocket() {
        rocketBullet = new RocketBullet(this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(5, this.turret.getRotate())), this.turret.getRotate(), this.level, this.getDamage());
        rocketBulletImage.setVisible(true);
    }

    private void setupRocket(RocketSide side) {
        switch (side) {
            case LEFT:
                rocketLeft = new RocketBullet(this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(8.6023, this.turret.getRotate()-ROCKET_ALPHA)), this.turret.getRotate(), this.level, this.getDamage());
                rocketLeftImage.setVisible(true);
                break;
            case RIGHT:
                rocketRight = new RocketBullet(this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(8.6023, this.turret.getRotate()+ROCKET_ALPHA)), this.turret.getRotate(), this.level, this.getDamage());
                rocketRightImage.setVisible(true);
                break;
        }
    }

    @Override
    public void attack(Minion target) {
        switch(this.level) {
            case 1:
                rocketBullet.setTarget(target);
                rocketBullet = null;
                rocketBulletImage.setVisible(false);

                new Invoker(this::setupRocket).startIn((long) ((1.0/2)*(1/rateOfFire)*(1000)));

                break;
            case 2:
                if (rocketLeft != null) {
                    rocketLeft.setTarget(target);
                    rocketLeft = null;
                    rocketLeftImage.setVisible(false);

                    new Invoker(() -> setupRocket(RocketSide.LEFT)).startIn((long) ((7.0/5)*(1/rateOfFire)*(1000)));
                } else if (rocketRight != null) {
                    rocketRight.setTarget(target);
                    rocketRight = null;
                    rocketRightImage.setVisible(false);

                    new Invoker(() -> setupRocket(RocketSide.RIGHT)).startIn((long) ((7.0/5)*(1/rateOfFire)*(1000)));
                }
                break;
        }
    }
}
