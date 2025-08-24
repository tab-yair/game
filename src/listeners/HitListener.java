
package listeners;
import entities.Ball;
import entities.Block;

/**
 * The listeners.HitListener interface indicates that objects that
 * implement it can be notified when they are hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit
     * @param hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}
