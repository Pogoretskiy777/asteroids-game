/**
 * This class represents a rendered game element of the A.S.T.E.R.O.I.D.S game. Every game element has some sort of
 * velocity, position on the screen, radius, and the ability to be destroyed.
 * 
 * Honor Code: All work is my own.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/03/2023
 */
public abstract class GameElement implements Updatable, Drawable {
    protected Pose pose;
    protected Vector2D velocity;
    protected double radius;
    protected boolean destroyed;

    /**
     * Construct a game element that isn't destroyed with inputed position, velocity, and radius.
     * 
     * @param pose Position and direction on the screen
     * @param velocity The velocity of which the element moves
     * @param radius The radius of the element
     */
    public GameElement(Pose pose, Vector2D velocity, double radius) {
        this.pose = pose;
        this.velocity = velocity;
        this.radius = radius;
        this.destroyed = false;
    }

    /**
     * Get the pose of the element.
     * 
     * @return Return pose value
     */
    public Pose getPose() {
        return this.pose;
    }

    /**
     * Get the velocity of the element.
     * 
     * @return Return the velocity
     */
    public Vector2D getVelocity() {
        return this.velocity;
    }

    /**
     * Get the radius of the element.
     * 
     * @return Return the radius
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Check whether the element is destroyed or not.
     * 
     * @return If the element is destroyed
     */
    public boolean isDestroyed() {
        return this.destroyed;
    }

    /**
     * Set the destroyed boolean.
     * 
     * @param destroyed Whether the element is destroyed or not
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * Update the game with the element's new position. If the element flies off an edge of the screen, it should
     * reappear on the opposite side.
     */
    public void update() {
        Pose newPose = this.pose.move(this.velocity);
        this.pose = newPose;
        if (this.pose.getX() < 0) {
            Pose newX = this.pose.newX(GameDriver.SCREEN_WIDTH);
            this.pose = newX;
        } else if (this.pose.getX() > GameDriver.SCREEN_WIDTH) {
            Pose newX = this.pose.newX(0);
            this.pose = newX;
        }
        if (newPose.getY() < 0) {
            Pose newY = this.pose.newY(GameDriver.SCREEN_HEIGHT);
            this.pose = newY;
        } else if (newPose.getY() > GameDriver.SCREEN_HEIGHT) {
            Pose newY = this.pose.newY(0);
            this.pose = newY;
        }

    }

    /**
     * Check the collision of two game elements based on their radiuses and distance between them.
     * 
     * @param other The other GameElement to compare to
     * @return Whether or not collision has occured
     */
    public boolean checkCollision(GameElement other) {
        double radii = this.radius + other.radius;
        double xDistance = Math.abs(this.pose.xPosition - other.pose.xPosition);
        double yDistance = Math.abs(this.pose.yPosition - other.pose.yPosition);
        double hypotenuse = (Math.sqrt((Math.pow(xDistance, 2)) + Math.pow(yDistance, 2))) - radii;
        return hypotenuse < 0;
    }

}
