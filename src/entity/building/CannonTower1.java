package entity.building;

import database.Database;
import entity.building.base.Tower;
import gui.BoardCell;

public class CannonTower1 extends Tower {
    public CannonTower1(BoardCell cell) {
        super(cell, Database.C1);
        this.turretImage.setTranslateY(-5);
    }
}
