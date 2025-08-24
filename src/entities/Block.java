
package entities;
import biuoop.DrawSurface;
import colilision.Collidable;
import game.Game;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A entities.Block class that implements both Collidable and entities.Sprite interfaces.
 * Blocks are rectangular objects that can collide with other objects and be drawn on a DrawSurface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private List<HitListener> hitListeners;

    /**
     * Constructs a entities.Block with a specified upper-left corner point, width, and height.
     *
     * @param upperLeft The upper-left corner point of the block.
     * @param width     The width of the block.
     * @param height    The height of the block.
     */
    public Block(Point upperLeft, double width, double height) {
        this.block = new Rectangle(upperLeft, width, height);
    }

    /**
     * Constructs a entities.Block with a specified upper-left corner point, width, height, and color.
     *
     * @param upperLeft The upper-left corner point of the block.
     * @param width     The width of the block.
     * @param height    The height of the block.
     * @param c         The color of the block.
     */
    public Block(Point upperLeft, double width, double height, Color c) {
        this.block = new Rectangle(upperLeft, width, height);
        this.block.setColor(c);
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Returns the collision rectangle representing this block.
     *
     * @return The collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return block;
    }

    /**
     * Handles a collision with the block.
     * Adjusts the velocity of an object that collides with the block based on collision point and current velocity.
     *
     * @param hitter          The ball that hit the block.
     * @param collisionPoint  The point of collision.
     * @param currentVelocity The current velocity of the object colliding with the block.
     * @return The new velocity of the object after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        Velocity newV = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (block.getRightSide().isPointOnLine(collisionPoint) || block.getLeftSide().isPointOnLine(collisionPoint)) {
            hitter.notifyHit(this, hitter);
            newV = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());

            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }

        }
        if (block.getUpperSide().isPointOnLine(collisionPoint) || block.getDownSide().isPointOnLine(collisionPoint)) {
            hitter.notifyHit(this, hitter);
            newV = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }

        }
        return newV;
    }

    /**
     * Performs an action when time passes, although this block doesn't change with time.
     */
    @Override
    public void timePassed() {
        // This block does not perform any action with time passage.
    }

    /**
     * Draws the block on a given DrawSurface.
     *
     * @param d The DrawSurface on which to draw the block.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Fill the block with its color
        block.drawOn(d);
    }

    /**
     * Adds this block to the given game by registering it as both a sprite and a collidable object.
     *
     * @param g The game to which this block will be added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns if the ball's color matches the block's color.
     *
     * @param ball
     * @return true if the ball's color matches the block's color, false otherwise
     */
    public Boolean ballColorMatch(Ball ball) {
        if (this.block.getColor() == null) {
            return false;
        }
        return this.block.getColor().equals(ball.getColor());
    }

    /**
     * Removes this block from the given game by unregistering it as both a sprite and a collidable object.
     *
     * @param game
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    /**
     * Notifies all listeners that a hit occurred.
     * @param hitter The ball that hit the block.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    /**
     * Notifies all listeners that a hit occurred.
     * @param hitter The ball that hit the block.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all listeners that a hit occurred.
     *
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
