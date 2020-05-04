package logic;

public class Vector2 {
    private final double x, y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(other.getX()+x, other.getY()+y);
    }

    public Vector2 multiply(double time) {
        return new Vector2(x*time, y*time);
    }

    public Vector2 getDirectionTo(Vector2 target) {
        return new Vector2(target.getX()-x, target.getY()-y);
    }

    public double distance(Vector2 point) {
        double dx = this.getX() - point.getX();
        double dy = this.getY() - point.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double getMagnitude() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector2 normalize() {
        double magnitude = getMagnitude();
        return new Vector2(x/magnitude, y/magnitude);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
