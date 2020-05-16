package entity;

/**
 * The Updatable interface represents classes that are supposed to be updated every frame. The method is called
 * from {@link logic.GameController GameController} using {@link javafx.animation.AnimationTimer AnimationTimer} class.
 */
public interface Updatable {
    /**
     * This method is called every frame from the {@link logic.GameController GameController}.
     * @param deltaTime The time since the last frame.
     */
    void update(double deltaTime);
}
