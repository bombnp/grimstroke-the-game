package entity.building;

import database.Database;
import entity.building.base.Tower;
import gui.BoardCell;

public class CannonTower2 extends Tower {
    public CannonTower2(BoardCell cell) {
        super(cell, Database.C2);
        this.turretImage.setTranslateY(-5);
    }
}
