package gui;

import javafx.scene.layout.Pane;

public class EntityInformationPane extends Pane{
	public EntityInformationPane() {
		
	}
	public void CreateBox() {
		this.setPrefSize(50, 50);
		System.out.println("CREATED ฺBOX");
		System.out.println(this.getWidth()+" "+this.getHeight());
	}
}
