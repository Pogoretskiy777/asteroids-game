import java.util.ArrayList;

/**
 * Main class for Asteroids.
 * 
 * Honor Code: I used
 * https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java to
 * figure out how to generate random numbers. All modifications to the original code is my own work otherwise.
 * 
 * @author Joseph Pogoretskiy and CS159 Instructors
 * @version 04/14/2023
 */
public class AsteroidsGame implements Playable {

    public static final int LIVES = 3;
    private ArrayList<Drawable> drawElements;
    private ArrayList<Updatable> updateElements;
    private ArrayList<GameElement> shipAndBullets;
    private ArrayList<Enemy> enemies;

    private NumericDisplay score;
    private NumericDisplay lives;
    private Ship ship;

    /**
     * Constructs all game elements.
     */
    public AsteroidsGame() {
        StdDraw.setTitle("Generic Space Rock Shooter");

        drawElements = new ArrayList<>();
        updateElements = new ArrayList<>();
        shipAndBullets = new ArrayList<>();
        enemies = new ArrayList<>();

        // add background elements
        final int TOP = GameDriver.SCREEN_HEIGHT;
        score = new NumericDisplay(40, TOP - 20, "Score: ", 0);
        lives = new NumericDisplay(40, TOP - 40, "Lives: ", LIVES);
        drawElements.add(score);
        drawElements.add(lives);
        this.newShip();
    }

    /**
     * Creates and adds 10 new asteroids with random locations.
     */
    private void newEnemies() {
        for (int i = 0; i < 10; i++) {
            Asteroid asteroid = new Asteroid(AsteroidSize.randomSize());
            drawElements.add(asteroid);
            updateElements.add(asteroid);
            enemies.add(asteroid);
        }
    }

    /**
     * Creates and adds 100 stars with random locations.
     */
    private void newStars() {
        for (int i = 0; i < 100; i++) {
            Star star = new Star(AsteroidsGame.randomXPosition(), AsteroidsGame.randomYPosition());
            drawElements.add(star);
            updateElements.add(star);
        }
    }

    /**
     * Create and add a new ship centered on the screen.
     */
    private void newShip() {
        ship = new Ship();
        drawElements.add(ship);
        updateElements.add(ship);
        shipAndBullets.add(ship);
    }

    /**
     * Add an enemy to the screen given then inputed enemy.
     * 
     * @param enemy The enemy to be added to the game.
     */
    public void addEnemy(Enemy enemy) {
        updateElements.add(enemy);
        drawElements.add(enemy);
        enemies.add(enemy);
    }

    /**
     * Create and add a new bullet relative to the ship's position.
     */
    private void newBullet() {
        Bullet bullet = new Bullet(ship.getPose());
        drawElements.add(bullet);
        updateElements.add(bullet);
        shipAndBullets.add(bullet);
    }

    /**
     * Remove all bullets that have collided with an enemy.
     */
    private void removeDestroyedBullets() {
        for (int i = shipAndBullets.size() - 1; i >= 0; i--) {
            if (shipAndBullets.get(i).isDestroyed()) {
                updateElements.remove(shipAndBullets.get(i));
                drawElements.remove(shipAndBullets.get(i));
                shipAndBullets.remove(shipAndBullets.get(i));

            }
        }
    }

    /**
     * Remove all enemies that have collided with a bullet.
     */
    private void removeDestroyedEnemies() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (enemies.get(i).isDestroyed()) {
                updateElements.remove(enemies.get(i));
                drawElements.remove(enemies.get(i));
                enemies.remove(enemies.get(i));
            }
        }
    }

    /**
     * Starts a new game with 10 asteroids and 100 stars.
     */
    public void startGame() {
        this.newStars();
        this.newEnemies();
    }

    /**
     * For every enemy in the game, check their collision with every ship, and remove the ship if so, and every bullet,
     * and remove the bullet and enemy if so. Also adjust the lives and score amount depending on the collision.
     */
    public void handleCollisions() {
        for (int i = enemies.size() - 1; i >= 0; i--) {
            // Check the ship's collision.
            if (ship.checkCollision(enemies.get(i))) {
                lives.setValue(lives.getValue() - 1);
                score.setValue(score.getValue() + enemies.get(i).getPoints());
                ship.setDestroyed(true);
                enemies.get(i).setDestroyed(true);
                newShip();
            }
            // Check the bullet's collision.
            for (int j = shipAndBullets.size() - 1; j >= 0; j--) {
                if (shipAndBullets.get(j) instanceof Bullet) {
                    if (shipAndBullets.get(j).checkCollision(enemies.get(i))) {
                        score.setValue(score.getValue() + enemies.get(i).getPoints());
                        enemies.get(i).setDestroyed(true);
                        shipAndBullets.get(j).setDestroyed(true);
                    }
                }
            }
        }
    }

    /**
     * Handle keyboard input from the game and move the ship and shoot bullets if the corresponding keys are pressed.
     */
    private void handleKeyboardInput() {
        if (GameDriver.spacePressed()) {
            newBullet();
        }

        if (GameDriver.leftPressed()) {
            this.ship.turnLeft();
        }

        if (GameDriver.rightPressed()) {
            this.ship.turnRight();
        }

        if (GameDriver.upPressed()) {
            this.ship.thrust();
        }
    }

    /**
     * For every frame of the game, update all updatable items, remove destroyed game elements, check for collisions,
     * and handle keyboard input. If the player dies three times, the game freezes and you lose. If all enemies are
     * destroyed, 10 more asteroids spawn. There is a .2% chance of a saucer randomly spawning each update.
     */
    @Override
    public void update() {
        // freeze simulation if game is over
        if (lives.getValue() <= 0) {
            return;
        }

        // update everything (including newest bullet)
        handleKeyboardInput();
        for (Updatable item : updateElements) {
            item.update();
        }
        handleCollisions();
        removeDestroyedBullets();
        removeDestroyedEnemies();

        int randNum = GameDriver.GENERATOR.nextInt(0, 499);
        if (randNum == 0) {
            addEnemy(new Saucer());
        }
        if (enemies.size() == 0) {
            this.newEnemies();
        }
    }

    /**
     * Draw all drawable elements in white.
     */
    @Override
    public void draw() {
        int randNum = GameDriver.GENERATOR.nextInt(0, 3);
        for (Drawable element : drawElements) {
            if (element instanceof Star && randNum == 0) {
                StdDraw.setPenColor(StdDraw.BLACK);
                element.draw();
            } else {
                StdDraw.setPenColor(StdDraw.WHITE);
                element.draw();
            }

        }
    }

    /**
     * Generates a random X position on the screen.
     * 
     * @return the x position
     */
    protected static double randomXPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_WIDTH;
    }

    /**
     * Generates a random Y position on the screen.
     * 
     * @return the y position
     */
    protected static double randomYPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_HEIGHT;
    }

    /**
     * Generates a random heading from the interval [0, 2*PI).
     * 
     * @return the heading
     */
    protected static double randomHeading() {
        return GameDriver.GENERATOR.nextDouble() * 2 * Math.PI;
    }

}
