package entity.building;

import database.Database;
import entity.building.base.Tower;
import gui.BoardCell;

public class MachineGunTower extends Tower {
    public MachineGunTower(BoardCell cell) {
        super(cell, Database.MG);
        this.turretImage.setTranslateY(-5);
    }
}
