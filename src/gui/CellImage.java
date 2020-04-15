package gui;

import application.Utility;
import exception.SpriteIndexOutOfBoundsException;
import javafx.scene.image.ImageView;

public class CellImage extends ImageView {

    public CellImage(int spriteIndex) {
        try {
            this.setImage(Utility.getSprite(spriteIndex));
        } catch (SpriteIndexOutOfBoundsException e) {
            this.setImage(null);
        }
        this.setFitWidth(48);
        this.setFitHeight(48);
    }
}
