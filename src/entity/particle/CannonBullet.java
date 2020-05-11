package entity.particle;

import entity.minion.base.Minion;
import entity.particle.base.Bullet;
import gui.Sprite;
import logic.DamageType;
import logic.Vector2;

public class CannonBullet extends Bullet {
    public CannonBullet(Vector2 position, double rotation, Minion target, double damage) {
        super(position, rotation, target, Sprite.CANNON_BULLET, damage);
    }

    @Override
    public void hit(Minion target) {
        target.takeDamage(this.damage, DamageType.Cannon);
    }
}
