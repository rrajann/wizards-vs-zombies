package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.WizardsVsZombies;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    private GameLogic game;
    private static final int WIDTH = WizardsVsZombies.WIDTH;
    private static final int HEIGHT = WizardsVsZombies.HEIGHT;
    private static final int X = WizardsVsZombies.WIDTH / 2;
    private static final int Y = WizardsVsZombies.HEIGHT / 2;
    private static final int X_SIZE = Entity.LIVING_X;
    private static final int Y_SIZE = Entity.LIVING_Y;
    private static final int damage = Zombie.ZOMBIE_DAMAGE;

    private List<Zombie> zombies;
    private List<Blast> blasts;
    private Wizard wizard;
    private Zombie z;
    private Blast b;

    @BeforeEach
    public void before() {
        game = new GameLogic();
        z = new Zombie(X, Y);
        b = new Blast(X, Y, Entity.Direction.RIGHT);
        game.addZombie(z);
        game.addBlast(b);
        zombies = game.getZombies();
        blasts = game.getBlasts();
        wizard = game.getWizard();

    }

    @Test
    public void addZombieTest() {
        assertEquals(1, zombies.size());
        assertTrue(zombies.contains(z));
    }

    @Test
    public void addZombieMultipleTest() {
        Zombie zTwo = new Zombie(2 * X, 2 * Y);
        Zombie zThree = new Zombie(0, 0);
        game.addZombie(zTwo);
        assertEquals(2, zombies.size());
        game.addZombie(zThree);
        assertEquals(3, zombies.size());
        assertTrue(zombies.contains(z));
        assertTrue(zombies.contains(zTwo));
        assertTrue(zombies.contains(zThree));
    }

    @Test
    public void addBlastTest() {
        assertEquals(1, blasts.size());
        assertTrue(blasts.contains(b));
    }

    @Test
    public void addBlastMultipleTest() {
        assertEquals(1, blasts.size());
        Blast blastUp = new Blast(X, Y, Entity.Direction.LEFT);
        game.addBlast(blastUp);
        assertEquals(2, blasts.size());
        assertTrue(blasts.contains(b));
        assertTrue(blasts.contains(blastUp));
    }

    // ALL THE TESTS BELOW UNTIL "POINT END" ARE TESTING deleteZombies


    @Test
    public void deleteZombiesTestEmpty() {
        zombies.remove(z);
        assertFalse(game.deleteZombies());
        assertTrue(zombies.isEmpty());                          // blast is already same coordinate as zombie
    }

    @Test
    public void deleteZombiesTestNoBlasts() {
        blasts.remove(b);
        assertFalse(game.deleteZombies());
        assertTrue(zombies.contains(z));                        // blast is already same coordinate as zombie
        assertEquals(1, zombies.size());
    }

    @Test
    public void deleteZombiesTestSingle() {
        assertTrue(game.deleteZombies());
        assertTrue(zombies.isEmpty());                          // blast is already same coordinate as zombie
    }

    @Test
    public void deleteZombiesTestMultiple() {
        Zombie zSecond = new Zombie(X / 2, Y / 2);
        Zombie zThird = new Zombie(0, 0);
        game.addZombie(zSecond);
        game.addZombie(zThird);
        assertTrue(game.deleteZombies());
        assertEquals(2, zombies.size());
        assertFalse(zombies.contains(z));
        assertTrue(zombies.contains(zSecond));
        assertTrue(zombies.contains(zThird));
    }

    @Test
    public void deleteZombiesTestNoDeletion() {
        zombies.remove(z);
        Zombie zSecond = new Zombie(X / 2, Y / 2);
        Zombie zThird = new Zombie(0, 0);
        game.addZombie(zSecond);
        game.addZombie(zThird);
        assertFalse(game.deleteZombies());
        assertEquals(2, zombies.size());
        assertTrue(zombies.contains(zSecond));
        assertTrue(zombies.contains(zThird));
    }

    @Test
    public void deleteZombiesTestMultipleBlasts() {
        Zombie zSecond = new Zombie(X / 2, Y / 2);
        Zombie zThird = new Zombie(0, 0);
        Blast blastZero = new Blast(0, 0, Entity.Direction.RIGHT);
        game.addBlast(blastZero);
        game.addZombie(zSecond);
        game.addZombie(zThird);
        assertTrue(game.deleteZombies());
        assertEquals(1, zombies.size());
        assertFalse(zombies.contains(z));
        assertTrue(zombies.contains(zSecond));
        assertFalse(zombies.contains(zThird));
    }

    @Test
    public void deleteBlastsTestEmpty() {
        blasts.remove(b);
        assertFalse(game.deleteZombies());
        assertTrue(blasts.isEmpty());                          // blast is already same coordinate as zombie
    }

    @Test
    public void deleteBlastsTestNoZombies() {
        zombies.remove(z);
        assertFalse(game.deleteZombies());
        assertTrue(blasts.contains(b));                        // blast is already same coordinate as zombie
        assertEquals(1, blasts.size());
    }

    @Test
    public void deleteBlastsTestSingle() {
        assertTrue(game.deleteZombies());                       // blast is already in same coordinate as zombie
        assertTrue(blasts.isEmpty());
    }

    @Test
    public void deleteBlastsTestMultiple() {
        Blast blastDown = new Blast(0, 0, Entity.Direction.LEFT);
        Blast blastLeft = new Blast(X / 2, Y / 2, Entity.Direction.UP);
        game.addBlast(blastDown);
        game.addBlast(blastLeft);
        assertTrue(game.deleteZombies());
        assertEquals(2, blasts.size());
        assertTrue(blasts.contains(blastDown));
        assertTrue(blasts.contains(blastLeft));
        assertFalse(blasts.contains(b));
    }

    @Test
    public void deleteBlastsTestNoDeletion() {
        blasts.remove(b);
        Blast blastDown = new Blast(0, 0, Entity.Direction.DOWN);
        Blast blastLeft = new Blast(X / 2, Y / 2, Entity.Direction.LEFT);
        game.addBlast(blastDown);
        game.addBlast(blastLeft);
        assertFalse(game.deleteZombies());
        assertEquals(2, blasts.size());
        assertTrue(blasts.contains(blastDown));
        assertTrue(blasts.contains(blastLeft));
    }

    @Test
    public void deleteBlastsTestMultipleZombies() {
        Blast blastDown = new Blast(0, 0, Entity.Direction.DOWN);
        Blast blastLeft = new Blast(X / 2, Y / 2, Entity.Direction.LEFT);
        Zombie zombieZero = new Zombie(0, 0);
        game.addBlast(blastDown);
        game.addBlast(blastLeft);
        game.addZombie(zombieZero);
        assertTrue(game.deleteZombies());
        assertEquals(1, blasts.size());
        assertFalse(blasts.contains(blastDown));
        assertFalse(blasts.contains(b));
        assertTrue(blasts.contains(blastLeft));
    }

    @Test
    public void deleteZombiesEverythingKilled() {
        Blast blastDown = new Blast(0, 0, Entity.Direction.DOWN);
        Blast blastLeft = new Blast(X / 2, Y / 2, Entity.Direction.LEFT);
        Zombie zombieSecond = new Zombie(0, 0);
        Zombie zombieThird = new Zombie(X / 2, Y / 2);
        game.addBlast(blastDown);
        game.addBlast(blastLeft);
        game.addZombie(zombieSecond);
        game.addZombie(zombieThird);
        assertTrue(game.deleteZombies());
        assertTrue(blasts.isEmpty());
        assertTrue(zombies.isEmpty());
    }

    @Test
    public void deleteZombiesEverythingKilledDuplicated() {
        Blast blastDown = new Blast(0, 0, Entity.Direction.DOWN);
        Blast blastLeft = new Blast(X / 2, Y / 2, Entity.Direction.LEFT);
        Zombie zombieSecond = new Zombie(0, 0);
        Zombie zombieThird = new Zombie(X / 2, Y / 2);
        game.addBlast(blastDown);
        game.addBlast(blastLeft);
        game.addZombie(zombieSecond);
        game.addZombie(zombieThird);
        game.addZombie(zombieThird);
        assertTrue(game.deleteZombies());
        assertTrue(blasts.isEmpty());
        assertTrue(zombies.isEmpty());
    }

    // POINT END

    @Test
    public void basicAttackTestRightDown() {
        game.deleteZombies();
        game.basicAttack();
        assertEquals(1, blasts.size());
        Blast blastRight = blasts.get(0);
        assertEquals(X, blastRight.getPosX());
        assertEquals(Y, blastRight.getPosY());
        wizard.moveDown();
        game.basicAttack();
        Blast blastDown = blasts.get(1);
        assertEquals(2, blasts.size());
        assertEquals(X, blastDown.getPosX());
        assertEquals(Y + Wizard.SPEED, blastDown.getPosY());
    }

    @Test
    public void basicAttackTestRightDownLeftUp(){
        game.deleteZombies();
        game.basicAttack();
        wizard.moveDown();
        game.basicAttack();
        wizard.moveLeft();
        game.basicAttack();
        Blast blastLeft = blasts.get(2);
        assertEquals(3, blasts.size());
        assertEquals(X - Wizard.SPEED, blastLeft.getPosX());
        assertEquals(Y + Wizard.SPEED, blastLeft.getPosY());
        wizard.moveUp();
        game.basicAttack();
        Blast blastUp = blasts.get(3);
        assertEquals(4, blasts.size());
        assertEquals(X - Wizard.SPEED, blastUp.getPosX());
        assertEquals(Y, blastUp.getPosY());
    }

    @Test
    public void basicAttackMultipleSameDirection() {
        game.deleteZombies();
        game.basicAttack();
        Blast blastFirst = blasts.get(0);
        wizard.moveRight();
        game.basicAttack();
        wizard.moveLeft();                             // ensures that blast doesn't repeatedly reference wizard x/y
        wizard.moveLeft();
        Blast blastSecond = blasts.get(1);
        assertEquals(2, blasts.size());
        assertEquals(X, blastFirst.getPosX());
        assertEquals(Y, blastFirst.getPosY());
        assertEquals(X + Wizard.SPEED, blastSecond.getPosX());
        assertEquals(Y, blastFirst.getPosY());
    }

    @Test
    public void blastBoundaryTestX() {
        List<Blast> blasts = game.getBlasts();
        blasts.add(new Blast(1, Y, Entity.Direction.RIGHT));

        game.blastBoundary();
        assertEquals(2, blasts.size());
        b.setPosX(WIDTH - 1);
        game.blastBoundary();
        assertEquals(2, blasts.size());
        b.setPosX(WIDTH);
        blasts.get(1).setPosX(0);
        game.blastBoundary();
        assertEquals(0, blasts.size());
    }

    @Test
    public void blastBoundaryTestY() {
        List<Blast> blasts = game.getBlasts();
        blasts.add(new Blast(X, 1, Entity.Direction.RIGHT));

        game.blastBoundary();
        assertEquals(2, blasts.size());
        b.setPosY(HEIGHT - 1);
        game.blastBoundary();
        assertEquals(2, blasts.size());
        b.setPosY(HEIGHT);
        blasts.get(1).setPosY(0);
        game.blastBoundary();
        assertEquals(0, blasts.size());
    }

    @Test
    public void wizardDamaged() {
        blasts.remove(b);
        assertTrue(game.wizardDamaged());
        assertEquals(100 - damage, wizard.getHealth());
        wizard.setPosX(X + 5);                                             // wizard moves out of harms way
        assertFalse(game.wizardDamaged());
        assertEquals(100 - damage, wizard.getHealth());
        wizard.setPosX(X + 3);
        assertTrue(game.wizardDamaged());
        assertEquals(100 - damage * 2, wizard.getHealth());
        wizard.setPosY(Y + 26);
        assertFalse(game.wizardDamaged());
        wizard.setPosY(Y + 9);
        assertTrue(game.wizardDamaged());
        assertEquals(100 - damage * 3, wizard.getHealth());
    }

    @Test
    public void wizardDamagedMultiple() {
        blasts.remove(b);
        Zombie zSecond = new Zombie(X, Y);
        game.addZombie(zSecond);
        assertTrue(game.wizardDamaged());
        assertEquals(100 - (2 * damage), wizard.getHealth());
        wizard.setPosX(X + 5);                                             // wizard moves out of harms way
        assertFalse(game.wizardDamaged());
        assertEquals(100 - (2 * damage), wizard.getHealth());
        Zombie zombie = zombies.get(0);
        zombie.approach(X + Wizard.SPEED, Y);
        assertTrue(game.wizardDamaged());
        assertEquals(100 - (3 * damage), wizard.getHealth());
    }

    @Test
    public void nextZombiesTestSingle() {
        game.nextZombies();
        assertEquals(X, z.getPosX());
        assertEquals(Y, z.getPosY());
    }

    @Test
    public void nextZombiesTestMultiple() {
        Zombie zSecond = new Zombie (0, 0);
        Zombie zThird = new Zombie (X * 2, Y * 2);
        game.addZombie(zSecond);
        game.addZombie(zThird);
        game.nextZombies();
        assertEquals(X, z.getPosX());
        assertEquals(Y, z.getPosY());
        assertEquals(Zombie.ZOMBIE_SPEED, zSecond.getPosX());
        assertEquals(Zombie.ZOMBIE_SPEED, zSecond.getPosY());
        assertEquals((X * 2) - Zombie.ZOMBIE_SPEED, zThird.getPosX());
        assertEquals((X * 2) - Zombie.ZOMBIE_SPEED, zThird.getPosY());
    }

    @Test
    public void nextBlastsTestSingle() {
        game.nextBlasts();
        assertEquals(X + Blast.BLAST_SPEED, b.getPosX());
        assertEquals(Y, b.getPosY());
    }

    @Test
    public void nextBlastsTestMultiple() {
        game.deleteZombies();
        Blast bSecond = new Blast (0, 0, Entity.Direction.DOWN);
        Blast bThird = new Blast (X * 2, Y * 2, Entity.Direction.LEFT);
            Blast bFourth = new Blast (X, Y, Entity.Direction.UP);
        game.addBlast(bSecond);
        game.addBlast(bThird);
        game.addBlast(bFourth);
        game.nextBlasts();
        assertEquals(0, bSecond.getPosX());
        assertEquals(Blast.BLAST_SPEED, bSecond.getPosY());
        assertEquals((X * 2) - Blast.BLAST_SPEED, bThird.getPosX());
        assertEquals(Y * 2, bThird.getPosY());
        assertEquals(X, bFourth.getPosX());
        assertEquals(Y - Blast.BLAST_SPEED, bFourth.getPosY());
    }

    @Test
    public void resetGameTest() {
        wizard.setHealth(2);
        wizard.setPosY(10);
        wizard.setPosX(10);
        Blast blastDown = new Blast(0, 0, Entity.Direction.DOWN);
        Blast blastLeft = new Blast(X / 2, Y / 2, Entity.Direction.LEFT);
        Zombie zombieSecond = new Zombie(0, 0);
        Zombie zombieThird = new Zombie(X / 2, Y / 2);
        game.addBlast(blastDown);
        game.addBlast(blastLeft);
        game.addZombie(zombieSecond);
        game.addZombie(zombieThird);
        game.resetGame();
        assertTrue(blasts.isEmpty());
        assertTrue(zombies.isEmpty());
        assertEquals(X, wizard.getPosX());
        assertEquals(Y, wizard.getPosY());
        assertEquals(100, wizard.getHealth());
    }

}
