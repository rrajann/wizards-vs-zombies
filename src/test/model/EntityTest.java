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
        wizard = new Wizard(x, y);
        zombie = new Zombie(x, y);
        blast = new Blast(x, y, true, true);
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
        Wizard rightWizard = new Wizard(width - (speed + 2), 0);
        rightWizard.moveRight();
        assertEquals(width - 2, rightWizard.getPosX());
        rightWizard.moveRight();
        assertEquals(width, rightWizard.getPosX());
        rightWizard.moveRight();
        assertEquals(width, rightWizard.getPosX());
    }

    @Test
    public void handleBoundaryTestLeft() {
        Wizard leftWizard = new Wizard(speed + 2, 0);
        leftWizard.moveLeft();
        assertEquals(2, leftWizard.getPosX());
        leftWizard.moveLeft();
        assertEquals(0, leftWizard.getPosX());
        leftWizard.moveLeft();
        assertEquals(0, leftWizard.getPosX());
    }

    @Test
    public void handleBoundaryTestUp() {
        Wizard upWizard = new Wizard(0, speed + 2);
        upWizard.moveUp();
        assertEquals(2, upWizard.getPosY());
        upWizard.moveUp();
        assertEquals(0, upWizard.getPosY());
        upWizard.moveUp();
        assertEquals(0, upWizard.getPosY());
    }

    @Test
    public void handleBoundaryTestDown() {
        Wizard downWizard = new Wizard(0, width - (speed + 2));
        downWizard.moveDown();
        assertEquals(width - 2, downWizard.getPosY());
        downWizard.moveDown();
        assertEquals(width, downWizard.getPosY());
        downWizard.moveDown();
        assertEquals(width, downWizard.getPosY());
    }

}
