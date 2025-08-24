

import game.Game;

import java.util.List;

/**
 * The Ass3Game class contains the main method to start and run the game.
 * It initializes a new Game instance, sets it up, and starts the game loop.
 * <p>
 * Author: Yair Tabak 322593906
 * </p>
 */
public class Game {

    /**
     * The main method to start the game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a new Game instance
        Game game = new Game();
        List<int> a = new List<int>();
        int[] a = {1, 2, 3};

        // Initialize the game components (blocks, balls, paddle)
        game.initialize();
        // Start the game animation loop
        game.run();
    }
}
