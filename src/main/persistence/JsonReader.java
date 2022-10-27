package persistence;

import model.Blast;
import model.GameLogic;
import model.Zombie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// The following methods are heavily credited to JSONSerializationDemo:
// read(), readFile()
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads the JSON Syntax and implements the game state accordingly
public class JsonReader {

    private String file;

    // constructs a reader that converts JSON to WizardsVsZombies
    public JsonReader(String file) {
        this.file = file;
    }

    // EFFECTS: reads a file, converts it to JSON and converts JSON to game state
    public GameLogic read() throws IOException {
        String fileString = readFile(file);
        JSONObject jsonObject = new JSONObject(fileString);
        return convertToGameState(jsonObject);
    }


    // EFFECTS: converts file to string and returns the string
    private String readFile(String source) throws IOException {
        StringBuilder translation = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> translation.append(s));
        }

        return translation.toString();
    }

    // EFFECTS: converts given JSON object to a game state
    private GameLogic convertToGameState(JSONObject json) {
        GameLogic game = new GameLogic();

        JSONObject wizardStats = json.getJSONObject("Wizard");
        int x = wizardStats.getInt("x");
        int y = wizardStats.getInt("y");
        int dx = wizardStats.getInt("dx");
        int dy = wizardStats.getInt("dy");
        int health = wizardStats.getInt("health");
        int time = wizardStats.getInt("time");
        boolean moving = wizardStats.getBoolean("moving");

        game.setWizard(x, y, dx, dy, health, time, moving);

        JSONArray blastStats = json.getJSONArray("Blasts");
        game.setBlasts(convertToBlasts(blastStats));

        JSONArray zombieStats = json.getJSONArray("Zombies");
        game.setZombies(convertToZombies(zombieStats));

        return game;
    }

    // EFFECTS: converts JSON blast arrays into real game list of blasts
    private ArrayList<Blast> convertToBlasts(JSONArray jsonArray) {

        ArrayList<Blast> blasts = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject curr = jsonArray.getJSONObject(i);
            int x = curr.getInt("x");
            int y = curr.getInt("y");
            boolean horizontal = curr.getBoolean("horizontal");
            boolean direction = curr.getBoolean("direction");

            Blast blast = new Blast(x, y, horizontal, direction);

            blasts.add(blast);
        }
        return blasts;
    }

    // EFFECTS: converts JSON zombie arrys into real game list of zombies
    private ArrayList<Zombie> convertToZombies(JSONArray jsonArray) {

        ArrayList<Zombie> zombies = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject curr = jsonArray.getJSONObject(i);
            int x = curr.getInt("x");
            int y = curr.getInt("y");

            Zombie zombie = new Zombie(x, y);

            zombies.add(zombie);
        }
        return zombies;
    }
}
