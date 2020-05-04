package logic;

public class Coordinate {
    private final double x, y;

    public Coordinate() {
        this.x = this.y = 0;
    }
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
