package gui;

import application.Utility;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CellImage extends ImageView {

    private final Image image;

    public CellImage(int spriteIndex) {
        image = Utility.getSprite(spriteIndex);
        this.setImage(image);

        this.setFitWidth(48);
        this.setFitHeight(48);
    }

    public CellImage(String imageName) {
        image = new Image(imageName);
        this.setImage(image);

        this.setFitWidth(48);
        this.setFitHeight(48);
    }

    public void enable() {
        this.setImage(image);
    }

    public void disable() {
        this.setImage(null);
    }
}
