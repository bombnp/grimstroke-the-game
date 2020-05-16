package gui;

import application.Utility;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TowerImage extends ImageView{
    public TowerImage(int spriteIndex) {
        this.setImage(Utility.getSprite(spriteIndex));

        this.setFitWidth(TowerConstructionPane.TOWER_PANE_HEIGHT);
        this.setFitHeight(TowerConstructionPane.TOWER_PANE_HEIGHT);
    }

    public TowerImage(String imagePath) {
        this.setImage(new Image(imagePath));

        this.setFitWidth(TowerConstructionPane.TOWER_PANE_HEIGHT);
        this.setFitHeight(TowerConstructionPane.TOWER_PANE_HEIGHT);
    }
}
