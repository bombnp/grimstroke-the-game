package entity.building;

import entity.building.base.SingleTargetTower;
import gui.BoardCell;
import gui.Sprite;

public class MachineGunTower extends SingleTargetTower {
    public MachineGunTower(BoardCell cell) {
        super(cell, Sprite.MACHINE_GUN_TURRET);
        this.turretImage.setTranslateY(-5);
    }
}
