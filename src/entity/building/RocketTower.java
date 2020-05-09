package entity.building;

import database.Database;
import entity.building.base.Tower;
import entity.minion.base.Minion;
import gui.BoardCell;

public class RocketTower extends Tower {
    public RocketTower(BoardCell cell) {
        super(cell, Database.R1);
    }

    @Override
    public void attack(Minion target) {

    }
}
