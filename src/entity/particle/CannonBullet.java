package entity.particle;

import entity.minion.Minion;
import entity.particle.base.Bullet;
import gui.Sprite;
import logic.DamageType;
import logic.Vector2;

/**
 * The CannonBullet class represents {@link Bullet Bullets} fired from {@link entity.tower.CannonTower Cannon Towers}.
 */
public class CannonBullet extends Bullet {
    /**
     * The constructor for the CannonBullet class.
     * Instantiates the bullet with {@link Bullet#Bullet(Vector2, double, Minion, int, double) Bullet()} constructor.
     * @param position The starting position of the bullet.
     * @param rotation The starting rotation of the bullet.
     * @param target The target of the bullet.
     * @param damage The damage the bullet will deal if it reaches its target.
     */
    public CannonBullet(Vector2 position, double rotation, Minion target, double damage) {
        super(position, rotation, target, Sprite.CANNON_BULLET, damage);
    }

    /**
     * The implementation of the method {@link Bullet#hit(Minion) hit} of the {@link Bullet} class.
     * It deals damage to the {@link Minion} when it reaches its target.
     * @param target The target of the bullet.
     */
    @Override
    public void hit(Minion target) {
        target.takeDamage(this.damage, DamageType.Cannon);
    }
}
