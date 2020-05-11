package logic;

import javafx.application.Platform;

public class Invoker {

    private final Runnable runnable;

    public Invoker(Runnable runnable) {
        this.runnable = runnable;
    }

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
