
package collections;
import biuoop.DrawSurface;
import entities.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The collections.SpriteCollection class manages a collection of sprites.
 * It allows adding sprites, and provides methods to update and draw all sprites in the collection.
 */
public class SpriteCollection {
    private ArrayList<Sprite> list;

    /**
     * Constructs a new collections.SpriteCollection.
     * Initializes an empty list to hold sprites.
     */
    public SpriteCollection() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.list.add(s);
    }
    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection.
     * This method is typically called once per game loop iteration to update sprite states.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the hitListeners before iterating over them.
        List<Sprite> copy = new ArrayList<>(list);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all sprites in the collection.
     * This method is typically called once per game loop iteration to draw sprites on a DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : list) {
            s.drawOn(d);
        }
    }
}
