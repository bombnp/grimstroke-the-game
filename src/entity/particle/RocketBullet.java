package entity.particle;

import entity.minion.base.Minion;
import entity.particle.animation.Explosion;
import entity.particle.base.Bullet;
import gui.Sprite;
import logic.DamageType;
import logic.GameController;
import logic.Vector2;

import java.util.ArrayList;

public class RocketBullet extends Bullet {
    private final int level;
    private final double[] explosionScale = {1.25, 1.5};

    public RocketBullet(int level, double damage) {
        super(Sprite.ROCKET[level-1], damage);
        this.level = level;
    }

    public void damageMinionsInRange(double range) {
        ArrayList<Minion> minions = GameController.getMinions();
        for (Minion minion : minions) {
            if (!minion.isFlying() && Vector2.distance(this.currentPosition, minion.getCurrentPosition()) <= range) {
                minion.takeDamage(this.damage, DamageType.Rocket);
            }
        }
    }

    public void fire(Minion target, Vector2 position, double rotation) {
        this.target = target;
        this.currentPosition = position;
        this.updatePosition();
        this.setRotate(rotation);
        this.setVisible(true);
    }

    @Override
    public void hit(Minion target) {
        new Explosion(this.currentPosition, explosionScale[level-1]);

        damageMinionsInRange(24 * explosionScale[level-1]);
    }
}
