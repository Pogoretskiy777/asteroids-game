/**
 * A class with three predetermined asteroid sizes with their corresponding point values.
 * 
 * Honor Code: All work is my own.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/24/2023
 */

public enum AsteroidSize {
    SMALL(10, 200), MEDIUM(20, 100), LARGE(30, 50);

    private int radius;
    private int points;

    /**
     * Construct an asteroid with a given radius and point value.
     * 
     * @param radius The radius of the asteroid
     * @param points The point value of the asteroid to add to the score when destroyed
     */
    private AsteroidSize(int radius, int points) {
        this.radius = radius;
        this.points = points;
    }

    /**
     * Get the radius of the asteroid.
     * 
     * @return Return the radius
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Get the point value of the asteroid.
     * 
     * @return Return the points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Return and randomly chosen predetermined asteroid.
     * 
     * @return An asteroid
     */
    public static AsteroidSize randomSize() {
        int randNum = GameDriver.GENERATOR.nextInt(1, 5);
        if (randNum == 1) {
            return AsteroidSize.SMALL;
        } else if (randNum == 2) {
            return AsteroidSize.MEDIUM;
        } else {
            return AsteroidSize.LARGE;
        }
    }
}