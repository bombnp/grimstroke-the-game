package entity.tower;

import database.Database;
import entity.minion.Minion;
import entity.particle.RocketBullet;
import entity.tower.base.Tower;
import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;
import logic.Invoker;
import logic.Vector2;

/**
 * The RocketTower class represents the Rocket Towers. They shoot {@link RocketBullet Rocket Bullets} to their targets.
 */
public class RocketTower extends Tower {
    /**
     * The {@link RocketBullet} for firing. (Lv. 1)
     */
    private RocketBullet rocketBullet;

    /**
     * The {@link RocketBullet RocketBullets} for firing. (Lv. 2)
     */
    private RocketBullet rocketLeft, rocketRight;

    /**
     * The {@link CellImage} of the rocket. (Lv. 1)
     */
    private CellImage rocketBulletImage;

    /**
     * The {@link CellImage CellImages} of the rockets. (Lv. 2)
     */
    private CellImage rocketLeftImage, rocketRightImage;

    /**
     * The enum for LEFT or RIGHT side of the rockets.
     */
    public enum RocketSide {
        LEFT,
        RIGHT
    }

    /**
     * The constructor of the RocketTower class. It sets up the rockets based on its level.
     * @param cell The cell the tower is bound to.
     * @param level The level of the tower.
     */
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

    /**
     * Sets up the rocket. This variant is called if the tower is Lv. 1
     */
    public void setupRocket() {
        rocketBullet = new RocketBullet(this.level, this.getDamage());
        rocketBulletImage.setVisible(true);
    }

    /**
     * Sets up the rocket. This variant is called if the tower is Lv. 2
     * @param side The side of the rockets to setup.
     */
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

    /**
     * The implementation of the {@link Tower#attack(Minion) attack} method of the {@link Tower} class. It fires the
     * rockets, and sets up the rocket using {@link Invoker}.
     * @param target The target of the tower.
     */
    @Override
    public void attack(Minion target) {
        double ROCKET_ANGLE_ALPHA = 54.462;
        switch(this.level) {
            case 1:
                rocketBullet.fire(target, this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(5, this.turret.getRotate())), this.turret.getRotate());
                rocketBullet = null;
                rocketBulletImage.setVisible(false);

                new Invoker(this::setupRocket).startIn((long) ((1.0/2)*(1/rate)*(1000)));

                break;
            case 2:
                if (rocketLeft != null) {
                    rocketLeft.fire(target, this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(8.6023, this.turret.getRotate()- ROCKET_ANGLE_ALPHA)), this.turret.getRotate());
                    rocketLeft = null;
                    rocketLeftImage.setVisible(false);

                    new Invoker(() -> setupRocket(RocketSide.LEFT)).startIn((long) ((7.0/5)*(1/rate)*(1000)));
                } else if (rocketRight != null) {
                    rocketRight.fire(target, this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(8.6023, this.turret.getRotate()+ ROCKET_ANGLE_ALPHA)), this.turret.getRotate());
                    rocketRight = null;
                    rocketRightImage.setVisible(false);

                    new Invoker(() -> setupRocket(RocketSide.RIGHT)).startIn((long) ((7.0/5)*(1/rate)*(1000)));
                }
                break;
        }
    }
}
