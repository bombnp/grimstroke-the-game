package entity.building;

import database.Database;
import entity.building.base.Tower;
import gui.BoardCell;

public class CannonTower extends Tower {
    public CannonTower(BoardCell cell) {
        super(cell, Database.C1);
        this.turretImage.setTranslateY(-5);
    }
}
