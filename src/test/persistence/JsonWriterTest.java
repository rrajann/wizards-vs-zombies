package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    GameLogic randomGame;

    @BeforeEach
    public void before() {                          // used for random games
        randomGame = new GameLogic();
        randomGame.setWizard(50, 20, 0, 5, 56, 5, false);

        Blast blast1 = new Blast(20, 5, Entity.Direction.DOWN);
        Blast blast2 = new Blast(50, 20, Entity.Direction.UP);

        Zombie zombie1 = new Zombie(50, 25);
        Zombie zombie2 = new Zombie(32, 30);
        Zombie zombie3 = new Zombie(0, 0);

        randomGame.addBlast(blast1);
        randomGame.addBlast(blast2);
        randomGame.addZombie(zombie1);
        randomGame.addZombie(zombie2);
        randomGame.addZombie(zombie3);
    }

    @Test
    public void testJsonWriterIllegalFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterStartGame() {
        try {
            GameLogic game = new GameLogic();
            JsonWriter writer = new JsonWriter("./data/testWriterStartGame.json");
            writer.open();
            writer.writeOn(game);

            JsonReader reader = new JsonReader("./data/testWriterStartGame.json");
            GameLogic readGame = reader.read();

            assertEquals(50, readGame.getWizard().getPosX());
            assertEquals(50, readGame.getWizard().getPosY());
            assertEquals(0, readGame.getBlasts().size());
            assertEquals(0, readGame.getZombies().size());
        } catch (IOException e) {
            fail("Should not have caught exception");
        }
    }

    @Test
    public void testWriterRandomGame() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterRandomGame.json");
            writer.open();
            writer.writeOn(randomGame);

            JsonReader reader = new JsonReader("./data/testWriterRandomGame.json");
            GameLogic readGame = reader.read();

            assertEquals(50, readGame.getWizard().getPosX());
            assertEquals(20, readGame.getWizard().getPosY());
            assertEquals(56, readGame.getWizard().getHealth());
            assertEquals(2, readGame.getBlasts().size());
            assertEquals(3, readGame.getZombies().size());
        } catch (IOException e) {
            fail ("Should not have caught exception");
        }
    }

    @Test
    public void testOverwritePreviousGame() {
        try {
            GameLogic game = new GameLogic();
            game.setWizard(0, 5, 0, 5, 100, 5, false);
            JsonWriter writer = new JsonWriter("./data/testOverridePreviousGame");
            writer.open();
            writer.writeOn(game);

            JsonReader reader = new JsonReader("./data/testOverridePreviousGame");
            game = reader.read();

            game.getWizard().moveRight();
            game.getWizard().moveDown();

            writer.open();
            writer.writeOn(game);

            game = reader.read();
            assertEquals(Wizard.SPEED, game.getWizard().getPosX());
            assertEquals(5 + Wizard.SPEED, game.getWizard().getPosY());
        } catch (IOException e) {
            fail("Should not have caught exception");
        }
    }
}
