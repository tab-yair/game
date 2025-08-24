
package ui;

import biuoop.DrawSurface;
import entities.Sprite;
import game.Game;
import geometry.Rectangle;
import utils.Counter;

import java.awt.Color;
/**
 * The ScoreIndicator class is a sprite that displays the player's score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private final Rectangle rectangle;
    private final Counter score;

    /**
     * Constructs a new ScoreIndicator with the specified rectangle and counter.
     * @param rect
     * @param c
     */
    public ScoreIndicator(Rectangle rect, Counter c) {
        this.rectangle = rect;
        this.score = c;
    }


    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());

        surface.setColor(Color.BLACK);
        surface.drawText((int) (rectangle.getUpperLeft().getX() + rectangle.getWidth() / 2),
                (int) (rectangle.getUpperLeft().getY() + rectangle.getHeight() * 9 / 10),
                String.format("Score: %d", score.getValue()), 20);
    }


    @Override
    public void timePassed() {

    }

    /**
     * Adding the indicator to the sprites list.
     *
     * @param game a game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
