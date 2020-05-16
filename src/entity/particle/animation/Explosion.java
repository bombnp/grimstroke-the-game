package entity.particle.animation;

import application.Utility;
import gui.CellImage;
import gui.GUIController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import logic.Vector2;

public class Explosion extends CellImage {
    private final Image[] animation;
    private final double FPS = 15;

    public Explosion(Vector2 position, double explosionScale) {
        animation = Utility.getAnimation("explosion", 8);

        this.setX(position.getX());
        this.setY(position.getY());
        this.setCenter();
        this.setScaleX(explosionScale);
        this.setScaleY(explosionScale);
        GUIController.getGamePane().getChildren().add(this);

        new Thread(() -> {
            for (Image frame : animation) {
                Platform.runLater(() -> this.setImage(frame));
                try {
                    Thread.sleep((long) (1000/FPS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> GUIController.getGamePane().getChildren().remove(this));
        }).start();
    }
}
