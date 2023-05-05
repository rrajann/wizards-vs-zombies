package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.WizardsVsZombies;

import static java.lang.Math.round;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EntityTest {

    private Wizard wizard;
    private Blast blast;
    private Zombie zombie;

    private static final int x = WizardsVsZombies.WIDTH/2;
    private static final int y = WizardsVsZombies.HEIGHT/2;
    private static final int width = WizardsVsZombies.WIDTH;
    private static final int height = WizardsVsZombies.HEIGHT;
    private static final int speed = Wizard.SPEED;

    @BeforeEach
    public void before() {
        Wizard wizard = Wizard.getInstance();
        zombie = new Zombie(x, y);
        blast = new Blast(x, y, Entity.Direction.RIGHT);
    }

    @Test
    public void collisionTestX() {
        assertTrue(zombie.collision(blast));
        assertTrue(wizard.collision(zombie));
        wizard.setPosX(x + (int) round(zombie.getHitbox().getWidth() / 2) - 1);
        blast.setPosX((x + (int) round(zombie.getHitbox().getWidth() / 2) - 1));
        assertTrue(wizard.collision(zombie));
        assertTrue(zombie.collision(blast));
        wizard.setPosX(x + (int) round(zombie.getHitbox().getWidth() / 2) + 1);
        blast.setPosX(x + (int) round(zombie.getHitbox().getWidth() / 2) + 1);
        assertFalse(wizard.collision(zombie));
        assertFalse(zombie.collision(blast));
    }

    @Test
    public void collisionTestY() {
        assertTrue(zombie.collision(blast));
        assertTrue(wizard.collision(zombie));
        wizard.setPosY(y + (int) round(zombie.getHitbox().getHeight() / 2) - 1);
        blast.setPosY((y + (int) round(zombie.getHitbox().getHeight() / 2) - 1));
        assertTrue(wizard.collision(zombie));
        assertTrue(zombie.collision(blast));
        wizard.setPosY(y + (int) round(zombie.getHitbox().getHeight() / 2) + 1);
        blast.setPosY(y + (int) round(zombie.getHitbox().getHeight() / 2) + 1);
        assertFalse(wizard.collision(zombie));
        assertFalse(zombie.collision(blast));
    }

    @Test
    public void handleBoundaryTestRight() {
        wizard.setPosX(width - (speed + 2));
        wizard.setPosY(0);
        wizard.moveRight();
        assertEquals(width - 2, wizard.getPosX());
        wizard.moveRight();
        assertEquals(width, wizard.getPosX());
        wizard.moveRight();
        assertEquals(width, wizard.getPosX());
    }

    @Test
    public void handleBoundaryTestLeft() {
        wizard.setPosX(speed + 2);
        wizard.setPosY(0);
        wizard.moveLeft();
        assertEquals(2, wizard.getPosX());
        wizard.moveLeft();
        assertEquals(0, wizard.getPosX());
        wizard.moveLeft();
        assertEquals(0, wizard.getPosX());
    }

    @Test
    public void handleBoundaryTestUp() {
        wizard.setPosX(0);
        wizard.setPosY(speed + 2);
        wizard.moveUp();
        assertEquals(2, wizard.getPosY());
        wizard.moveUp();
        assertEquals(0, wizard.getPosY());
        wizard.moveUp();
        assertEquals(0, wizard.getPosY());
    }

    @Test
    public void handleBoundaryTestDown() {
        wizard.setPosX(0);
        wizard.setPosY(width - (speed + 2));
        wizard.moveDown();
        assertEquals(width - 2, wizard.getPosY());
        wizard.moveDown();
        assertEquals(width, wizard.getPosY());
        wizard.moveDown();
        assertEquals(width, wizard.getPosY());
    }

}
