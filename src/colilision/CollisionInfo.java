//Yair Tabak 322593906
package colilision;
import geometry.Point;

/**
 * colilision.CollisionInfo represents information about a collision between objects.
 * It encapsulates the collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private final Point collisionP;
    private final Collidable collisionObj;

    /**
     * Constructs a colilision.CollisionInfo object with the specified collision point and collidable object.
     *
     * @param p The point at which the collision occurs.
     * @param c The collidable object involved in the collision.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionP = p;
        this.collisionObj = c;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return this.collisionP;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return The collidable object.
     */
    public Collidable collisionObject() {
        return this.collisionObj;
    }
}
