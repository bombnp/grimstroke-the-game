package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControlPane extends VBox {

    public ControlPane() {
        this.setBackground(new Background(new BackgroundFill(Color.gray(0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefWidth(300);
    }
}
