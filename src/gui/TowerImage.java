package gui;

import application.Utility;
import exception.SpriteIndexOutOfBoundsException;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class TowerImage extends ImageView{
    public TowerImage(int spriteIndex) {
        try {
            this.setImage(Utility.getSprite(spriteIndex));
        } catch (SpriteIndexOutOfBoundsException e) {
            this.setImage(null);
        }
        this.setFitWidth(ControlPane.GetControlPaneSize());
        this.setFitHeight(ControlPane.GetControlPaneSize());
        
    }
}
