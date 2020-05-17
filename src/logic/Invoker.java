package logic;

import javafx.application.Platform;

/**
 * The Invoker class is a utility class that runs a {@link Runnable} in a later time.
 */
public class Invoker {
    /**
     * The {@link Runnable} to be run.
     */
    private final Runnable runnable;

    /**
     * The constructor of the Invoker class. Sets the {@link #runnable} field.
     * @param runnable The {@link Runnable} to be run.
     */
    public Invoker(Runnable runnable) {
        this.runnable = runnable;
    }

    /**
     * Runs the {@link #runnable} after the given amount of time.
     * @param millis The delay before running the {@link #runnable}.
     */
    public void startIn(long millis) {
        new Thread(() -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(runnable);
        }).start();
    }
}
