package debug;

import entity.tower.base.Tower;
import gui.GUIController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.HashMap;
import java.util.Map;

/**
 * The Debug class contains helpful debugging methods used during development.
 */
public class Debug {
    /**
     * A {@link HashMap} that maps {@link Tower Towers} to {@link Shape Shapes}
     * so that generated tower ranges can be removed later.
     */
    private static final Map<Tower, Shape> rangeCircleMap = new HashMap<>();

    /**
     * Draws a visible range of the given {@link Tower}.
     * This method draws the range directly on the {@link gui.GamePane GamePane}
     * and map the given tower to the drawn {@link Shape} in {@link #rangeCircleMap}.
     *
     * @param tower The tower for the range to be drawn on.
     * @see Tower
     */
    public static void drawTowerRange(Tower tower) {
        Shape rangeCircle = new Circle(tower.getRange(), Color.color(1, 0, 0, 0.10));
        rangeCircle.setLayoutX(tower.getCenterPosition().getX());
        rangeCircle.setLayoutY(tower.getCenterPosition().getY());
        rangeCircle.setMouseTransparent(true);
        rangeCircleMap.put(tower, rangeCircle);
        GUIController.getGamePane().getChildren().add(rangeCircle);
	}

    /**
     * Removes the range of the given {@link Tower}. Called when a tower is removed.
     * This method looks up the {@link Shape} that the tower is mapped to in {@link #rangeCircleMap}
     * and removes it from the {@link gui.GamePane GamePane}.
     * @param tower The {@link Tower} whose range is to be removed.
     */
	public static void removeTowerRange(Tower tower) {
        Shape rangeCircle = rangeCircleMap.get(tower);
        GUIController.getGamePane().getChildren().remove(rangeCircle);
    }
}
