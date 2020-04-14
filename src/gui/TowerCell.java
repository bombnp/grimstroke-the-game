package gui;

import com.sun.glass.events.MouseEvent;

import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class TowerCell extends StackPane{
	int PrefSize = ControlPane.GetControlPaneSize();
	private String Name;
	private String Damage;
	private String Rates;
	private String Range;
	public TowerCell(int bgSprite,String Name,String Damage,String Rates,String Range) {
		this.getChildren().add(new TowerImage(bgSprite));
		this.setPrefWidth(PrefSize);
		this.setPrefWidth(PrefSize);
	}
}
