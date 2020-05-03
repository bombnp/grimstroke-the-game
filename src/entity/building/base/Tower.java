package entity.building.base;

import gui.BoardCell;
import gui.CellImage;
import gui.Sprite;
import logic.Coordinate;

public abstract class Tower extends Building {
    protected CellImage turretImage;

    public Tower(BoardCell cell, int turretSprite) {
        super(cell, Sprite.getRandomTowerBase());
        turretImage = new CellImage(turretSprite);
        this.getChildren().add(turretImage);
    }

    public void lookAt(Coordinate target) {
        int dx = target.getX() - this.getCenterPosition().getX();
        int dy = target.getY() - this.getCenterPosition().getY();


        if (dx == 0) { // on axis Y
            if (dy > 0) { // target is below centerPosition
                this.setRotate(0);
            } else { //target is above centerPosition
                this.setRotate(180);
            }
        }
        else if (dy == 0) { // on axis X
            if (dx > 0) { // target is to the right of centerPosition
                this.setRotate(90);
            } else { // target is to the left of centerPosition
                this.setRotate(270);
            }
        }
        else { // on quadrants
            double alpha = Math.toDegrees(Math.atan((double)dx/dy));
            if (dy > 0) { // target is below centerPosition, quadrant 3,4
                this.setRotate(180 - alpha);
            } else { // target is above centerPosition, quadrant 1,2
                this.setRotate(360 - alpha);
            }
        }
    }
}
