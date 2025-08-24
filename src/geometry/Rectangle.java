
package geometry;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a rectangle in 2D space.
 */
public class Rectangle {
    private Point leftBottom;
    private Point leftUp;
    private Point rightBottom;
    private Point rightUp;
    private Color color;

    /**
     * Constructs a new rectangle with an upper-left point, width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.leftUp = new Point(upperLeft.getX(), upperLeft.getY());
        this.leftBottom = new Point(upperLeft.getX(), upperLeft.getY() - height);
        this.rightUp = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.rightBottom = new Point(upperLeft.getX() + width, upperLeft.getY() - height);
    }

    /**
     * Constructs a new rectangle with an upper-left point, width, height, and color.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param c         the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color c) {
        this.leftUp = new Point(upperLeft.getX(), upperLeft.getY());
        this.leftBottom = new Point(upperLeft.getX(), upperLeft.getY() - height);
        this.rightUp = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.rightBottom = new Point(upperLeft.getX() + width, upperLeft.getY() - height);
        this.color = c;
    }

    /**
     * Constructs a new geometry.Rectangle with specified left-bottom and right-up points.
     *
     * @param leftBottom the left-bottom point of the rectangle
     * @param rightUp    the right-up point of the rectangle
     */
    public Rectangle(Point leftBottom, Point rightUp) {
        this.leftBottom = leftBottom;
        this.leftUp = new Point(leftBottom.getX(), rightUp.getY());
        this.rightBottom = new Point(rightUp.getX(), leftBottom.getY());
        this.rightUp = rightUp;
        this.color = Color.WHITE;
    }

    /**
     * Returns the left-bottom point of this rectangle.
     *
     * @return the left-bottom point
     */
    public Point getLeftBottom() {
        return this.leftBottom;
    }

    /**
     * Returns the upper-left point of this rectangle.
     *
     * @return the upper-left point
     */
    public Point getUpperLeft() {
        return this.leftUp;
    }

    /**
     * Returns the right-bottom point of this rectangle.
     *
     * @return the right-bottom point
     */
    public Point getRightBottom() {
        return this.rightBottom;
    }

    /**
     * Returns the right-up point of this rectangle.
     *
     * @return the right-up point
     */
    public Point getRightUp() {
        return this.rightUp;
    }

    /**
     * Returns the width of this rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.rightBottom.getX() - this.leftBottom.getX();
    }

    /**
     * Returns the height of this rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.rightUp.getY() - this.rightBottom.getY();
    }

    /**
     * Returns the left side of this rectangle as a Line object.
     *
     * @return the left side of the rectangle
     */
    public Line getLeftSide() {
        return new Line(leftBottom, leftUp);
    }

    /**
     * Returns the right side of this rectangle as a Line object.
     *
     * @return the right side of the rectangle
     */
    public Line getRightSide() {
        return new Line(rightBottom, rightUp);
    }

    /**
     * Returns the upper side of this rectangle as a Line object.
     *
     * @return the upper side of the rectangle
     */
    public Line getUpperSide() {
        return new Line(rightUp, leftUp);
    }

    /**
     * Returns the bottom side of this rectangle as a Line object.
     *
     * @return the bottom side of the rectangle
     */
    public Line getDownSide() {
        return new Line(leftBottom, rightBottom);
    }

    /**
     * Returns a list of all sides of this rectangle as Line objects.
     *
     * @return a list containing all sides of the rectangle
     */
    public List<Line> getRectSides() {
        List<Line> list = new ArrayList<>();
        list.add(getDownSide());
        list.add(getLeftSide());
        list.add(getRightSide());
        list.add(getUpperSide());
        return list;
    }

    /**
     * Returns the color of this rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of this rectangle.
     *
     * @param c the new color of the rectangle
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Finds intersection points of this rectangle with a given line.
     *
     * @param line the line to find intersection points with
     * @return a list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Line> list = getRectSides();
        List<Point> intersections = new ArrayList<>();
        for (Line l : list) {
            if (line.isIntersecting(l)) {
                Point intersection = line.intersectionWith(l);
                if (intersection != null) {
                    intersections.add(intersection);
                }
            }
        }
        return intersections;
    }

    /**
     * Draws this rectangle on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) Math.round(leftBottom.getX());
        int y = (int) Math.round(leftBottom.getY()); // Use leftBottom for y-coordinate
        int width = (int) Math.round(getWidth());
        int height = (int) Math.round(getHeight());
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, height);
    }
}
