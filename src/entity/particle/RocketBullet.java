package entity.particle;

import entity.minion.base.Minion;
import entity.particle.animation.Explosion;
import entity.particle.base.Bullet;
import gui.Sprite;
import logic.Vector2;

public class RocketBullet extends Bullet {
    private final int level;
    private final double[] explosionRadius = {1.25, 1.5};

    public RocketBullet(Vector2 currentPosition, double currentRotation, int level) {
        super(currentPosition, currentRotation, Sprite.ROCKET[level-1]);
        this.level = level;
    }

    @Override
    public void hit(Minion target) {
        new Explosion(this.currentPosition, explosionRadius[level-1]);
    }
}
