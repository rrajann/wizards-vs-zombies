package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.WizardsVsZombies;

import static org.junit.jupiter.api.Assertions.*;

public class WizardTest {

    private Wizard wizard;
    private int speed = Wizard.SPEED;
    private int width = WizardsVsZombies.WIDTH;

    @BeforeEach
    public void before() {
        wizard = new Wizard(50, 20);
    }

    // TESTING CONSTRUCTOR:

    // MOVING LEFT/RIGHT
    @Test
    public void getPosXTestLeftRight() {
        assertEquals(50, wizard.getPosX());
        wizard.moveRight();
        assertEquals(50 + speed, wizard.getPosX());
        wizard.moveRight();
        assertEquals(50 + speed * 2, wizard.getPosX());
        wizard.moveLeft();
        assertEquals(50 + speed, wizard.getPosX());
    }

    // MOVING UP/DOWN
    @Test
    public void getPosXTestUpDown() {
        assertEquals(50, wizard.getPosX());
        wizard.moveUp();
        assertEquals(50, wizard.getPosX());
        wizard.moveUp();
        assertEquals(50, wizard.getPosX());
        wizard.moveDown();
        assertEquals(50, wizard.getPosX());
    }

    // MOVING UP/DOWN/LEFT/RIGHT
    @Test
    public void getPosXTestUpDownLeftRight() {
        assertEquals(50, wizard.getPosX());
        wizard.moveRight();
        assertEquals(50 + speed, wizard.getPosX());
        wizard.moveUp();
        assertEquals(50 + speed, wizard.getPosX());
        wizard.moveLeft();
        assertEquals(50, wizard.getPosX());
        wizard.moveDown();
        assertEquals(50, wizard.getPosX());
    }

    // MOVING LEFT/RIGHT
    @Test
    public void getPosYTestLeftRight() {
        assertEquals(20, wizard.getPosY());
        wizard.moveRight();
        assertEquals(20, wizard.getPosY());
        wizard.moveRight();
        assertEquals(20, wizard.getPosY());
        wizard.moveLeft();
        assertEquals(20, wizard.getPosY());
    }

    // MOVING UP/DOWN
    @Test
    public void getPosYTestUpDown() {
        assertEquals(20, wizard.getPosY());
        wizard.moveUp();
        assertEquals(20 - speed, wizard.getPosY());
        wizard.moveUp();
        assertEquals(20 - speed * 2, wizard.getPosY());
        wizard.moveDown();
        assertEquals(20 - speed, wizard.getPosY());
    }

    // MOVING UP/DOWN/LEFT/RIGHT
    @Test
    public void getPosYTestUpDownLeftRight() {
        assertEquals(20, wizard.getPosY());
        wizard.moveRight();
        assertEquals(20, wizard.getPosY());
        wizard.moveUp();
        assertEquals(20 - speed, wizard.getPosY());
        wizard.moveLeft();
        assertEquals(20 - speed, wizard.getPosY());
        wizard.moveDown();
        assertEquals(20, wizard.getPosY());
    }

    @Test
    public void getDXTestRightLeft() {
        assertEquals(0, wizard.getDX());
        wizard.moveRight();
        assertEquals(speed, wizard.getDX());
        wizard.moveLeft();
        assertEquals(-speed, wizard.getDX());
    }

    @Test
    public void getDXTestUpDown() {
        assertEquals(0, wizard.getDX());
        wizard.moveUp();
        assertEquals(0, wizard.getDX());
        wizard.moveDown();
        assertEquals(0, wizard.getDX());
    }

    @Test
    public void getDXTestUpDownLeftRight() {
        assertEquals(0, wizard.getDX());
        wizard.moveRight();
        assertEquals(speed, wizard.getDX());
        wizard.moveUp();
        assertEquals(0, wizard.getDX());
        wizard.moveLeft();
        assertEquals(-speed, wizard.getDX());
        wizard.moveDown();
        assertEquals(0, wizard.getDX());
    }

    @Test
    public void getDYTestRightLeft() {
        assertEquals(0, wizard.getDY());
        wizard.moveRight();
        assertEquals(0, wizard.getDY());
        wizard.moveLeft();
        assertEquals(0, wizard.getDY());
    }

    @Test
    public void getDYTestUpDown() {
        assertEquals(0, wizard.getDY());
        wizard.moveUp();
        assertEquals(-speed, wizard.getDY());
        wizard.moveDown();
        assertEquals(speed, wizard.getDY());
    }

    @Test
    public void getDYTestUpDownLeftRight() {
        assertEquals(0, wizard.getDY());
        wizard.moveRight();
        assertEquals(0, wizard.getDY());
        wizard.moveUp();
        assertEquals(-speed, wizard.getDY());
        wizard.moveLeft();
        assertEquals(0, wizard.getDY());
        wizard.moveDown();
        assertEquals(speed, wizard.getDY());
    }

    @Test
    public void getHealthTest() {
        assertEquals(100, wizard.getHealth());
        wizard.setHealth(50);
        assertEquals(50, wizard.getHealth());
        wizard.setHealth(0);
        assertEquals(0, wizard.getHealth());
    }

    @Test
    public void getTimeTest() {
        assertEquals(5, wizard.getTime());
        wizard.setTime(3);
        assertEquals(3, wizard.getTime());
        wizard.setTime(1);
        assertEquals(1, wizard.getTime());
    }

    @Test
    public void isMovingTest() {
        assertFalse(wizard.isMoving());
        wizard.moveRight();
        assertTrue(wizard.isMoving());
    }

}