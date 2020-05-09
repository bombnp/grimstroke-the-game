package entity.building;

import database.Database;
import entity.building.base.Tower;
import entity.minion.base.Minion;
import gui.BoardCell;

public class MachineGunTower extends Tower {
    public MachineGunTower(BoardCell cell) {
        super(cell, Database.M1);
        this.turretImage.setTranslateY(-5);
    }

    @Override
    public void attack(Minion target) {

    }
}
