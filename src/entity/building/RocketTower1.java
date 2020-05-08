package entity.building;

import database.Database;
import entity.building.base.Tower;
import gui.BoardCell;

public class RocketTower1 extends Tower {
    public RocketTower1(BoardCell cell) {
        super(cell, Database.R1);
    }
}
