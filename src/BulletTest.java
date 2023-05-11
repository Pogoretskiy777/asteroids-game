import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Bullet class to ensure its capabilities.
 * 
 * @author Joseph Pogoretskiy
 * @version 04/05/2023
 */
public class BulletTest {
    private Bullet bullet;
    private Ship ship;

    /**
     * Setting up a bullet, a ship, and the ship's pose before each test.
     */
    @BeforeEach
    public void setup() {
        ship = new Ship();
        Pose shipPose = ship.getPose();
        bullet = new Bullet(shipPose);
    }

    /**
     * Test that bullet is drawable.
     */
    @Test
    public void testImplementsInterfaceDrawable() {
        assertTrue(bullet instanceof Drawable);
    }

    /**
     * Test that bullet is updatable.
     */
    @Test
    public void testImplementsInterfaceUpdatable() {
        assertTrue(bullet instanceof Updatable);
    }

    /**
     * Testing the constants to be correct.
     */
    @Test
    public void testConstants() {
        assertEquals(1.5, Bullet.BULLET_RADIUS);
        assertEquals(20, Bullet.BULLET_SPEED);
        assertEquals(20, Bullet.BULLET_DURATION);
    }

    /**
     * Testing the functionality of the bullet constructor.
     */
    @Test
    public void testConstructor() {
        Ship ship = new Ship();
        Vector2D expectedVelocity = new Vector2D(Math.PI / 2, 20);
        TestHelpers.comparePoses(ship.getPose(), bullet.getPose());
        TestHelpers.compareVectors(expectedVelocity, bullet.getVelocity());
        assertEquals(1.5, bullet.getRadius());
    }

    /**
     * Test the getPose() method.
     */
    @Test
    public void testGetPose() {
        Pose expected = new Pose(240, 240, Math.PI / 2);
        TestHelpers.comparePoses(expected, bullet.getPose());
    }

    /**
     * Test that the bullet and ship's pose are identical.
     */
    @Test
    public void testPoseIsIdenticalWithShipPose() {
        Ship ship = new Ship();
        ship.thrust();
        TestHelpers.comparePoses(ship.getPose(), (bullet.getPose()));
    }

    /**
     * Test the getVelocity() method.
     */
    @Test
    public void testGetVelocity() {
        Vector2D expected = new Vector2D(Math.PI / 2, 20);
        TestHelpers.compareVectors(expected, bullet.getVelocity());
    }

    /**
     * Test the getRadius() method.
     */
    @Test
    public void testGetRadius() {
        assertEquals(1.5, bullet.getRadius());
    }

    /**
     * Test the isDestroyed() method.
     */
    @Test
    public void testIsDestroyed() {
        assertEquals(false, bullet.isDestroyed());
    }

    /**
     * Test the setDestroyed() method.
     */
    @Test
    public void testSetDestroyed() {
        bullet.setDestroyed(true);
        assertEquals(true, bullet.isDestroyed());
        bullet.setDestroyed(false);
        assertEquals(false, bullet.isDestroyed());
    }

    /**
     * Test that the bullet is correctly drawn on the canvas.
     */
    @Test
    public void testDraw() {
        bullet.draw();

        String actual = StdDraw.getLastDraw();
        assertTrue("filledCircle(240.0, 240.0, 1.5)".equals(actual));
    }

    /**
     * Test that the bullet updates correctly and does not exist more than 20 frames since the object's creation.
     */
    @Test
    public void testUpdate() {
        String actual = StdDraw.getLastDraw();
        assertTrue("filledCircle(240.0, 240.0, 1.5)".equals(actual));
        assertEquals(false, bullet.isDestroyed());
        for (int i = 0; i < 21; i++) {
            bullet.update();
        }
        assertEquals(true, bullet.isDestroyed());

    }
}