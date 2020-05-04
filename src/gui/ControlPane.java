package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.Database;
import logic.TowerData;

public class ControlPane extends VBox {
	private static final int CONTROL_PANE_WIDTH = 100;

    public ControlPane() {
        this.setBackground(new Background(new BackgroundFill(Color.gray(0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefWidth(CONTROL_PANE_WIDTH);
        this.setSpacing(10);

        addTower(Database.MG);
        addTower(Database.R1);
        addTower(Database.R2);
        addTower(Database.C1);
        addTower(Database.C2);

        this.getChildren().add(new TowerCell(true));
    }

    public void addTower(TowerData towerData) {
        TowerCell towerCell = new TowerCell(towerData);
        this.getChildren().add(towerCell);
    }

    public static int getControlPaneWidth() {
    	return CONTROL_PANE_WIDTH;
    }
}
