package debug;

import entity.tower.base.Tower;
import gui.GUIController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.HashMap;
import java.util.Map;

public class Debug {
    private static final Map<Tower, Shape> rangeCircleMap = new HashMap<>();

    public static void drawTowerRange(Tower tower) {
        Shape rangeCircle = new Circle(tower.getRange(), Color.color(1, 0, 0, 0.10));
        rangeCircle.setLayoutX(tower.getCenterPosition().getX());
        rangeCircle.setLayoutY(tower.getCenterPosition().getY());
        rangeCircle.setMouseTransparent(true);
        rangeCircleMap.put(tower, rangeCircle);
        GUIController.getGamePane().getChildren().add(rangeCircle);
	}

	public static void removeTowerRange(Tower tower) {
        Shape rangeCircle = rangeCircleMap.get(tower);
        GUIController.getGamePane().getChildren().remove(rangeCircle);
    }
}
