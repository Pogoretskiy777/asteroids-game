import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Ship class to ensure its capabilities.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/05/2023
 */
public class ShipTest {
    private Ship ship;

    /**
     * Setting up a ship in its original position in the game.
     */
    @BeforeEach
    public void setup() {
        ship = new Ship();
    }

    /**
     * Test that Ship is a Drawable.
     */
    @Test
    public void testImplementsInterfaceDrawable() {
        assertTrue(ship instanceof Drawable);
    }

    /**
     * Test that Ship is Updatable.
     */
    @Test
    public void testImplementsInterfaceUpdatable() {
        assertTrue(ship instanceof Updatable);
    }

    /**
     * Test the Ship's constant values.
     */
    @Test
    public void testConstants() {
        assertEquals(10, Ship.SHIP_WIDTH);
        assertEquals(20, Ship.SHIP_HEIGHT);
        assertEquals(0.1, Ship.SHIP_TURN);
        assertEquals(0.1, Ship.SHIP_MOVE);
        assertEquals(0.02, Ship.FRICTION);
    }

    /**
     * Test the getPose() method.
     */
    @Test
    public void testGetPose() {
        Pose expected = new Pose(240, 240, Math.PI / 2);
        TestHelpers.comparePoses(expected, ship.getPose());
    }

    /**
     * Test the getVelocity() method.
     */
    @Test
    public void testGetVelocity() {
        Vector2D expected = new Vector2D(Math.PI / 2, 0);
        TestHelpers.compareVectors(expected, ship.getVelocity());
    }

    /**
     * Test the getRadius() method.
     */
    @Test
    public void testGetRadius() {
        assertEquals(10, ship.getRadius());
    }

    /**
     * Test the isDestroyed() method.
     */
    @Test
    public void testIsDestroyed() {
        assertEquals(false, ship.isDestroyed());
    }

    /**
     * Test the setDestroyed() method.
     */
    @Test
    public void testSetDestroyed() {
        ship.setDestroyed(true);
        assertEquals(true, ship.isDestroyed());
    }

    /**
     * Test the inherited draw() method.
     */
    @Test
    public void testDraw() {
        ship.draw();
        String actual = StdDraw.getLastDraw();
        assertTrue("polygon()".equals(actual));
    }

    /**
     * Test that the constructor constructs properly.
     */
    @Test
    public void testConstructor() {
        Pose expectedPose = new Pose(240, 240, Math.PI / 2);
        Vector2D expectedVelocity = new Vector2D(Math.PI / 2, 0);
        TestHelpers.comparePoses(expectedPose, ship.getPose());
        TestHelpers.compareVectors(expectedVelocity, ship.getVelocity());
    }

    /**
     * Test the turnLeft() method.
     */
    @Test
    public void testTurningLeft() {
        ship.turnLeft();
        Vector2D expected = new Vector2D(Math.PI / 2 + .1, 0);
        Pose expectedPose = new Pose(240, 240, Math.PI / 2 + .1);
        TestHelpers.compareVectors(expected, ship.getVelocity());
        TestHelpers.comparePoses(expectedPose, ship.getPose());
    }

    /**
     * Test the turnRight() method.
     */
    @Test
    public void testTurningRight() {
        ship.turnRight();
        Pose expectedPose = new Pose(240, 240, Math.PI / 2 - .1);
        Vector2D expected = new Vector2D(Math.PI / 2 - .1, 0);
        TestHelpers.compareVectors(expected, ship.getVelocity());
        TestHelpers.comparePoses(expectedPose, ship.getPose());
    }

    /**
     * Test the thrust() method.
     */
    @Test
    public void testThrust() {
        ship.thrust();
        Vector2D expected = new Vector2D(Math.PI / 2, 0.1);
        TestHelpers.compareVectors(expected, ship.getVelocity());

    }

    /**
     * Test that the FRICTION constant is applied to the appropriate values when the ship does not have thrust after
     * each update().
     */
    @Test
    public void testFriction() {
        ship.thrust();
        ship.update();
        Vector2D expected = new Vector2D(Math.PI / 2, 0.08);
        Vector2D expected2 = new Vector2D(Math.PI / 2, 0.06);
        Vector2D expected3 = new Vector2D(Math.PI / 2, 0.00);
        TestHelpers.compareVectors(expected, ship.getVelocity());
        ship.update();
        TestHelpers.compareVectors(expected2, ship.getVelocity());
        for (int i = 0; i < 20; i++) {
            ship.update();
        }
        TestHelpers.compareVectors(expected3, ship.getVelocity());

    }

}