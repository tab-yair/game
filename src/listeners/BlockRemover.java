
package listeners;

import entities.Ball;
import entities.Block;
import game.Game;
import utils.Counter;

import java.util.ArrayList;

/**
 * The listeners.BlockRemover class is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */

public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;
    private ArrayList<Block> unbreakableBlocks;

    /**
     * Constructs a new listeners.BlockRemover with the specified game and counter.
     *
     * @param game
     * @param remainingBlocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Processes the collision of the ball with an unbreakable block.
     *
     * @param list
     */
    public void processUnremovedBlockCollision(ArrayList<Block> list) {
        unbreakableBlocks = list;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!unbreakableBlocks.contains(beingHit)) {
            if (!beingHit.ballColorMatch(hitter)) {
                beingHit.removeFromGame(game);
                beingHit.removeHitListener(this);
                remainingBlocks.decrease(1);
                hitter.setColor(beingHit.getCollisionRectangle().getColor());
            }
        }
    }
}
