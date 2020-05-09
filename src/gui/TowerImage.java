package gui;

import application.Utility;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TowerImage extends ImageView{
    public TowerImage(int spriteIndex) {
        this.setImage(Utility.getSprite(spriteIndex));

        this.setFitWidth(ControlPane.getControlPaneWidth());
        this.setFitHeight(ControlPane.getControlPaneWidth());
    }

    public TowerImage(String imagePath) {
        this.setImage(new Image(imagePath));

        this.setFitWidth(ControlPane.getControlPaneWidth());
        this.setFitHeight(ControlPane.getControlPaneWidth());
    }
}
