/**
 * This class represents an enemy that carries an amount of points. It has a random direction and position when drawn on
 * the canvas.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/10/2023
 */
public abstract class Enemy extends GameElement {
    protected int points;

    /**
     * Construct an enemy with the given speed, radius, and points, which then constructs a game element with a random
     * position and heading.
     * 
     * @param speed The speed of the enemy
     * @param radius The collision radius of the enemy
     * @param points The amount of points the player receives when the enemy is killed
     */
    public Enemy(double speed, double radius, int points) {
        super(new Pose(AsteroidsGame.randomXPosition(), AsteroidsGame.randomYPosition(), AsteroidsGame.randomHeading()),
                new Vector2D(AsteroidsGame.randomHeading(), speed), radius);
        this.points = points;
    }

    /**
     * Get the enemy's points.
     * 
     * @return Return points
     */
    public int getPoints() {
        return this.points;
    }

}