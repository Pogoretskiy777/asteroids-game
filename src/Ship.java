/**
 * This class represents a ship that can turn and accelerate based off of the keyboard inputs. The ship slows down at
 * the absence of thrust.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/10/2023
 */

public class Ship extends GameElement {

    public static final int SHIP_WIDTH = 10;
    public static final int SHIP_HEIGHT = 20;
    public static final double SHIP_TURN = 0.1;
    public static final double SHIP_MOVE = 0.1;
    public static final double FRICTION = 0.02;

    /**
     * Construct a ship in the center of the canvas with the initial direction north with no velocity.
     */
    public Ship() {
        super(new Pose(GameDriver.SCREEN_WIDTH / 2, GameDriver.SCREEN_HEIGHT / 2, Math.PI / 2),
                new Vector2D(Math.PI / 2, 0), SHIP_HEIGHT / 2);
    }

    /**
     * Turn the ship left.
     */
    public void turnLeft() {
        Pose turntPose = new Pose(this.pose.getX(), this.pose.getY(), this.pose.getHeading() + SHIP_TURN);
        this.pose = turntPose;
    }

    /**
     * Turn the ship right.
     */
    public void turnRight() {
        Pose turntPose = new Pose(this.pose.getX(), this.pose.getY(), this.pose.getHeading() - SHIP_TURN);
        this.pose = turntPose;
    }

    /**
     * Accelerate the ship every time thrust is applied in a frame.
     */
    public void thrust() {
        Vector2D newVelocity = new Vector2D(this.pose.getHeading(), SHIP_MOVE);
        Vector2D addedVelocity = this.velocity.add(newVelocity);
        this.velocity = addedVelocity;
    }

    /**
     * Draw the ship facing north with its given width and height.
     */
    public void draw() {
        GameUtils.drawPoseAsTriangle(this.pose, SHIP_WIDTH, SHIP_HEIGHT);
    }

    /**
     * Update the ship based off of the keyboard inputs and the ship's pose and velocity.
     */
    public void update() {
        super.update();
        double velocity = this.velocity.getMagnitude();
        velocity = velocity - FRICTION;
        if (velocity < 0) {
            Vector2D newVelocity = this.velocity.newMagnitude(0);
            this.velocity = newVelocity;
        } else {
            Vector2D newVelocity = this.velocity.newMagnitude(this.velocity.getMagnitude() - FRICTION);
            this.velocity = newVelocity;
        }

    }
}