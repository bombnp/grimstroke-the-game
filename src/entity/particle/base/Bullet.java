package entity.particle.base;

import entity.Updatable;
import entity.minion.Minion;
import gui.CellImage;
import gui.GUIController;
import logic.GameController;
import logic.Vector2;

/**
 * The Bullet class represents the base class of all ammunition fired from {@link entity.tower.base.Tower Towers},
 * namely {@link entity.particle.RocketBullet RocketBullet} and {@link entity.particle.CannonBullet CannonBullet}.<br>
 * <br>
 * This class also implements the {@link Updatable} interface. Every frame, the bullet calculates the movement
 * towards its target. If the bullet reaches its target, it destroys itself and deal damage to target.
 */
public abstract class Bullet extends CellImage implements Updatable {
    /**
     * The target of the bullet. A bullet may not have its target at the beginning, but is assigned later.
     */
    protected Minion target;

    /**
     * The current position of the bullet.
     */
    protected Vector2 currentPosition;

    /**
     * The damage the bullet will deal if it reaches its target.
     */
    protected final double damage;

    /**
     * The constructor for the Bullet class. This variant is called by the
     * {@link entity.particle.RocketBullet RocketBullet} class. It instantiates
     * the bullet itself, but does not assign a target.
     * @param spriteIndex The sprite that represents the bullet.
     * @param damage The damage the bullet will deal if it reaches its target.
     */
    public Bullet(int spriteIndex, double damage) {
        super(spriteIndex);
        this.damage = damage;

        GameController.addUpdatable(this);
        GUIController.getGamePane().getChildren().add(this);

        this.setCenter();
        this.setVisible(false);
        this.setMouseTransparent(true);
    }

    /**
     * The constructor for the Bullet class. This variant is called by the
     * {@link entity.particle.CannonBullet CannonBullet} class. It instantiates
     * the bullet itself, assign a target, and set its position and rotation.
     * @param position The starting position of the bullet.
     * @param rotation The starting rotation of the bullet.
     * @param target The target of the bullet.
     * @param spriteIndex The sprite that represents the bullet.
     * @param damage The damage the bullet will deal if it reaches its target.
     */
    public Bullet(Vector2 position, double rotation, Minion target, int spriteIndex, double damage) {
        super(spriteIndex);
        this.currentPosition = position;
        this.target = target;
        this.damage = damage;
        updatePosition();

        GameController.addUpdatable(this);
        GUIController.getGamePane().getChildren().add(this);

        this.setCenter();
        this.setRotate(rotation);
        this.setMouseTransparent(true);
    }

    /**
     * Sets the JavaFX position with {@link #currentPosition}.
     */
    public void updatePosition() {
        this.setX(currentPosition.getX());
        this.setY(currentPosition.getY());
    }

    /**
     * Sets the rotation so that the forward axis points to the given {@link Vector2 coordinate}.
     * @param target The target to have the forward axis point to.
     */
    public void lookAt(Vector2 target) {
        double dx = target.getX() - this.getX();
        double dy = target.getY() - this.getY();

        if (dx == 0) { // on axis Y
            if (dy > 0) { // target is below centerPosition
                this.setRotate(180);
            } else { //target is above centerPosition
                this.setRotate(0);
            }
        }
        else if (dy == 0) { // on axis X
            if (dx > 0) { // target is to the right of centerPosition
                this.setRotate(90);
            } else { // target is to the left of centerPosition
                this.setRotate(270);
            }
        }
        else { // on quadrants
            double alpha = Math.toDegrees(Math.atan(dx /dy));
            if (dy > 0) { // target is below centerPosition, quadrant 3,4
                this.setRotate(180 - alpha);
            } else { // target is above centerPosition, quadrant 1,2
                this.setRotate(360 - alpha);
            }
        }
    }

    /**
     * The implementation of the {@link Updatable#update(double) update} method of the {@link Updatable} interface.
     * This method calculates the movement to the next position as well as sets the rotation every frame.
     * @param deltaTime The time since the last frame, measured in seconds.
     */
    @Override
    public void update(double deltaTime) {
        if (target == null)
            return;

        move(deltaTime, target.getCurrentPosition());

        this.lookAt(target.getCurrentPosition());

        updatePosition();
    }

    /**
     * Calculates the movement distance and direction for this frame, then apply it on to the current position.
     * @param deltaTime The time since the last frame, measured in seconds.
     */
    public void move(double deltaTime, Vector2 destination) {
        double translationDistance = GameController.bulletSpeed * deltaTime;
        double totalDistance = Vector2.distance(currentPosition, destination);

        if (totalDistance <= translationDistance) {
            GameController.removeUpdatable(this);
            hit(target);
        } else {
            Vector2 direction = currentPosition.getDirectionTo(destination).normalize();
            currentPosition = currentPosition.add(direction.multiply(translationDistance));
        }
    }

    /**
     * Called when the bullet hits its target.
     * Different types of bullet have different implementations of this method.
     * @param target The target of the bullet.
     */
    public abstract void hit(Minion target);
}
