package entity.minion.base;

import application.Utility;
import javafx.scene.image.ImageView;
import logic.Coordinate;
import logic.GameController;

import java.util.ArrayList;

public abstract class Minion extends ImageView {
    private ArrayList<Coordinate> path;
    private int destinationIndex;

    private Coordinate destination;

    public Minion(int minionSprite) {
        super(Utility.getSprite(minionSprite));
        path = GameController.getMinionPath();
        destinationIndex = 0;
        destination = path.get(destinationIndex);

        this.setTranslateX(-24);
        this.setTranslateY(-24);
    }
}
