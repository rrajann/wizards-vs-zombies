package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.WizardsVsZombies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EntityTest {

    private Wizard wizard;
    private Blast blast;
    private Zombie zombie;

    private static final int x = WizardsVsZombies.WIDTH/2;
    private static final int y = WizardsVsZombies.HEIGHT/2;

    @BeforeEach
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
