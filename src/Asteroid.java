/**
 * This class represents an asteroid that comes in 3 predetermined sizes.
 * 
 * Honor Code: All work is my own.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/14/2023
 */

public class Asteroid extends Enemy {
    public static final int ASTEROID_SPEED = 1;

    /**
     * Construct an asteroid given a random asteroid size.
     * 
     * @param size A randomly-generated predetermined asteroid
     */
    public Asteroid(AsteroidSize size) {
        super(ASTEROID_SPEED, size.getRadius(), size.getPoints());
    }

    /**
     * Draw the asteroid onto the canvas.
     */
    public void draw() {
        StdDraw.circle(this.getPose().getX(), this.getPose().getY(), this.getRadius());
    }
}