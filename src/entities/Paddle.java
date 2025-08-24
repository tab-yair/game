// Yair Tabak 322593906
package entities;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import colilision.Collidable;
import game.Game;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The entities.Paddle class represents the paddle in the game.
 * The paddle can move left and right based on user input and can collide with other objects.
 */
public class Paddle implements Sprite, Collidable {
    private static final double EPSILON = 0.0001; // Small constant for floating point comparison
    private final int paddleWidth = 100;
    private final int paddleHeight = 20;
    private final int paddleSpeed = 5;
    private int leftSide;
    private int rightSide;
    private final Color color = Color.YELLOW;

    private Rectangle paddle;
    private KeyboardSensor keyboard;

    /**
     * Constructs a new entities.Paddle with the specified screen dimensions, boundaries, and keyboard sensor.
     *
     * @param screenWidth  the width of the screen
     * @param screenHeight the height of the screen
     * @param leftSide     the left boundary for the paddle
     * @param rightSide    the right boundary for the paddle
     * @param keyboard     the keyboard sensor to control the paddle
     */
    public Paddle(int screenWidth, int screenHeight, int leftSide, int rightSide, KeyboardSensor keyboard) {
        // Calculate paddle initial position
        int paddleX = (screenWidth - paddleWidth) / 2; // Center the paddle horizontally
        int paddleY = screenHeight - 2 * paddleHeight; // Place the paddle near the bottom of the screen
        // Create the paddle rectangle
        this.paddle = new Rectangle(new Point(paddleX, paddleY), paddleWidth, paddleHeight, Color.YELLOW);
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.keyboard = keyboard;
    }

    /**
     * Moves the paddle to the left.
     * If the paddle goes beyond the left boundary, it wraps around to the right side.
     */
    public void moveLeft() {
        // Calculate the new position of the paddle
        double currentX = paddle.getUpperLeft().getX();
        double newX = currentX - paddleSpeed;

        // Ensure the paddle stays within bounds
        if (newX < leftSide) {
            newX = rightSide - paddleWidth; // Wrap around to the right side
        }

        // Update the paddle's position
        paddle = new Rectangle(new Point(newX, paddle.getUpperLeft().getY()), paddleWidth, paddleHeight, Color.YELLOW);
    }

    /**
     * Moves the paddle to the right.
     * If the paddle goes beyond the right boundary, it wraps around to the left side.
     */
    public void moveRight() {
        // Calculate the new position of the paddle
        double currentX = paddle.getUpperLeft().getX();
        double newX = currentX + paddleSpeed;

        // Ensure the paddle stays within bounds
        if (newX > rightSide - paddleWidth) {
            newX = leftSide; // Wrap around to the left side
        }

        // Update the paddle's position
        paddle = new Rectangle(new Point(newX, paddle.getUpperLeft().getY()), paddleWidth, paddleHeight, Color.YELLOW);
    }

    /**
     * Updates the paddle's position based on user input.
     * If the left key is pressed, the paddle moves left. If the right key is pressed, the paddle moves right.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    /**
     * Returns the rectangle representing the paddle's collision area.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Adds the paddle to the game as both a entities.Sprite and a Collidable.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Handles the collision with the paddle.
     * Changes the ball's velocity based on the collision point and region on the paddle.
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleRegion = paddleWidth / 5.0;
        double x = collisionPoint.getX() - paddle.getUpperLeft().getX();
        double angle;

        if (x < paddleRegion + EPSILON) {
            angle = Math.toRadians(300);
        } else if (x < 2 * paddleRegion + EPSILON) {
            angle = Math.toRadians(330);
        } else if (x < 3 * paddleRegion + EPSILON) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (x < 4 * paddleRegion + EPSILON) {
            angle = Math.toRadians(30);
        } else {
            angle = Math.toRadians(60);
        }
        return Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed());
    }
}
