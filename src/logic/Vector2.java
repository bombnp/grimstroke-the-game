package logic;

/**
 * The Vector2 class represents the coordinate system used by the application. It contains various mathematical
 * methods that deal with points and vectors.
 */
public class Vector2 {
    /**
     * The x position of the vector.
     */
    private final double x;


    /**
     * The y position of the vector.
     */
    private final double y;

    /**
     * The constructor of the Vector2 class. This variants constructs the object with {@link Integer} parameters.
     * @param x The x position of the vector.
     * @param y The y position of the vector.
     */
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The constructor of the Vector2 class. This variants constructs the object with {@link Double} parameters.
     * @param x The x position of the vector.
     * @param y The y position of the vector.
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the {@link #x}.
     * @return The {@link #x}.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the {@link #y}.
     * @return The {@link #y}.
     */
    public double getY() {
        return y;
    }

    /**
     * Creates a new Vector2 from adding the given Vector2 to the current one.
     * @param other The Vector2 to add to the current vector.
     * @return A new Vector2 created from adding the two vectors together.
     */
    public Vector2 add(Vector2 other) {
        return new Vector2(other.getX()+x, other.getY()+y);
    }

    /**
     * Creates a new Vector2 from multiplying the current Vector2 with the given scale. The direction is the same.
     * @param scale The scale to to multiply the current vector with.
     * @return A new Vector2 created from multiplying the current Vector2 with the given scale.
     */
    public Vector2 multiply(double scale) {
        return new Vector2(x*scale, y*scale);
    }

    /**
     * Creates a new Vector2 that starts from the current coordinate and ends at the target.
     * @param target The target to point to.
     * @return A new Vector2 that starts from the current coordinate and ends at the target.
     */
    public Vector2 getDirectionTo(Vector2 target) {
        return new Vector2(target.getX()-x, target.getY()-y);
    }

    /**
     * Gets the magnitude of the vector.
     * @return The magnitude of the vector.
     */
    public double getMagnitude() {
        return Math.sqrt(x*x + y*y);
    }

    /**
     * Creates a new Vector2 of the same direction with the magnitude of 1.
     * @return A new Vector2 of the same direction with the magnitude of 1.
     */
    public Vector2 normalize() {
        double magnitude = getMagnitude();
        return new Vector2(x/magnitude, y/magnitude);
    }

    /**
     * Generates a string that represents the vector.
     * @return A string that represents the vector.
     */
    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Calculates the distance between the two given points.
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between the two given points.
     */
    public static double distance(Vector2 p1, Vector2 p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Creates a new Vector2 from the given magnitude and direction.
     * @param magnitude The magnitude of the vector.
     * @param direction The rotation of the vector from the y axis.
     * @return A new Vector2 from the given magnitude and direction.
     */
    public static Vector2 fromMagnitudeAndDirection(double magnitude, double direction) {
        return new Vector2(magnitude * Math.sin(Math.toRadians(direction)), -1 * magnitude * Math.cos(Math.toRadians(direction)));
    }
}
