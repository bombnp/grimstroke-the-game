package entity.building.base;

import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;

public abstract class Tower extends Building {
    protected CellImage turretImage;

    public Tower(BoardCell cell, int turretSprite) {
        super(cell, Sprite.getRandomTowerBase());
        turretImage = new CellImage(turretSprite);
        this.getChildren().add(turretImage);
    }
}
