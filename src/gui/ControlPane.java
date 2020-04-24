package gui;

import application.Utility;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControlPane extends VBox {
	private static final int CONTROL_PANE_WIDTH = 100;

    public ControlPane() {
        this.setBackground(new Background(new BackgroundFill(Color.gray(0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefWidth(CONTROL_PANE_WIDTH);
        this.setSpacing(10);

        String[][] TowerDataCSV = Utility.readCSV("data/TowerData.csv");
        for(String[] str: TowerDataCSV) {
            // ImgIDX,Name,DamageRange,RateOfFire,Range,isTool
            addTower(Integer.parseInt(str[0]), str[1], str[2], str[3], str[4]);
        }
    }

    public void addTower(int bgSprite, String name, String damage, String rates, String range) {
    	TowerCell cell = new TowerCell(bgSprite, name, damage, rates, range);
    	this.getChildren().add(cell);
    }

    public static int getControlPaneWidth() {
    	return CONTROL_PANE_WIDTH;
    }
}
