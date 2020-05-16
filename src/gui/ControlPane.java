package gui;

import database.Database;
import database.TowerData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControlPane extends VBox {
	private static final int CONTROL_PANE_WIDTH = 100;

    public ControlPane() {
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefWidth(CONTROL_PANE_WIDTH);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        addTower(Database.MG[0]);
        addTower(Database.Rocket[0]);
        addTower(Database.Cannon[0]);

        this.getChildren().add(new TowerCell("Upgrade Tool"));
        this.getChildren().add(new TowerCell("Sell Tool"));
    }

    public void addTower(TowerData towerData) {
        TowerCell towerCell = new TowerCell(towerData);
        this.getChildren().add(towerCell);
    }

    public static int getControlPaneWidth() {
    	return CONTROL_PANE_WIDTH;
    }
}
