package entity.particle.base;

import entity.Updatable;
import entity.minion.base.Minion;
import gui.CellImage;
import gui.GUIController;
import logic.GameController;
import logic.Vector2;

public abstract class Bullet extends CellImage implements Updatable {
    protected Minion target;
    protected Vector2 currentPosition;
    protected final double damage;

    public Bullet(Vector2 position, double rotation, int spriteIndex, double damage) {
        super(spriteIndex);
        this.currentPosition = position;
        this.damage = damage;

        GameController.addUpdatable(this);
        GUIController.getGamePane().getChildren().add(this);

        this.setCenter();
        this.setX(position.getX());
        this.setY(position.getY());
        this.setRotate(rotation);
        this.setVisible(false);
    }

    public Bullet(Vector2 position, double rotation, Minion target, int spriteIndex, double damage) {
        super(spriteIndex);
        this.currentPosition = position;
        this.target = target;
        this.damage = damage;

        GameController.addUpdatable(this);
        GUIController.getGamePane().getChildren().add(this);

        this.setCenter();
        this.setX(position.getX());
        this.setY(position.getY());
        this.setRotate(rotation);
    }

    public void setTarget(Minion target) {
        this.target = target;
        this.setVisible(true);
    }

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

    @Override
    public void update(double deltaTime) {
        if (target == null)
            return;

        move(deltaTime, target.getCurrentPosition());

        this.lookAt(target.getCurrentPosition());

        this.setX(currentPosition.getX());
        this.setY(currentPosition.getY());
    }

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

    public abstract void hit(Minion target);
}
