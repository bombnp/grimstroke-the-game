package gui;

import application.Utility;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The TowerImage class represents the image used in {@link TowerCell TowerCells}.
 */
public class TowerImage extends ImageView{
    /**
     * The constructor of the TowerImage class. This variant sets the image to the given {@link Sprite spriteIndex}.
     * @param spriteIndex The sprite of the tower.
     */
    public TowerImage(int spriteIndex) {
        this.setImage(Utility.getSprite(spriteIndex));

        this.setFitWidth(TowerConstructionPane.TOWER_PANE_HEIGHT);
        this.setFitHeight(TowerConstructionPane.TOWER_PANE_HEIGHT);
    }

    /**
     * The constructor of the TowerImage class. This variant sets the image to the given image name.
     * @param imagePath The name of the image to be set to.
     */
    public TowerImage(String imagePath) {
        this.setImage(new Image(imagePath));

        this.setFitWidth(TowerConstructionPane.TOWER_PANE_HEIGHT);
        this.setFitHeight(TowerConstructionPane.TOWER_PANE_HEIGHT);
    }
}
