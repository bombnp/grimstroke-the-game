package entity.minion.base;

import entity.Updatable;
import gui.CellImage;
import gui.GUIController;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import logic.GameController;
import logic.MinionData;
import logic.Vector2;

import java.util.ArrayList;

public abstract class Minion extends StackPane implements Updatable {
    private final ArrayList<Vector2> path;
    private int destinationIndex;

    private Vector2 destination;
    private Vector2 currentPosition;

    private final int reward;
    private final double maxHealth, currentHealth, speed, resist_MG,resist_Rocket,resist_Cannon;

    private ProgressBar healthBar;
    private final CellImage minionImage;

    public Minion(MinionData minionData) {
        this.reward = minionData.reward;
        this.currentHealth = this.maxHealth = minionData.health;
        this.speed = minionData.speed;
        this.resist_MG = minionData.resist_MG;
        this.resist_Rocket = minionData.resist_Rocket;
        this.resist_Cannon = minionData.resist_Cannon;

        // offset to make image appear at center of position
        this.setTranslateX(-24);
        this.setTranslateY(-24);

        GUIController.getGamePane().getChildren().add(this);
        GameController.getUpdatables().add(this);
        GameController.getMinions().add(this);

        path = GameController.getMinionPath();
        destinationIndex = 0;
        destination = path.get(destinationIndex);
        currentPosition = destination;
        this.setLayoutX(currentPosition.getX());
        this.setLayoutY(currentPosition.getY());
        changeDestination();

        minionImage = new CellImage(minionData.spriteIndex);
        this.getChildren().add(minionImage);
        setHealthBar();
    }

    public void setHealthBar() {
        healthBar = new ProgressBar();
        healthBar.setPrefWidth(48);
        healthBar.setPrefHeight(12);
        healthBar.setTranslateY(-24);
        this.getChildren().add(healthBar);
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

        healthBar.setProgress(currentHealth / maxHealth);

        this.lookAt(destination);

        this.setLayoutX(currentPosition.getX());
        this.setLayoutY(currentPosition.getY());
    }

    public void lookAt(Vector2 target) {
        double dx = target.getX() - currentPosition.getX();
        double dy = target.getY() - currentPosition.getY();

        if (dx == 0) { // on axis Y
            if (dy > 0) { // target is below centerPosition
                minionImage.setRotate(180);
            } else { //target is above centerPosition
                minionImage.setRotate(0);
            }
        }
        else if (dy == 0) { // on axis X
            if (dx > 0) { // target is to the right of centerPosition
                minionImage.setRotate(90);
            } else { // target is to the left of centerPosition
                minionImage.setRotate(270);
            }
        }
        else { // on quadrants
            double alpha = Math.toDegrees(Math.atan(dx /dy));
            if (dy > 0) { // target is below centerPosition, quadrant 3,4
                minionImage.setRotate(180 - alpha);
            } else { // target is above centerPosition, quadrant 1,2
                minionImage.setRotate(360 - alpha);
            }
        }
    }

}
