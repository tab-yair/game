//Yair Tabak 322593906
package colilision;

import entities.Ball;
import entities.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * The Collidable interface represents objects that can collide with other objects.
 * Implementing classes must provide methods to retrieve collision rectangles
 * and handle collisions with other objects.
 */
public interface Collidable {

    /**
     * Returns the collision rectangle representing this collidable object.
     *
     * @return The collision rectangle of this object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Handles a collision with this collidable object.
     * Adjusts the velocity of an object that collides with this object based on collision point and current velocity.
     *
     * @param hitter           The ball that hit this object.
     * @param collisionPoint   The point of collision with this object.
     * @param currentVelocity  The current velocity of the colliding object.
     * @return The new velocity of the colliding object after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
