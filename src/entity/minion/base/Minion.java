package entity.minion.base;

import application.Utility;
import entity.Updatable;
import javafx.scene.image.ImageView;
import logic.Coordinate;
import logic.GameController;

import java.util.ArrayList;

public abstract class Minion extends ImageView implements Updatable {
    private ArrayList<Coordinate> path;
    private int destinationIndex;

    private Coordinate destination;

    public Minion(int minionSprite) {
        super(Utility.getSprite(minionSprite));
        path = GameController.getMinionPath();
        destinationIndex = 0;
        destination = path.get(destinationIndex);

        GameController.addUpdatable(this);

        this.setTranslateX(-24);
        this.setTranslateY(-24);
    }

    @Override
    public void update(double deltaTime) {
        //TODO
    }
}
