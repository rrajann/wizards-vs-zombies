package persistence;

import model.Blast;
import model.GameLogic;
import model.Wizard;
import model.Zombie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    GameLogic randomGame;

    @BeforeEach
    public void before() {                          // used for random games
        randomGame = new GameLogic();
        Wizard wizard = randomGame.getWizard();
        wizard.setPosX(50);
        wizard.setPosY(20);
        wizard.setDx(0);
        wizard.setDy(5);

        Blast blast1 = new Blast(20, 5, true, false);
        Blast blast2 = new Blast(50, 20, false, false);

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

            readGame.equals(game);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

            readGame.equals(randomGame);                      // checks if what is written is the same as instantiation
        } catch (IOException e) {
            fail ("Should not have caught exception");
        }
    }

}
