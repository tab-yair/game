//Yair Tabak 322593906
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;
import colilision.Collidable;
import collections.GameEnvironment;
import collections.SpriteCollection;
import entities.Ball;
import entities.Block;
import entities.Paddle;
import entities.Sprite;
import entities.Velocity;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import ui.ScoreIndicator;
import utils.Counter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Game class represents the main game engine that manages sprites, collidables,
 * and game elements such as the paddle, balls, blocks, and frames.
 */
public class Game {
    private final int screenWidth = 800;     // Screen width
    private final int screenHeight = 620;    // Screen height
    private final int frameSize = 25;        // Size of the frame blocks
    private final int ballNum = 3;           // Number of balls
    private final int radius = 10;            // Radius of each ball
    private final int blockHeight = 25;      // Height of blocks
    private final int blockWidth = 50;       // Width of blocks

    /**
     * Enum defining colors available for blocks in the game.
     */
    public enum Colors {
        GRAY(Color.GRAY),
        RED(Color.RED),
        YELLOW(Color.YELLOW),
        BLUE(Color.BLUE),
        PINK(Color.PINK),
        GREEN(Color.GREEN);

        private final Color color;

        Colors(Color color) {
            this.color = color;
        }

        /**
         * Returns the color associated with this enum constant.
         *
         * @return The Color object representing the color of this enum constant.
         */
        public Color getColor() {
            return this.color;
        }
    }

    private GUI gui;                        // GUI object for drawing
    private Paddle paddle;                  // The game paddle
    private Block[] frames;                 // Array of frame blocks
    private ArrayList<Block> obstacles;     // List of obstacle blocks
    private ArrayList<Ball> balls;          // List of balls in the game
    private SpriteCollection sprites;       // Collection of game sprites
    private GameEnvironment environment;     // Game environment for collisions
    private Rectangle screen; // Game screen rectangle
    private Block deathRegion;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;

    /**
     * Constructs a new Game instance. Initializes GUI, sprites, game environment, and game elements.
     */
    public Game() {
        this.balls = new ArrayList<>();
        this.frames = new Block[4];
        this.obstacles = new ArrayList<>();
        this.screen = new Rectangle(new Point(0, screenHeight), screenWidth, screenHeight);
        this.screen.setColor(Color.blue.darker());
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.gui = new GUI("Game", screenWidth, screenHeight);
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.scoreCounter = new Counter();
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s The sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Sets up the frame blocks around the game screen.
     */
    public void setFrames() {
        // Top frame block
        frames[0] = new Block(new Point(0, screenHeight + 2 * frameSize), screenWidth, frameSize, Color.gray);
        this.frames[0].addToGame(this);
        this.deathRegion = frames[0];

        // Bottom frame block
        frames[1] = new Block(new Point(0, 2 * frameSize), screenWidth, frameSize, Color.gray);
        this.frames[1].addToGame(this);

        // Left frame block
        frames[2] = new Block(new Point(0, screenHeight), frameSize,
                screenHeight - frameSize, Color.gray);
        this.frames[2].addToGame(this);
        // Right frame block
        frames[3] = new Block(new Point(screenWidth - frameSize, screenHeight), frameSize,
                screenHeight - frameSize, Color.gray);
        this.frames[3].addToGame(this);
    }

    /**
     * Creates lines of blocks on the game screen.
     */
    public void createBlocks() {
        double startY = screenHeight / 4; // Start from one-fourth down the screen height

        for (int i = 0; i < 6; i++) {
            Colors color = Colors.values()[i % Colors.values().length]; // Get the color from the enum
            for (int j = 0; j < 12 - i; j++) {
                double x = screenWidth - frameSize - (j + 1) * blockWidth; // Start from the right
                double y = startY + i * blockHeight; // Increment the y position for each line
                Block b = new Block(new Point(x, y), blockWidth, blockHeight, color.getColor());
                this.obstacles.add(b);
                b.addToGame(this);
                blockCounter.increase(1);
            }
        }
    }

    /**
     * Creates and initializes the paddle object.
     */
    public void createPaddle() {
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        this.paddle = new Paddle(screenWidth, screenHeight, frameSize, screenWidth - frameSize, keyboard);
        paddle.addToGame(this);
    }

    /**
     * Sets up the balls in the game environment.
     */
    public void setBalls() {
        for (int i = 0; i < ballNum; i++) {
            int x = screenWidth / 2;
            int y = (screenHeight - 7 * frameSize);
            Ball newB = new Ball(new Point(x, y), radius, this.environment, Color.ORANGE);

            newB.setVelocity(Velocity.fromAngleAndSpeed(i * Math.PI / 3 - Math.PI / 3, 3.5));
            newB.addToGame(this);
            this.balls.add(newB);
            this.ballCounter.increase(1);
        }
    }

    /**
     * Adds a listeners.BlockRemover object to the game.
     *
     * @param remover The listeners.BlockRemover object to add.
     */
    private void addBlockRemover(BlockRemover remover) {
        for (Block b : this.obstacles) {
            b.addHitListener(remover);
        }
    }

    private void addBallRemover(BallRemover remover) {
        for (Ball b : this.balls) {
            b.addHitListener(remover);
        }
    }

    private void addScoreTracker(ScoreTrackingListener tracker) {
        for (Ball b : this.balls) {
            b.addHitListener(tracker);
        }
    }

    /**
     * Updates the counters for blocks, balls, and score.
     */
    public void updateCounters() {
        BlockRemover b = new BlockRemover(this, this.blockCounter);
        b.processUnremovedBlockCollision(new ArrayList<>(Arrays.asList(this.frames)));
        addBlockRemover(b);
        addBallRemover(new BallRemover(this, this.deathRegion, this.ballCounter));
        ScoreTrackingListener c = new ScoreTrackingListener(scoreCounter);
        c.processUnscoredBlockCollision(new ArrayList<>(Arrays.asList(this.frames)));
        addScoreTracker(c);
    }


    /**
     * Initializes the score board.
     */
    private void createScoreBoard() {
        ScoreIndicator scoreIndicator =
                new ScoreIndicator(new Rectangle(new Point(0, 0), screenWidth, frameSize, Color.white),
                        scoreCounter);
        scoreIndicator.addToGame(this);
    }


    /**
     * Initializes the game by setting up frames, lines of blocks, paddle, and balls.
     */
    public void initialize() {
        setFrames();
        createBlocks();
        createPaddle();
        setBalls();
        updateCounters();
        createScoreBoard();
    }

    /**
     * Runs the game loop, handling animation and updates.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            if (ballCounter.getValue() <= 0 || blockCounter.getValue() <= 0) {
                if (blockCounter.getValue() <= 0) {
                    this.scoreCounter.increase(100);
                }
                gui.close();
            }
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.screen.drawOn(d);
            this.sprites.notifyAllTimePassed();
            this.sprites.drawAllOn(d);
            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Removes a collidable from the game environment.
     *
     * @param c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}


