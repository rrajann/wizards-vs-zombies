package persistence;

import model.GameLogic;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// The following methods are heavily credited to JSONSerializationDemo:
// writeOn()
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a writer that translates the saved game state into JSON Syntax
public class JsonWriter {

    private String fileName;
    private PrintWriter file;
    private static final int INDENT = 2;


    // EFFECTS: Stores game state as a JSON file
    public JsonWriter(String name) {
        fileName = name;
    }

    // MODIFIES: this
    // EFFECTS: traces JSON to specified file, throwing exception if file given does not exist
    public void open() throws FileNotFoundException {
        file = new PrintWriter(fileName);
    }

    // MODIFIES: this
    // EFFECTS: prints the current game state into specified file and closes the file
    public void writeOn(GameLogic gl) {
        JSONObject text = gl.toJson();                          // good luck to Jason! :D
        String stringToPrint = text.toString(INDENT);
        file.print(stringToPrint);
        file.close();
    }
}
