package logic;

public class Coordinate {
    private final int x, y;

    public Coordinate() {
        this.x = this.y = 0;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
