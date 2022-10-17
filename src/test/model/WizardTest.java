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
        assertEquals(55, wizard.getPosX());
        wizard.moveRight();
        assertEquals(60, wizard.getPosX());
        wizard.moveLeft();
        assertEquals(55, wizard.getPosX());
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
        assertEquals(55, wizard.getPosX());
        wizard.moveUp();
        assertEquals(55, wizard.getPosX());
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
        assertEquals(15, wizard.getPosY());
        wizard.moveUp();
        assertEquals(10, wizard.getPosY());
        wizard.moveDown();
        assertEquals(15, wizard.getPosY());
    }

    // MOVING UP/DOWN/LEFT/RIGHT
    @Test
    public void getPosYTestUpDownLeftRight() {
        assertEquals(20, wizard.getPosY());
        wizard.moveRight();
        assertEquals(20, wizard.getPosY());
        wizard.moveUp();
        assertEquals(15, wizard.getPosY());
        wizard.moveLeft();
        assertEquals(15, wizard.getPosY());
        wizard.moveDown();
        assertEquals(20, wizard.getPosY());
    }

    @Test
    public void getDXTestRightLeft() {
        assertEquals(speed, wizard.getDX());
        wizard.moveRight();
        assertEquals(5, wizard.getDX());
        wizard.moveLeft();
        assertEquals(-5, wizard.getDX());
    }

    @Test
    public void getDXTestUpDown() {
        assertEquals(speed, wizard.getDX());
        wizard.moveUp();
        assertEquals(0, wizard.getDX());
        wizard.moveDown();
        assertEquals(0, wizard.getDX());
    }

    @Test
    public void getDXTestUpDownLeftRight() {
        assertEquals(speed, wizard.getDX());
        wizard.moveRight();
        assertEquals(5, wizard.getDX());
        wizard.moveUp();
        assertEquals(0, wizard.getDX());
        wizard.moveLeft();
        assertEquals(-5, wizard.getDX());
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
        assertEquals(-5, wizard.getDY());
        wizard.moveDown();
        assertEquals(5, wizard.getDY());
    }

    @Test
    public void getDYTestUpDownLeftRight() {
        assertEquals(0, wizard.getDY());
        wizard.moveRight();
        assertEquals(0, wizard.getDY());
        wizard.moveUp();
        assertEquals(-5, wizard.getDY());
        wizard.moveLeft();
        assertEquals(0, wizard.getDY());
        wizard.moveDown();
        assertEquals(5, wizard.getDY());
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
        wizard.setMoving(true);
        assertTrue(wizard.isMoving());
        wizard.setMoving(false);
        assertFalse(wizard.isMoving());
        wizard.setMoving(false);
        assertFalse(wizard.isMoving());
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