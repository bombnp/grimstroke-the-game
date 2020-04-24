package entity.building;

import entity.building.base.Tower;
import gui.BoardCell;
import gui.Sprite;

public class RocketTower2 extends Tower {
    public RocketTower2(BoardCell cell) {
        super(cell, Sprite.ROCKET_TURRET_2);
    }
}
