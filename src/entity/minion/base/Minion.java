package entity.minion.base;

import application.Utility;
import entity.Updatable;
import gui.GUIController;
import javafx.scene.image.ImageView;
import logic.GameController;
import logic.MinionData;
import logic.Vector2;

import java.util.ArrayList;

public abstract class Minion extends ImageView implements Updatable {
    private final ArrayList<Vector2> path;
    private int destinationIndex;

    private Vector2 destination;
    private Vector2 currentPosition;
    
    private final String name, description;
    private final int reward;
    private final double speed, resist_MG,resist_Rocket,resist_Cannon;
    
    public Minion(MinionData minionData) {
        super(Utility.getSprite(minionData.spriteIndex));
        this.name = minionData.name;
        this.description = minionData.description;
        this.reward = minionData.reward;
        this.speed = minionData.speed;
        this.resist_MG = minionData.resist_MG;
        this.resist_Rocket = minionData.resist_Rocket;
        this.resist_Cannon = minionData.resist_Cannon;

        path = GameController.getMinionPath();
        destinationIndex = 0;
        destination = path.get(destinationIndex);

        this.setFitHeight(48);
        this.setFitWidth(48);
        // offset to make image appear at center of position
        this.setTranslateX(-24);
        this.setTranslateY(-24);

        GUIController.getGamePane().getChildren().add(this);

        GameController.getUpdatables().add(this);
        GameController.getMinions().add(this);

        currentPosition = destination;
        this.setX(currentPosition.getX());
        this.setY(currentPosition.getY());
        changeDestination();
    }
    public Vector2 getCurrentPosition() {
    	return currentPosition;
    }

    public void changeDestination() {
        destinationIndex++;
        if (destinationIndex == path.size()) {
            GameController.removeMinion(this);
        }
        else {
            destination = path.get(destinationIndex);
        }
    }

    @Override
    public void update(double deltaTime) {
        double translationDistance = this.speed * GameController.minionSpeed * deltaTime;
        double totalDistance = currentPosition.distance(destination);

        if (totalDistance <= translationDistance) {
            currentPosition = destination;
            changeDestination();
        } else {
            Vector2 direction = currentPosition.getDirectionTo(destination).normalize();
            currentPosition = currentPosition.add(direction.multiply(translationDistance));
        }

        this.setX(currentPosition.getX());
        this.setY(currentPosition.getY());
        this.lookAt(destination);
    }

    public void lookAt(Vector2 target) {
        double dx = target.getX() - currentPosition.getX();
        double dy = target.getY() - currentPosition.getY();

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getReward() {
        return reward;
    }

    public double getSpeed() {
        return speed;
    }

    public double getResist_MG() {
        return resist_MG;
    }

    public double getResist_Rocket() {
        return resist_Rocket;
    }

    public double getResist_Cannon() {
        return resist_Cannon;
    }
}
