package entity.building;

import entity.building.base.Building;
import gui.BoardCell;
import gui.Sprite;
import javafx.event.EventHandler;
import javafx.event.EventType;

public class Buildspot extends Building {
    public Buildspot(BoardCell cell) {
        super(cell, Sprite.BUILD_SPOT);
    }
}
