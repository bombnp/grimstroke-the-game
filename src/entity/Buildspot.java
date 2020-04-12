package entity;

import entity.base.Building;
import gui.BoardCell;
import gui.Sprite;

public class Buildspot extends Building {
    public Buildspot(BoardCell cell) {
        super(cell, Sprite.BUILD_SPOT);
    }
}
