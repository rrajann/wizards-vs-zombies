package model;

import org.junit.Before;
import org.junit.Test;
import ui.WizardsVsZombies;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EntityTest {

    private Wizard wizard;
    private Blast blast;
    private Zombie zombie;

    private static final int x = WizardsVsZombies.WIDTH/2;
    private static final int y = WizardsVsZombies.HEIGHT/2;

    @Before
    public void before() {
        wizard = new Wizard(x, y);
        zombie = new Zombie(x, y);
        blast = new Blast(x, y, true, true);
    }

    @Test
    public void hitByTest() {
        assertTrue(zombie.hitBy(blast));
        assertTrue(wizard.hitBy(zombie));
        wizard.moveRight();
        blast.moveBlast();
        assertFalse(wizard.hitBy(zombie));
        assertFalse(zombie.hitBy(blast));
    }

}
