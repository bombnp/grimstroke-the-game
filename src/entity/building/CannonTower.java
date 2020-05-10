package entity.building;

import database.Database;
import entity.building.base.Tower;
import entity.minion.base.Minion;
import gui.BoardCell;

public class CannonTower extends Tower {
    public CannonTower(BoardCell cell, int level) {
        super(cell, Database.Cannon[level-1], level);
        this.turretImage.setTranslateY(-5);
    }

    @Override
    public void attack(Minion target) {

    }
}
