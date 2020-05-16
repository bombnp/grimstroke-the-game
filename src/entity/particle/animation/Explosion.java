package entity.particle.animation;

import application.Utility;
import gui.CellImage;
import gui.GUIController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import logic.Vector2;

/**
 * The Explosion class represents an explosion from the {@link entity.tower.RocketTower RocketTower}.
 * This class, when instantiated, loads the explosion animation and play through it at the given position,
 * then removes itself.
 */
public class Explosion extends CellImage {
    /**
     * The explosion animation.
     */
    private final Image[] animation;

    /**
     * The FPS of this animation ({@value})
     */
    private final double FPS = 15;

    /**
     * The constructor of the Explosion class. Loads the animation and create a {@link Thread} to play through it.
     * @param position The position of the explosion.
     * @param explosionScale The size of the explosion, compared to the original size.
     */
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
