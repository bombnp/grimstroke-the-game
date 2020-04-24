package entity.building;

import entity.building.base.Tower;
import gui.BoardCell;
import gui.Sprite;

public class MachineGunTower extends Tower {
    public MachineGunTower(BoardCell cell) {
        super(cell, Sprite.MACHINE_GUN_TURRET);
    }
}
