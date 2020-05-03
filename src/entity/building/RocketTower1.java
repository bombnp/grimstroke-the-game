package entity.building;

import entity.building.base.MultipleTargetTower;
import gui.BoardCell;
import gui.Sprite;

public class RocketTower1 extends MultipleTargetTower {
    public RocketTower1(BoardCell cell) {
        super(cell, Sprite.ROCKET_TURRET_1);
    }
}
