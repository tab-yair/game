
package collections;
import colilision.Collidable;
import colilision.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;

/**
 * The GameEnvironment class manages collidable objects and provides collision detection functionality.
 * It keeps track of all collidable objects and can find the closest collision point to a given trajectory.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables; // List of collidable objects in the environment

    /**
     * Constructs a new GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c The collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Finds the closest collision point between a trajectory and any collidable object in the environment.
     *
     * @param trajectory The trajectory (line) to check for collisions.
     * @return A colilision.CollisionInfo object containing information about the closest
     * collision point and the collidable object involved.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Lists to store intersection points and corresponding collidable objects
        ArrayList<Point> intersectionPoints = new ArrayList<>();
        ArrayList<Collidable> intersectionObj = new ArrayList<>();

        // Find intersection points with all collidable objects
        for (Collidable c : collidables) {
            if (c != null) {
                Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                intersectionPoints.add(intersection);
                intersectionObj.add(c);
            }
        }

        // Determine the closest collision point and collidable object
        Point closestP = null;
        Collidable closestO = null;
        double distance = Double.MAX_VALUE;

        for (Point p : intersectionPoints) {
            if (p == null) {
                continue;
            }
            if (p.distance(trajectory.start()) < distance) {
                distance = p.distance(trajectory.start());
                closestP = p;
                closestO = intersectionObj.get(intersectionPoints.indexOf(p));
            }
        }

        return new CollisionInfo(closestP, closestO);
    }

    /**
     * Returns the list of collidable objects in the game environment.
     *
     * @return The list of collidable objects.
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }
}
