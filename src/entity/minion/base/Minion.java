package entity.minion.base;

import database.MinionData;
import entity.Updatable;
import gui.CellImage;
import gui.GUIController;
import gui.GamePane;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import logic.DamageType;
import logic.GameController;
import logic.Vector2;

import java.util.ArrayList;


public abstract class Minion extends StackPane implements Updatable {
    private final ArrayList<Vector2> path;
    private int destinationIndex;

    private Vector2 destination;
    private Vector2 currentPosition;

    private String name;
    private int reward;
    private int penalty;
    private double maxHealth, currentHealth, speed, resist_MG,resist_Rocket,resist_Cannon;
    private boolean isFlying;

    private ProgressBar healthBar;
    private final CellImage minionImage;
    
    private DropShadow dropShadow;
    private DropShadow dropShadow2;
    public Minion(MinionData minionData) {
        extractData(minionData);

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
        
        dropShadow = new DropShadow();
        dropShadow.setColor(Color.GREEN);
        dropShadow.setHeight(20);
        dropShadow.setWidth(20);
               
        setHealthBar();
    }

    public void extractData(MinionData minionData) {
    	this.name = minionData.name;
        this.reward = minionData.reward;
        this.currentHealth = this.maxHealth = minionData.health;
        this.speed = minionData.speed;
        this.resist_MG = minionData.resist_MG;
        this.resist_Rocket = minionData.resist_Rocket;
        this.resist_Cannon = minionData.resist_Cannon;
        this.isFlying = minionData.isFlying;
        this.penalty = minionData.penalty;
    }

    public void setHealthBar() {
        healthBar = new ProgressBar(1);
        healthBar.setPrefWidth(24);
        healthBar.setPrefHeight(12);
        healthBar.setTranslateY(-24);
        healthBar.setVisible(false);
        this.getChildren().add(healthBar);
    }

    public Vector2 getCurrentPosition() {
    	return currentPosition;
    }

    public void changeDestination() {
        destinationIndex++;
        if (destinationIndex == path.size()) {
            GameController.removeUpdatable(this);
            GameController.addHp(-1*penalty);
            if(GameController.getCurrentHp() <= 0) {
            	GameController.lose();
            }
        }
        else {
            destination = path.get(destinationIndex);
        }
    }

    @Override
    public void update(double deltaTime) {
        move(deltaTime);

        this.lookAt(destination);

        this.setLayoutX(currentPosition.getX());
        this.setLayoutY(currentPosition.getY());
        if(this.equals(GamePane.informationPane.getSelectedMinion()))
        	minionImage.setEffect(dropShadow);
        else
        	minionImage.setEffect(null);
        this.setOnMouseClicked(e -> {
        	minionImage.setEffect(dropShadow);
            GamePane.informationPane.sendInfo(this,name, maxHealth, currentHealth, resist_MG,resist_Rocket,resist_Cannon);
    	});        	
    }

    public void move(double deltaTime) {
        double translationDistance = this.speed * GameController.minionSpeed * deltaTime;
        double totalDistance = Vector2.distance(currentPosition, destination);

        if (totalDistance <= translationDistance) {
            currentPosition = destination;
            changeDestination();
        } else {
            Vector2 direction = currentPosition.getDirectionTo(destination).normalize();
            currentPosition = currentPosition.add(direction.multiply(translationDistance));
        }
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

    public double distanceToDestination() {
        return Vector2.distance(currentPosition, destination);
    }

    public int getDestinationIndex() {
        return destinationIndex;
    }

    public void takeDamage(double rawDamage, DamageType damageType) {
        double damage;
        switch (damageType) {
            case MG:
                damage = rawDamage*(1-resist_MG);
                break;
            case Rocket:
                damage = rawDamage*(1-resist_Rocket);
                break;
            case Cannon:
                damage = rawDamage*(1-resist_Cannon);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + damageType);
        }
        currentHealth -= damage;
        if (currentHealth <= 0) {
            GameController.removeUpdatable(this);
            GamePane.informationPane.sendInfo(null,name, maxHealth, currentHealth, resist_MG,resist_Rocket,resist_Cannon);
            GameController.addMoney(reward);
            //System.out.println("UpdateMoney");
        }

        healthBar.setProgress(currentHealth / maxHealth);
        healthBar.setVisible(true);
    }

    public boolean isFlying() {
        return isFlying;
    }

}
