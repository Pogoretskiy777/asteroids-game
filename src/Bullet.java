/**
 * This class represents a bullet that shoots from the ship when the space bar is applied. The bullet only exists for 20
 * frames and must shoot in the identical direction of the ship.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/10/2023
 */
public class Bullet extends GameElement {

    public static final double BULLET_RADIUS = 1.5;
    public static final int BULLET_SPEED = 20;
    public static final int BULLET_DURATION = 20;
    private int counter = 0;

    /**
     * Construct a bullet relative to the ship's position.
     * 
     * @param pose The position and heading of the bullet.
     */
    public Bullet(Pose pose) {
        super(pose, new Vector2D(pose.getHeading(), BULLET_SPEED), BULLET_RADIUS);
    }

    /**
     * Update the bullet and destroyed it when it has existed for over 20 frames.
     */
    public void update() {
        super.update();
        counter++;
        if (counter > BULLET_DURATION) {
            this.setDestroyed(true);
        }
    }

    /**
     * Draw the bullet given the ship's position.
     */
    public void draw() {
        StdDraw.filledCircle(this.pose.getX(), this.pose.getY(), BULLET_RADIUS);
    }
}