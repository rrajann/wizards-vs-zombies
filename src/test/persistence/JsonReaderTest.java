package persistence;

import model.Blast;
import model.GameLogic;
import model.Zombie;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testJsonReaderIllegalFile() {
        JsonReader reader = new JsonReader("./data/lol.json");
        try {
            GameLogic game = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderStartGame() {
        JsonReader reader = new JsonReader("./data/testReaderStartGame.json");
        try {
            GameLogic game = reader.read();
            assertEquals(50, game.getWizard().getPosX());
            assertEquals(50, game.getWizard().getPosY());
            assertEquals(5, game.getWizard().getDX());
            assertEquals(0, game.getWizard().getDY());
            assertEquals(100, game.getWizard().getHealth());
            assertEquals(0, game.getBlasts().size());
            assertEquals(0, game.getZombies().size());
        } catch (IOException e) {
            fail("Shouldn't have caught exception");
        }
    }

    @Test
    void testReaderRandomGameWizardAndArraySizes() {
        JsonReader reader = new JsonReader("./data/testReaderRandomGame.json");
        try {
            GameLogic game = reader.read();
            assertEquals(50, game.getWizard().getPosX());
            assertEquals(20, game.getWizard().getPosY());
            assertEquals(0, game.getWizard().getDX());
            assertEquals(5, game.getWizard().getDY());
            assertEquals(56, game.getWizard().getHealth());
            assertEquals(2, game.getBlasts().size());
            assertEquals(3, game.getZombies().size());
        } catch (IOException e) {
            fail("Shouldn't have caught exception");
        }
    }

    @Test
    void testReaderRandomGameIndividualBlasts() {
        JsonReader reader = new JsonReader("./data/testReaderRandomGame.json");
        try {
            GameLogic game = reader.read();
            Blast blast1 = game.getBlasts().get(0);
            Blast blast2 = game.getBlasts().get(1);
            assertEquals(20, blast1.getPosX());
            assertEquals(5, blast1.getPosY());
            assertEquals(50, blast2.getPosX());
            assertEquals(20, blast2.getPosY());
        } catch (IOException e) {
            fail("Shouldn't have caught exception");
        }
    }

    @Test
    void testReaderRandomGameIndividualZombies() {
        JsonReader reader = new JsonReader("./data/testReaderRandomGame.json");
        try {
            GameLogic game = reader.read();
            Zombie zombie1 = game.getZombies().get(0);
            Zombie zombie2 = game.getZombies().get(1);
            Zombie zombie3 = game.getZombies().get(2);
            assertEquals(50, zombie1.getPosX());
            assertEquals(25, zombie1.getPosY());
            assertEquals(32, zombie2.getPosX());
            assertEquals(30, zombie2.getPosY());
            assertEquals(0, zombie3.getPosX());
            assertEquals(0, zombie3.getPosY());
        } catch (IOException e) {
            fail("Shouldn't have caught exception");
        }
    }
}



