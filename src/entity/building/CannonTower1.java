package entity.building;

import entity.building.base.Tower;
import gui.BoardCell;
import gui.Sprite;

public class CannonTower1 extends Tower {
    public CannonTower1(BoardCell cell) {
        super(cell, Sprite.CANNON_TURRET_1);
    }
}
