package entity.building;

import entity.building.base.Tower;
import gui.BoardCell;
import gui.Sprite;

public class CannonTower2 extends Tower {
    public CannonTower2(BoardCell cell) {
        super(cell, Sprite.CANNON_TURRET_2);
    }
}
