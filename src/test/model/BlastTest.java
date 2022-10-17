package model;


import org.junit.jupiter.api.Test;
import ui.WizardsVsZombies;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BlastTest {

    private Blast blastRight;
    private Blast blastLeft;
    private Blast blastUp;
    private Blast blastDown;
    private final int speed = Blast.BLAST_SPEED;
    private final int startWidth = WizardsVsZombies.WIDTH / 2;
    private final int startHeight = WizardsVsZombies.HEIGHT / 2;

    @Test
    public void moveTestRight() {
        blastRight = new Blast(startWidth, startHeight, true, true);
        blastRight.moveBlast();
        assertEquals(startWidth + speed, blastRight.getPosX());
        assertEquals(startHeight, blastRight.getPosY());
        blastRight.moveBlast();
        assertEquals(startWidth + speed + speed, blastRight.getPosX());
        assertEquals(startHeight, blastRight.getPosY());
    }

    @Test
    public void moveTestLeft() {
        blastLeft = new Blast(startWidth, startHeight, true, false);
        blastLeft.moveBlast();
        assertEquals(startWidth - speed, blastLeft.getPosX());
        assertEquals(startHeight, blastLeft.getPosY());
        blastLeft.moveBlast();
        assertEquals(startWidth - speed - speed, blastLeft.getPosX());
        assertEquals(startHeight, blastLeft.getPosY());
    }

    @Test
    public void moveTestUp() {
        blastUp = new Blast(startWidth, startHeight, false, false);
        blastUp.moveBlast();
        assertEquals(startHeight - speed, blastUp.getPosY());
        assertEquals(startWidth, blastUp.getPosX());
        blastUp.moveBlast();
        assertEquals(startHeight - speed - speed, blastUp.getPosY());
        assertEquals(startWidth, blastUp.getPosX());
    }

    @Test
    public void moveTestDown() {
        blastDown = new Blast(startWidth, startHeight, false, true);
        blastDown.moveBlast();
        assertEquals(startHeight + speed, blastDown.getPosY());
        assertEquals(startWidth, blastDown.getPosX());
        blastDown.moveBlast();
        assertEquals(startHeight + speed + speed, blastDown.getPosY());
        assertEquals(startWidth, blastDown.getPosX());
    }

}
