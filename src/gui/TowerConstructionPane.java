package gui;

import database.Database;
import database.TowerData;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class TowerConstructionPane extends HBox {
	public static final int TOWER_PANE_HEIGHT = 59;

    public TowerConstructionPane() {
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefHeight(TOWER_PANE_HEIGHT);

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

}
