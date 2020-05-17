package entity.particle;

import entity.minion.Minion;
import entity.particle.animation.Explosion;
import entity.particle.base.Bullet;
import gui.Sprite;
import logic.DamageType;
import logic.GameController;
import logic.Vector2;

import java.util.ArrayList;

/**
 * The RocketBullet class represents the {@link Bullet} fired from {@link entity.tower.RocketTower Rocket Towers}.
 */
public class RocketBullet extends Bullet {
    /**
     * The level of the {@link entity.tower.RocketTower Rocket Tower} that fired the bullet.
     */
    private final int level;

    /**
     * The explosion size of each rocket level, compared to the original size.
     */
    private final double[] explosionScale = {1.5, 2};

    /**
     * The constructor for the RocketBullet class.
     * Instantiates the bullet with {@link Bullet#Bullet(int, double) Bullet()} constructor.
     * @param level The level of the {@link entity.tower.RocketTower Rocket Tower} that fire the bullet.
     * @param damage The damage the bullet will deal when it reaches its target.
     */
    public RocketBullet(int level, double damage) {
        super(Sprite.ROCKET[level-1], damage);
        this.level = level;
    }

    /**
     * Finds all {@link Minion Minions} in the given range and deals damage to them.
     * @param range The range for detecting the minions.
     */
    public void damageMinionsInRange(double range) {
        ArrayList<Minion> minions = GameController.getMinions();
        for (Minion minion : minions) {
            if (!minion.isFlying() && Vector2.distance(this.currentPosition, minion.getCurrentPosition()) <= range) {
                minion.takeDamage(this.damage, DamageType.Rocket);
            }
        }
    }

    /**
     * Assigns a target to the bullet and sets the position and rotation of the bullet.
     * @param target The target of the bullet.
     * @param position The starting position of the bullet.
     * @param rotation The starting rotation of the bullet.
     */
    public void fire(Minion target, Vector2 position, double rotation) {
        this.target = target;
        this.currentPosition = position;
        this.updatePosition();
        this.setRotate(rotation);
        this.setVisible(true);
    }

    /**
     * The implementation of the method {@link Bullet#hit(Minion) hit} of the {@link Bullet} class.
     * It deals damage to all {@link Minion Minions} in range and creates an {@link Explosion}
     * when it reaches its target.
     * @param target The target of the bullet.
     */
    @Override
    public void hit(Minion target) {
        new Explosion(this.currentPosition, explosionScale[level-1]);

        damageMinionsInRange(24 * explosionScale[level-1]);
    }
}
