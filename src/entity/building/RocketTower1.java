package entity.building;

import entity.building.base.Tower;
import gui.BoardCell;
import gui.Sprite;

public class RocketTower1 extends Tower {
    public RocketTower1(BoardCell cell) {
        super(cell, Sprite.ROCKET_TURRET_1);
    }
}
