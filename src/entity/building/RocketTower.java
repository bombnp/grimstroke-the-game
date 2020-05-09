package entity.building;

import database.Database;
import entity.building.base.Tower;
import entity.minion.base.Minion;
import gui.BoardCell;

public class RocketTower extends Tower {
    public RocketTower(BoardCell cell, int level) {
        super(cell, Database.Rocket[level-1]);
    }

    @Override
    public void attack(Minion target) {

    }
}
