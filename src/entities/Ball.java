
package entities;
import biuoop.DrawSurface;
import colilision.CollisionInfo;
import collections.GameEnvironment;
import game.Game;
import geometry.Line;
import geometry.Point;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a ball with a center point, radius, color, velocity, and boundary constraints.
 * The ball can move within a specified rectangular area and handle collisions with the boundaries and
 * other rectangular obstacles.
 */
public class Ball implements Sprite, HitNotifier {
    private static final double EPSILON = 0.0001; // Small constant for floating point comparison
    private Point center; // Center point of the ball
    private int r; // Radius of the ball
    private java.awt.Color color; // Color of the ball
    private Velocity v; // entities.Velocity of the ball
    private GameEnvironment env;
    private List<HitListener> hitListeners;

    /**
     * Constructor to create a ball with a center point, radius, and color.
     *
     * @param center Center point of the ball
     * @param r      Radius of the ball
     * @param color  Color of the ball
     * @param g      Game environment of the ball
     */
    public Ball(Point center, int r, GameEnvironment g, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.env = g;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor to create a ball with x, y coordinates, radius, and color.
     *
     * @param x     X coordinate of the ball
     * @param y     Y coordinate of the ball
     * @param r     Radius of the ball
     * @param color Color of the ball
     * @param g     Game environment of the ball
     */
    public Ball(double x, double y, int r, GameEnvironment g, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.env = g;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor to create a ball with x, y coordinates, and radius.
     *
     * @param x X coordinate of the ball
     * @param y Y coordinate of the ball
     * @param r Radius of the ball
     */
    public Ball(double x, double y, int r) {
        this.center = new Point(x, y);
        this.r = r;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Helper method to generate a random x-coordinate within specified width limit.
     *
     * @param widthLim The maximum width limit for the random x-coordinate
     * @return A random x-coordinate within the specified width limit
     */
    private static int generateRandomX(int widthLim) {
        Random rand = new Random();
        return rand.nextInt(widthLim);
    }

    /**
     * Helper method to generate a random y-coordinate within specified height limit.
     *
     * @param heightLim The maximum height limit for the random y-coordinate
     * @return A random y-coordinate within the specified height limit
     */
    private static int generateRandomY(int heightLim) {
        Random rand = new Random();
        return rand.nextInt(heightLim);
    }

    /**
     * Helper method to generate a random color.
     *
     * @return A randomly generated color
     */
    private static Color generateRandomColor() {
        Random rand = new Random();
        int rVal = rand.nextInt(256);
        int gVal = rand.nextInt(256);
        int bVal = rand.nextInt(256);
        return new Color(rVal, gVal, bVal);
    }

    // Accessor methods

    /**
     * Gets the x-coordinate of the ball's center.
     *
     * @return The x-coordinate of the ball's center
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * Gets the y-coordinate of the ball's center.
     *
     * @return The y-coordinate of the ball's center
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * Gets the size (radius) of the ball.
     *
     * @return The size (radius) of the ball
     */
    public int getSize() {
        return this.r;
    }


    /**
     * Gets the velocity of the ball.
     *
     * @return The velocity of the ball
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * Gets the color of the ball.
     *
     * @return The color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    // Mutator methods

    /**
     * Sets the size (radius) of the ball.
     *
     * @param size The new size (radius) of the ball
     */
    public void setSize(int size) {
        this.r = size;
    }


    /**
     * Sets the velocity of the ball.
     *
     * @param v The new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.v = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * Sets the velocity of the ball using dx and dy components.
     *
     * @param dx The change in x-coordinate
     * @param dy The change in y-coordinate
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Sets the color of the ball.
     *
     * @param randomColor The new color of the ball
     */
    public void setColor(Color randomColor) {
        this.color = randomColor;
    }

    /**
     * Moves the ball one step, handling collisions with boundaries and rectangles.
     */
    public void moveOneStep() {
        // Apply collision handling before moving
        Point next = this.v.applyToPoint(this.center);
        Line path = new Line(this.center, next);
        CollisionInfo info = env.getClosestCollision(path);
        if (info == null || info.collisionPoint() == null || info.collisionObject() == null) {
            this.center = next;
        } else {
            this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
            this.center = new Point(this.center.getX() + 0.9 * this.v.getDx(),
                    this.center.getY() + 0.9 * this.v.getDy());
        }
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface The surface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) Math.round(center.getX()), (int) Math.round(center.getY()), r);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds this sprite to the specified game.
     * This method adds the current sprite object to the given game by invoking
     * {@link Game#addSprite(Sprite)} method with {@code this} as the argument.
     *
     * @param g The game to which this sprite will be added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes this block from the given game by unregistering it as a sprite object.
     *
     * @param game
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * Notifies all listeners that a hit occurred.
     *
     * @param hitter The ball that hit the block.
     *
     *@param beingHit The block that being hit.
     */
    public void notifyHit(Block beingHit, Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(beingHit, hitter);
        }
    }
}
