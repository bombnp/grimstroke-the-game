package entity.building;

import database.Database;
import entity.building.base.Tower;
import gui.BoardCell;

public class RocketTower2 extends Tower {
    public RocketTower2(BoardCell cell) {
        super(cell, Database.R2);
    }
}
