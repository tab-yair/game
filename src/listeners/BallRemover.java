//Yair Tabak 322593906
package listeners;

import entities.Ball;
import entities.Block;
import game.Game;
import utils.Counter;

/**
 * The BallRemover class is a listener that removes balls from the game when they hit a specified block.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Block deathRegion;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover with the specified game, block, and counter.
     * @param g The game to remove balls from.
     * @param b The block that removes balls.
     * @param c The counter of remaining balls.
     */
    public BallRemover(Game g, Block b, Counter c) {
        game = g;
        deathRegion = b;
        remainingBalls = c;
    }

    /**
     * Handles a hit event by removing the ball from the game.
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.equals(this.deathRegion)) {
            hitter.removeFromGame(game);
            hitter.removeHitListener(this);
            this.remainingBalls.decrease(1);

        }
    }
}
