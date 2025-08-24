// Yair Tabak 322593906
package geometry;
import java.util.List;
import java.util.Random;

/**
 * This class represents a line segment in a two-dimensional plane. It provides methods for calculating various
 * properties of the line segment, such as length, slope, midpoint, intersection with other lines, and more.
 */

public class Line {
    // Tolerance for comparing floating-point numbers
    private static final double EPSILON = 0.0001;
    private Point start;
    private Point end;

    /**
     * Constructs a line segment with the specified start and end points.
     *
     * @param start The start point of the line segment.
     * @param end   The end point of the line segment.
     * @throws IllegalArgumentException If either start or end points are null, or if start and end points are the same.
     */
    public Line(Point start, Point end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Points cannot be null.");
        }
        if (start.equals(end)) {
            throw new IllegalArgumentException("Start and end points cannot be the same.");
        }
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructs a line segment with the specified coordinates for start and end points.
     *
     * @param x1 The x-coordinate of the start point.
     * @param y1 The y-coordinate of the start point.
     * @param x2 The x-coordinate of the end point.
     * @param y2 The y-coordinate of the end point.
     * @throws IllegalArgumentException If start and end points are the same.
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (Math.abs(x1 - x2) < EPSILON && Math.abs(y1 - y2) < EPSILON) {
            throw new IllegalArgumentException("Start and end points cannot be the same.");
        }
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Constructs a line segment with random start and end points.
     */
    public Line() {
        Random rand = new Random();
        double x1 = rand.nextDouble();
        double x2 = rand.nextDouble();
        double y1 = rand.nextDouble();
        double y2 = rand.nextDouble();
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return The length of the line segment.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Calculates the midpoint of the line segment.
     *
     * @return The midpoint of the line segment.
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * Gets the start point of the line segment.
     *
     * @return The start point of the line segment.
     */
    public Point start() {
        return start;
    }

    /**
     * Gets the end point of the line segment.
     *
     * @return The end point of the line segment.
     */
    public Point end() {
        return end;
    }

    /**
     * Checks if the line segment is vertical.
     *
     * @return True if the line segment is vertical, false otherwise.
     */
    public boolean isVertical() {
        return Math.abs(start.getX() - end.getX()) < EPSILON;
    }

    /**
     * Calculates the slope of the line segment.
     *
     * @return The slope of the line segment.
     */
    public double slopeLine() {
        if (isVertical()) {
            return Double.POSITIVE_INFINITY;
        }
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    /**
     * Converts an angle to its corresponding slope.
     *
     * @param angle The angle in radians.
     * @return The slope corresponding to the angle.
     */
    public static double convertAngleToSlope(double angle) {
        if (Math.abs(angle - (Math.PI / 2)) < EPSILON || Math.abs(angle - 3 * (Math.PI / 2)) < EPSILON) {
            return Double.POSITIVE_INFINITY;
        }
        return Math.tan(angle);
    }

    /**
     * Calculates the y-intercept of the line segment.
     *
     * @return The y-intercept of the line segment.
     */
    public double yIntercept() {
        return (end.getY() - (this.slopeLine() * end.getX()));
    }

    /**
     * Finds the missing x-coordinate given a point, y-coordinate, and angle.
     *
     * @param start The starting point.
     * @param yEnd  The y-coordinate of the end point.
     * @param angle The angle in radians.
     * @return The missing x-coordinate.
     */
    public static double findMissingX(Point start, double yEnd, double angle) {
        double slope = Line.convertAngleToSlope(angle);
        if (slope == Double.POSITIVE_INFINITY) {
            return start.getX();
        } else if (Math.abs(slope - 0) < EPSILON) {
            return Double.NaN;
        }
        return (start.getX() - (start.getY() - yEnd) / slope);
    }

    /**
     * Finds the missing y-coordinate given a point, x-coordinate, and angle.
     *
     * @param start The starting point.
     * @param xEnd  The x-coordinate of the end point.
     * @param angle The angle in radians.
     * @return The missing y-coordinate.
     */
    public static double findMissingY(Point start, double xEnd, double angle) {
        double slope = Line.convertAngleToSlope(angle);
        if (Math.abs(slope - 0) < EPSILON) {
            return start.getY();
        }
        return (start.getY() - slope * (start.getX() - xEnd));
    }

    /**
     * Finds the missing y-intercept given a point and angle.
     *
     * @param start The starting point.
     * @param angle The angle in radians.
     * @return The missing y-intercept.
     */
    public static double findMissingYIntersection(Point start, double angle) {
        return start.getY() - Line.convertAngleToSlope(angle) * start.getX();
    }

    /**
     * Checks if two line segments are collinear.
     *
     * @param other The other line segment.
     * @return True if the line segments are collinear, false otherwise.
     */
    public boolean areCollinearSegments(Line other) {
        return (Math.abs(this.slopeLine() - other.slopeLine()) < EPSILON)
                && (Math.abs(this.yIntercept() - other.yIntercept()) < EPSILON);
    }

    /**
     * Checks if a point lies on a line segment given the angle.
     *
     * @param start The starting point of the line segment.
     * @param p     The point to check.
     * @param angle The angle in radians.
     * @return True if the point lies on the line segment, false otherwise.
     */
    public static boolean isPointOnLine(Point start, Point p, double angle) {
        double slope = Line.convertAngleToSlope(angle);
        double yIntersection = Line.findMissingYIntersection(p, angle);
        return (Math.abs(p.getY() - (yIntersection + slope * p.getX())) < EPSILON);
    }

    /**
     * Checks if a point lies on the current line segment.
     *
     * @param p The point to check.
     * @return True if the point lies on the line segment, false otherwise.
     */
    public boolean isPointOnLine(Point p) {
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxX = Math.max(this.start.getX(), end.getX());
        double minY = Math.min(this.start().getY(), this.end().getY());
        double maxY = Math.max(this.start().getY(), this.end().getY());

        return p.getX() >= minX - EPSILON && p.getX() <= maxX + EPSILON
                && p.getY() >= minY - EPSILON && p.getY() <= maxY + EPSILON;
    }

    /**
     * Calculates the intersection point of two vertical line segments.
     *
     * @param other The other vertical line segment.
     * @return The intersection point of the two vertical line segments, or null if they do not intersect.
     */
    public Point intersectionOfVerticals(Line other) {
        double minY1 = Math.min(this.start.getY(), this.end.getY());
        double maxY1 = Math.max(this.start.getY(), this.end.getY());
        double minY2 = Math.min(other.start.getY(), other.end.getY());
        double maxY2 = Math.max(other.start.getY(), other.end.getY());

        if (Math.abs(maxY1 - minY2) < EPSILON) {
            return new Point(other.start.getX(), maxY1);
        } else if (Math.abs(minY1 - maxY2) < EPSILON) {
            return new Point(other.start.getX(), minY1);
        } else {
            return null;
        }
    }

    /**
     * Finds the intersection point of two non-vertical line segments.
     *
     * @param other The other line segment.
     * @return The intersection point of the two line segments, or null if they do not intersect.
     */
    private Point findOverlapIntersection(Line other) {
        Point[] points = {this.start, this.end, other.start, other.end};
        Point overlapPoint = null;

        for (Point p : points) {
            if (this.isPointOnLine(p) && other.isPointOnLine(p)) {
                if (overlapPoint == null) {
                    overlapPoint = p;
                } else {
                    return null; // More than one overlap point found
                }
            }
        }

        return overlapPoint; // Return the single overlap point, or null if none was found
    }

    /**
     * Finds the intersection point of two line segments.
     *
     * @param other The other line segment.
     * @return The intersection point of the two line segments, or null if they do not intersect.
     */
    public Point intersectionWith(Line other) {
        if (this.isVertical() && other.isVertical()) {
            if (Math.abs(this.start.getX() - other.start.getX()) < EPSILON) {
                return intersectionOfVerticals(other);
            } else {
                return null;
            }
        }

        double x, y;
        if (this.isVertical() && !other.isVertical()) {
            x = this.start.getX();
            y = other.slopeLine() * x + other.yIntercept();
        } else if (other.isVertical() && !this.isVertical()) {
            x = other.start.getX();
            y = this.slopeLine() * x + this.yIntercept();
        } else {
            double slope1 = this.slopeLine();
            double slope2 = other.slopeLine();
            if (Math.abs(slope1 - slope2) < EPSILON) {
                if (Math.abs(this.yIntercept() - other.yIntercept()) < EPSILON) {
                    return findOverlapIntersection(other);
                }
                return null; // Parallel and collinear or distinct lines
            }
            x = (other.yIntercept() - this.yIntercept()) / (slope1 - slope2);
            y = slope1 * x + this.yIntercept();
        }

        Point intersection = new Point(x, y);
        if (this.isPointOnLine(intersection) && other.isPointOnLine(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * Checks if the current line segment intersects with another line segment.
     *
     * @param other The other line segment.
     * @return True if the line segments intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.areCollinearSegments(other)) {
            return (this.isPointOnLine(other.start) || this.isPointOnLine(other.end)
                    || other.isPointOnLine(this.start) || other.isPointOnLine(this.end));
        }
        // Return true if the collinear intersection is in both lines
        return (this.intersectionWith(other) != null);
    }

    /**
     * Checks if the current line segment intersects with two other line segments.
     *
     * @param other1 The first other line segment.
     * @param other2 The second other line segment.
     * @return True if the line segments intersect, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Checks if the current line segment is equal to another line segment.
     *
     * @param other The other line segment to compare.
     * @return True if the line segments are equal, false
     * otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * Finds the closest intersection point between this line and the given rectangle.
     *
     * @param rect The rectangle to find intersections with.
     * @return The closest intersection point to the start of this line, or null if no intersection exists.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        Point closestP = null;
        double distance = Double.MAX_VALUE;

        for (Point p : list) {
            if (p == null) {
                continue;
            }
            if (this.isPointOnLine(p) && start.distance(p) < distance) {
                closestP = p;
                distance = start.distance(p);
            }
        }
        return closestP;
    }

}