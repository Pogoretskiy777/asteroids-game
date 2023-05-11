/**
 * This class represents a positioned star in the A.S.T.E.R.O.I.D.S game. This class can return and draw the star.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/03/2023
 */
public class Star implements Drawable, Updatable {

    public static final int STAR_RADIUS = 1;
    private Point location;

    /**
     * Construct a star with the given x and y-coordinates.
     * 
     * @param x The x-coordinate of the star
     * @param y The y-coordinate of the star
     */
    public Star(double x, double y) {
        this.location = new Point(x, y);
    }

    /**
     * Get the location of the star.
     * 
     * @return Return the location of the star.
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Draw the star onto the canvas with its given position and radius.
     */
    public void draw() {
        StdDraw.filledCircle(this.location.getX(), this.location.getY(), STAR_RADIUS);
    }

    @Override
    public void update() {

    }
}