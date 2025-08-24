
package entities;
import geometry.Point;

/**
 * A class representing the velocity of an object in 2D space.
 * entities.Velocity specifies the change in position on the x and the y axes.
 */
public class Velocity {
    private double dx; // The change in position on the x-axis
    private double dy; // The change in position on the y-axis

    /**
     * Constructs a new entities.Velocity with the specified changes in position on the x and y axes.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a new entities.Velocity from an angle and speed.
     *
     * @param angle the angle in radians representing the direction of movement
     * @param speed the speed of movement
     * @return a new entities.Velocity object representing the velocity based on the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle - Math.PI / 2);
        double dy = speed * Math.sin(angle - Math.PI / 2);
        return new Velocity(dx, dy);
    }

    /**
     * Gets the change in position on the x-axis.
     *
     * @return the change in position on the x-axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in position on the y-axis.
     *
     * @return the change in position on the y-axis
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Gets the speed of the velocity.
     *
     * @return the speed of the velocity
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    /**
     * Gets the angle of the velocity.
     *
     * @return the angle of the velocity in radians
     */
    public double getAngle() {
        return Math.atan2(this.dy, this.dx);
    }

    /**
     * Applies this velocity to a given point.
     *
     * @param p the point to which the velocity will be applied
     * @return a new point representing the result of applying the velocity to the given point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
