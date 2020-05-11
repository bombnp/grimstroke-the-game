package gui;

import application.Utility;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CellImage extends ImageView {
    public CellImage(int spriteIndex) {
        this.setImage(Utility.getSprite(spriteIndex));

        this.setFitWidth(48);
        this.setFitHeight(48);
    }

    public CellImage(String imageName) {
        this.setImage(new Image(imageName));

        this.setFitWidth(48);
        this.setFitHeight(48);
    }

    public CellImage() {
        this.setFitWidth(48);
        this.setFitHeight(48);
    }

    public void setCenter() {
        this.setTranslateX(-24);
        this.setTranslateY(-24);
    }
}
