package entity.minion;

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

/**
 * The Minion class represents individual minion instances. Once a Minion is instantiated, it extracts
 * the data from the given {@link MinionData}. It then adds itself to the {@link GamePane}. <br>
 * <br>
 * This class also implements the {@link Updatable} interface. Each frame, the Minion calculates the
 * distance and direction it should move in that frame. If it reaches its current destination, it
 * switches to the next destination indicated by the {@link #path}. If it reaches the last destinations,
 * it is removed from the game and deal damage to the player's health. If it dies before it reaches the
 * destination, it increases the gold count of the player. <br>
 * <br>
 * Each minion has its own health and resistances and whether or not it is flying. Its resistances are
 * also different for each {@link entity.tower.base.Tower Tower} type. Flying minions only take damage
 * from {@link entity.tower.MachineGunTower Machine Gun Towers}.
 */
public class Minion extends StackPane implements Updatable {
    /**
     * The path the minion takes when moving.
     */
    private final ArrayList<Vector2> path;

    /**
     * The current destination number of the path
     */
    private int destinationIndex;

    /**
     * The current destination that the minion is moving towards.
     */
    private Vector2 destination;

    /**
     * The current position of the minion.
     */
    private Vector2 currentPosition;

    /**
     * The name of the minion.
     */
    private String name;

    /**
     * The reward for killing the minion.
     */
    private int reward;

    /**
     * The damage the minion will deal to the player's health if it reaches the end of path.
     */
    private int penalty;

    /**
     * The maximum amount of health of the minion.
     */
    private double maxHealth;

    /**
     * The current amount of health of the minion.
     */
    private double currentHealth;

    /**
     * The speed in which the minion moves.
     */
    private double speed;

    /**
     * The resistance to damage from {@link entity.tower.MachineGunTower Machine Gun Towers}.
     */
    private double resist_MG;

    /**
     * The resistance to damage from {@link entity.tower.RocketTower Rocket Towers}.
     */
    private double resist_Rocket;

    /**
     * The resistance to damage from {@link entity.tower.CannonTower Cannon Towers}.
     */
    private double resist_Cannon;

    /**
     * This field indicates whether the minion is flying or not.
     */
    private boolean isFlying;

    /**
     * The health bar above the minion.
     * It only appears after it has taken a hit from a {@link entity.tower.base.Tower Tower}.
     */
    private ProgressBar healthBar;

    /**
     * The {@link CellImage} that represents the minion.
     */
    private final CellImage minionImage;

    /**
     * The drop shadow for when the minion is selected.
     */
    private final DropShadow dropShadow;

    /**
     * The constructor for the Minion class. This constructor creates and position the {@link CellImage Image}
     * of this minion and initializes all related data, along with setting up event listener.
     * @param minionData The data of this minion.
     */
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

        this.setOnMouseClicked(e -> {
            minionImage.setEffect(dropShadow);
            GamePane.entityInformationPane.setSelectedMinion(this);
        });
               
        setHealthBar();
    }

    /**
     * Extracts the data from the given {@link MinionData} to all the related fields.
     * @param minionData The data of this minion.
     */
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

    /**
     * Sets up the {@link #healthBar} above the minion.
     */
    public void setHealthBar() {
        healthBar = new ProgressBar(1);
        healthBar.setPrefWidth(24);
        healthBar.setPrefHeight(12);
        healthBar.setTranslateY(-24);
        healthBar.setVisible(false);
        this.getChildren().add(healthBar);
    }

    /**
     * Gets the {@link #currentPosition} of this minion.
     * @return A {@link Vector2 coordinate} of the current position.
     */
    public Vector2 getCurrentPosition() {
    	return currentPosition;
    }

    /**
     * Changes this minion's destination to the next one. If the current destination
     * is the last one, removes itself and deal damage to the player.
     */
    public void changeDestination() {
        destinationIndex++;
        if (destinationIndex >= path.size()) {
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

    /**
     * The implementation of the {@link Updatable#update(double) update} method of the {@link Updatable} interface.
     * This method calculates the movement to the next position as well as sets the rotation every frame.
     * @param deltaTime The time since the last frame, measured in seconds.
     */
    @Override
    public void update(double deltaTime) {
        move(deltaTime);

        this.lookAt(destination);

        this.setLayoutX(currentPosition.getX());
        this.setLayoutY(currentPosition.getY());
    }

    /**
     * Calculates the movement distance and direction for this frame, then apply it on to the current position.
     * @param deltaTime The time since the last frame, measured in seconds.
     */
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

    /**
     * Sets the rotation so that the forward axis points to the given {@link Vector2 coordinate}.
     * @param target The target to have the forward axis point to.
     */
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

    /**
     * Enables or disables the drop shadow based on the active parameter.
     * @param active Indicates whether to enable or disable the drop shadow.
     */
    public void setDropShadow(boolean active) {
        if (active) {
            minionImage.setEffect(dropShadow);
        } else {
            minionImage.setEffect(null);
        }
    }

    /**
     * Calculates the distance to the current {@link #destination}.
     * @return The distance to the current destination.
     */
    public double distanceToDestination() {
        return Vector2.distance(currentPosition, destination);
    }

    /**
     * Gets the {@link #destinationIndex}.
     * @return The {@link #destinationIndex}
     */
    public int getDestinationIndex() {
        return destinationIndex;
    }

    /**
     * Calculates the final damage taken from the given {@link DamageType}. This method takes the raw damage
     * and lower it based on this minion's resistances. If this minion dies from the damage, remove it and
     * increase the amount of gold the player has.
     * @param rawDamage The damage received from the {@link entity.tower.base.Tower Tower}.
     * @param damageType The type of damage this minion receives.
     */
    public void takeDamage(double rawDamage, DamageType damageType) {
        if (currentHealth <= 0)
            return;
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
            GamePane.entityInformationPane.setSelectedMinion(null);
            GameController.addGold(reward);
        }

        healthBar.setProgress(currentHealth / maxHealth);
        healthBar.setVisible(true);
    }

    /**
     * Gets the {@link #isFlying}.
     * @return The {@link #isFlying}
     */
    public boolean isFlying() {
        return isFlying;
    }

    /**
     * Gets the {@link #name}
     * @return The {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the {@link #maxHealth}
     * @return The {@link #maxHealth}
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * Gets the {@link #currentHealth}
     * @return The {@link #currentHealth}
     */
    public double getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Gets the {@link #resist_MG}
     * @return The {@link #resist_MG}
     */
    public double getResist_MG() {
        return resist_MG;
    }

    /**
     * Gets the {@link #resist_Rocket}
     * @return The {@link #resist_Rocket}
     */
    public double getResist_Rocket() {
        return resist_Rocket;
    }

    /**
     * Gets the {@link #resist_Cannon}
     * @return The {@link #resist_Cannon}
     */
    public double getResist_Cannon() {
        return resist_Cannon;
    }
}
