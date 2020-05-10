package entity.particle;

import entity.minion.base.Minion;
import entity.particle.base.Bullet;
import gui.Sprite;
import logic.Vector2;

public class RocketBullet extends Bullet {
    public RocketBullet(Vector2 currentPosition, double currentRotation, int level) {
        super(currentPosition, currentRotation, Sprite.ROCKET[level-1]);
    }

    @Override
    public void hit(Minion target) {

    }
}
