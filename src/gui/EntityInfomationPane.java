package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class EntityInfomationPane extends Pane{
	public EntityInfomationPane() {
		
	}
	public void CreateBox() {
		this.setPrefSize(50, 50);
		System.out.println("CREATED");
		System.out.println(this.getWidth()+" "+this.getHeight());
	}
	public void HidePane() {
		this.setVisible(false);
	}
	public void ShowPane() {
		this.setVisible(true);
	}
}
