package entity.building;

import entity.building.base.SingleTargetTower;
import gui.BoardCell;
import gui.Sprite;

public class CannonTower1 extends SingleTargetTower {
    public CannonTower1(BoardCell cell) {
        super(cell, Sprite.CANNON_TURRET_1);
        this.turretImage.setTranslateY(-5);
    }
}
