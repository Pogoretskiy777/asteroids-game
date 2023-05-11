/**
 * A class that represents a saucer in the game.
 * 
 * Honor Code: This is my own work.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/14/2023
 */

public class Saucer extends Enemy {
    public static final int HALF_WIDTH = 10;
    public static final int HALF_HEIGHT = 5;
    public static final int SAUCER_SPEED = 2;
    public static final int SAUCER_POINTS = 400;
    public static final double SPAWN_PROB = 0.002;
    public static final double TURN_PROB = 0.05;

    /**
     * Construct a saucer given its speed, radius, and point value.
     */
    public Saucer() {
        super(SAUCER_SPEED, HALF_WIDTH, SAUCER_POINTS);
    }

    /**
     * For every frame of the game, move the saucer according to its new position. There is a 5% chance of the heading
     * of the saucer randomly changing.
     */
    public void update() {
        Pose newPose = this.pose.move(this.velocity);
        this.pose = newPose;
        if (this.pose.getX() < 0 - HALF_WIDTH || this.pose.getX() > GameDriver.SCREEN_WIDTH + HALF_WIDTH
                || this.pose.getY() < 0 - HALF_HEIGHT || this.pose.getY() > GameDriver.SCREEN_HEIGHT + HALF_HEIGHT) {
            this.setDestroyed(true);
        }
        int randNum = GameDriver.GENERATOR.nextInt(0, 19);
        if (randNum == 0) {
            Vector2D newVelocity = this.velocity.newHeading(AsteroidsGame.randomHeading());
            this.velocity = newVelocity;
        }
    }

    /**
     * Draw the saucer onto the canvas as a rectangle.
     */
    public void draw() {
        StdDraw.rectangle(this.pose.getX(), this.pose.getY(), HALF_WIDTH, HALF_HEIGHT);
    }
}