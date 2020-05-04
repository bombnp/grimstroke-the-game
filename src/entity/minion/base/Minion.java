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

        GameController.addUpdatable(this);
        GameController.addMinion(this);

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
        destination = path.get(destinationIndex);
    }

    @Override
    public void update(double deltaTime) {
        //TODO
    	if(currentPosition.getX() == destination.getX() && currentPosition.getY() == destination.getY()) {
            changeDestination();
    	}else{
    		move();
    	}
    }

    public void move() {
			if(currentPosition.getX() < destination.getX()) {
				currentPosition = new Vector2(currentPosition.getX() + 1*speed , currentPosition.getY());
		        this.setRotate(0);
			}
			if(currentPosition.getX() > destination.getX()) {
				currentPosition = new Vector2(currentPosition.getX() - 1*speed  , currentPosition.getY());
		        this.setRotate(180);
			}			
			if(currentPosition.getY() < destination.getY()) {
				currentPosition = new Vector2(currentPosition.getX(), currentPosition.getY() + 1*speed );
		        this.setRotate(90);
			}
			if(currentPosition.getY() > destination.getY()) {
				currentPosition = new Vector2(currentPosition.getX(), currentPosition.getY() - 1*speed );
		        this.setRotate(-90);
			}
			this.setX(currentPosition.getX());
			this.setY(currentPosition.getY());
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
