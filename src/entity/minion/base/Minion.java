package entity.minion.base;

import application.Utility;
import entity.Updatable;
import gui.GUIController;
import javafx.scene.image.ImageView;
import logic.Coordinate;
import logic.GameController;

import java.util.ArrayList;

public abstract class Minion extends ImageView implements Updatable {
    private ArrayList<Coordinate> path;
    private int destinationIndex;

    private Coordinate destination;
    private Coordinate currentPosition;
    
    private String name,desc;
    private int reward;
    private boolean isGroundUnit;
    private float speed,MG_RED,MIS_RED,CAN_RED;
    
    public Minion(int minionSprite,int type) {
        super(Utility.getSprite(minionSprite));
        path = GameController.getMinionPath();
        destinationIndex = 0;
        destination = path.get(destinationIndex);

        GameController.addUpdatable(this);
        GameController.addMinion(this);

        // offset to make image appear at center of position
        this.setTranslateX(-24);
        this.setTranslateY(-24);
        this.setFitHeight(48);
        this.setFitWidth(48);
        //
        GUIController.getGamePane().getChildren().add(this);
        currentPosition = destination;
        
        this.setX(currentPosition.getX());
        this.setY(currentPosition.getY());
        nextPath();
    }
    public Coordinate getCoordinate() {
    	return currentPosition;
    }
    public void setName(String Name) {
    	this.name = Name;
    }
    public void setDesc(String Desc) {
    	this.desc = Desc;
    }
    public void setReward(int Reward) {
    	this.reward = Reward;
    }
    public void setType(boolean type) {
    	this.isGroundUnit = type;
    }
    public void setSpeed(float Speed) {
    	this.speed = Speed;
    }
    public void setResistance(float[] Resistance) {
    	this.MG_RED = Resistance[0];
    	this.MIS_RED = Resistance[1];
    	this.CAN_RED = Resistance[2];
    }
    public void nextPath() {
        destinationIndex++;
        destination = path.get(destinationIndex);
    }
    @Override
    public void update(double deltaTime) {
        //TODO
    	System.out.println(currentPosition.toString()+destination.toString()); 	
    	if(currentPosition.getX() == destination.getX() && currentPosition.getY() == destination.getY()) {
            nextPath();
    	}else{
    		Move();
    	}
    }
    public void Move() {
			if(currentPosition.getX() < destination.getX()) {
				currentPosition = new Coordinate(currentPosition.getX() + 1*speed , currentPosition.getY());
		        this.setRotate(0);
			}
			if(currentPosition.getX() > destination.getX()) {
				currentPosition = new Coordinate(currentPosition.getX() - 1*speed  , currentPosition.getY());
		        this.setRotate(180);
			}			
			if(currentPosition.getY() < destination.getY()) {
				currentPosition = new Coordinate(currentPosition.getX(), currentPosition.getY() + 1*speed );
		        this.setRotate(90);
			}
			if(currentPosition.getY() > destination.getY()) {
				currentPosition = new Coordinate(currentPosition.getX(), currentPosition.getY() - 1*speed );
		        this.setRotate(-90);
			}
			this.setX(currentPosition.getX());
			this.setY(currentPosition.getY());
    }
}
