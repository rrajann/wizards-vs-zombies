package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.WizardsVsZombies;

import static model.Zombie.ZOMBIE_SPEED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZombieTest {

    private Zombie zombie;
    private static final int x = WizardsVsZombies.WIDTH / 2;
    private static final int y = WizardsVsZombies.HEIGHT / 2;

    @BeforeEach
    public void before() {
        zombie = new Zombie(x, y);
    }

    @Test
    public void approachAlreadyThere() {
        zombie.approach(x, y);
        assertEquals(x, zombie.getPosX());
        assertEquals(y, zombie.getPosY());
    }

    @Test
    public void approachTestRight() {
        zombie.approach(x * 2, y);
        assertEquals(x + ZOMBIE_SPEED, zombie.getPosX());
        assertEquals(y, zombie.getPosY());
    }

    @Test
    public void approachTestLeft() {
        zombie.approach(x / 2, y);
        assertEquals(x - ZOMBIE_SPEED, zombie.getPosX());
        assertEquals(y, zombie.getPosY());
    }

    @Test
    public void approachTestUp() {
        zombie.approach(x, y / 2);
        assertEquals(x, zombie.getPosX());
        assertEquals(y - ZOMBIE_SPEED, zombie.getPosY());
    }

    @Test
    public void approachTestDown() {
        zombie.approach(x, y * 2);
        assertEquals(x, zombie.getPosX());
        assertEquals(y + ZOMBIE_SPEED, zombie.getPosY());
    }

    @Test
    public void approachTestUpLeft() {
        zombie.approach(x / 2, y / 2);
        assertEquals(x - ZOMBIE_SPEED, zombie.getPosX());
        assertEquals(y - ZOMBIE_SPEED, zombie.getPosY());
    }

    @Test
    public void approachTestUpRight() {
        zombie.approach(x * 2, y / 2);
        assertEquals(x + ZOMBIE_SPEED, zombie.getPosX());
        assertEquals(y - ZOMBIE_SPEED, zombie.getPosY());
    }

    @Test
    public void approachTestDownLeft() {
        zombie.approach(x / 2, y * 2);
        assertEquals(x - ZOMBIE_SPEED, zombie.getPosX());
        assertEquals(y + ZOMBIE_SPEED, zombie.getPosY());
    }

    @Test
    public void approachTestDownRight() {
        zombie.approach(x * 2, y * 2);
        assertEquals(x + ZOMBIE_SPEED, zombie.getPosX());
        assertEquals(y + ZOMBIE_SPEED, zombie.getPosY());
    }

    @Test
    public void approachWithinSpeedTopRight() {
        zombie.approach(x + (ZOMBIE_SPEED / 2), y - (ZOMBIE_SPEED / 2));
        assertEquals(x + (ZOMBIE_SPEED / 2), zombie.getPosX());
        assertEquals(y - (ZOMBIE_SPEED / 2), zombie.getPosY());
    }

    @Test
    public void approachWithinSpeedTopLeft() {
        zombie.approach(x - (ZOMBIE_SPEED / 2), y - (ZOMBIE_SPEED / 2));
        assertEquals(x - (ZOMBIE_SPEED / 2), zombie.getPosX());
        assertEquals(y - (ZOMBIE_SPEED / 2), zombie.getPosY());
    }

    @Test
    public void approachWithinSpeedBotRight() {
        zombie.approach(x + (ZOMBIE_SPEED / 2), y + (ZOMBIE_SPEED / 2));
        assertEquals(x + (ZOMBIE_SPEED / 2), zombie.getPosX());
        assertEquals(y + (ZOMBIE_SPEED / 2), zombie.getPosY());
    }

    @Test
    public void approachWithinSpeedBotLeft() {
        zombie.approach(x - (ZOMBIE_SPEED / 2), y + (ZOMBIE_SPEED / 2));
        assertEquals(x - (ZOMBIE_SPEED / 2), zombie.getPosX());
        assertEquals(y + (ZOMBIE_SPEED / 2), zombie.getPosY());
    }

}
