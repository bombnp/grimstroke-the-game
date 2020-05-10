package entity.building;

import database.Database;
import entity.building.base.Tower;
import entity.minion.base.Minion;
import entity.particle.RocketBullet;
import gui.BoardCell;
import logic.Vector2;

public class RocketTower extends Tower {
    private RocketBullet rocketBullet;

    public RocketTower(BoardCell cell, int level) {
        super(cell, Database.Rocket[level-1], level);
    }

    private void setupRocket() {
        rocketBullet = new RocketBullet(this.getCenterPosition().add(Vector2.fromMagnitudeAndDirection(5, this.turret.getRotate())), this.turret.getRotate(), this.level);
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

        if (cooldown == 0 && rocketBullet == null) {
            setupRocket();
        }

        if (cooldown == 0 && currentTarget != null) {
            this.attack(currentTarget);
            cooldown = 1/rateOfFire;
        }
    }

    @Override
    public void attack(Minion target) {
        rocketBullet.setTarget(target);
        rocketBullet = null;
    }
}
