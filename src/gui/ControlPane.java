package gui;

import com.sun.glass.events.KeyEvent;
import com.sun.glass.events.MouseEvent;
import com.sun.javafx.event.EventHandlerManager;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GameController;

public class ControlPane extends VBox {
	private static int ControlPaneSize = 100;
	private static int NumberOfTower = 5;
	private static int NumberOfTowerData = 4;
    public ControlPane() {
        this.setBackground(new Background(new BackgroundFill(Color.gray(0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefWidth(ControlPaneSize);
        this.setSpacing(10);
    }
    public TowerCell addTower(int bgSprite,String Name,String Damage,String Rates,String Range) {
    	TowerCell cell = new TowerCell(bgSprite,Name,Damage,Rates,Range);
    	this.getChildren().add(cell);
		return cell;
    }
    public static int GetControlPaneSize() {
    	return ControlPaneSize;
    }
}
