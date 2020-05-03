package entity.building;

import entity.building.base.MultipleTargetTower;
import gui.BoardCell;
import gui.Sprite;

public class RocketTower2 extends MultipleTargetTower {
    public RocketTower2(BoardCell cell) {
        super(cell, Sprite.ROCKET_TURRET_2);
    }
}
