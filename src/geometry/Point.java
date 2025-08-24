//Yair Tabak 322593906
package geometry;
import java.util.Random;

/**
 * A class representing a point in 2D space.
 */
public class Point {
    // Tolerance for comparing floating-point numbers
    private static final double EPSILON = 0.0001;
    private double x; // x-coordinate of the point
    private double y; // y-coordinate of the point

    /**
     * Constructs a new geometry.Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new geometry.Point with random coordinates.
     */
    public Point() {
        Random rand = new Random();
        this.x = rand.nextDouble();
        this.y = rand.nextDouble();
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        // Euclidean distance formula: sqrt((x2 - x1)^2 + (y2 - y1)^2)
        return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
    }

    /**
     * Checks if this point is equal to another point within a small tolerance.
     *
     * @param other the other point to compare
     * @return true if the points are equal within the tolerance, false otherwise
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.x) < EPSILON && Math.abs(this.y - other.y) < EPSILON);
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }


}
