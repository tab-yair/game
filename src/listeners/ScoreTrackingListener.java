
package listeners;

import entities.Ball;
import entities.Block;
import utils.Counter;

import java.util.ArrayList;


/**
 * The ScoreTrackingListener class is a listener that tracks the score of the player.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private ArrayList<Block> unbreakableBlocks;

    /**
     * Constructs a new ScoreTrackingListener with the specified counter.
     * @param scoreCounter The counter to track the score with.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Processes the collision of the ball with an unbreakable block.
     * @param list
     */
    public void processUnscoredBlockCollision(ArrayList<Block> list) {
        unbreakableBlocks = list;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter) && !unbreakableBlocks.contains(beingHit)) {
            this.currentScore.increase(5);
        }
    }
}
