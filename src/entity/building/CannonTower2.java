package entity.building;

import entity.building.base.SingleTargetTower;
import gui.BoardCell;
import gui.Sprite;

public class CannonTower2 extends SingleTargetTower {
    public CannonTower2(BoardCell cell) {
        super(cell, Sprite.CANNON_TURRET_2);
        this.turretImage.setTranslateY(-5);
    }
}
