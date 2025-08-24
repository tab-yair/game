
package entities;
import biuoop.DrawSurface;

/**
 * The entities.Sprite interface represents an object that can be drawn on a DrawSurface
 * and can update its state over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This allows the sprite to update its state, if necessary.
     */
    void timePassed();
}

