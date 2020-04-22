package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControlPane extends VBox {
	private static final int ControlPaneSize = 100;

    public ControlPane() {
        this.setBackground(new Background(new BackgroundFill(Color.gray(0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefWidth(ControlPaneSize);
        this.setSpacing(10);
    }
    public void addTower(int bgSprite, String name, String damage, String rates, String range, boolean isTool) {
    	TowerCell cell = new TowerCell(bgSprite, name, damage, rates, range, isTool);
    	this.getChildren().add(cell);
    }
    public static int getControlPaneSize() {
    	return ControlPaneSize;
    }
}
