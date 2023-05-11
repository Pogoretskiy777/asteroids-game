/**
 * This class represents a visual numeric display onto the game with inputed location, name, and value.
 * 
 * Honor Code: All work is my own.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/03/2023
 */
public class NumericDisplay implements Drawable {

    private String prefix;
    private int value;
    private Point location;

    /**
     * Construct a numeric display with its location, name, and name-related value.
     * 
     * @param xPos The x-coordinate of the display
     * @param yPos The y-coordinate of the display
     * @param prefix The prefix of the value
     * @param value The value to be counted
     */
    public NumericDisplay(int xPos, int yPos, String prefix, int value) {
        this.prefix = prefix;
        this.value = value;
        this.location = new Point(xPos, yPos);
    }

    /**
     * Get the location of the display.
     * 
     * @return Return the location
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Get the integer value of the display.
     * 
     * @return Return the value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Set a new value of the display.
     * 
     * @param value The integer to replace the old value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Draw the prefix and its numerical value in a white, text format in a given position.
     */
    public void draw() {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.textLeft(this.location.getX(), this.location.getY(), this.prefix + value);
    }
}