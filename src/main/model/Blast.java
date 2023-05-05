package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// Represents a single blast that can be thrown by the wizard
public class Blast extends Entity implements Writable {

    public static final int BLAST_SPEED = 15;

    private static final int X_SIZE = Entity.BLAST_X_SIZE;
    private static final int Y_SIZE = Entity.BLAST_Y_SIZE;

    // EFFECT: creates a blast with given position and moving right
    public Blast(int posX, int posY, Direction direction) {
        super(posX, posY);
        lastDirection = direction;
        hitbox = new Rectangle(posX - X_SIZE / 2, posY - Y_SIZE / 2, X_SIZE * 4, Y_SIZE * 4);
    }

    // EFFECTS: moves the blast by its speed
    // MODIFIES: this
    public void move() {
        switch (lastDirection) {
            case UP:
                posY -= BLAST_SPEED;
                break;
            case DOWN:
                posY += BLAST_SPEED;
                break;
            case LEFT:
                posX -= BLAST_SPEED;
                break;
            case RIGHT:
                posX += BLAST_SPEED;
                break;
        }

        hitbox.setLocation(posX, posY);
    }

    // EFFECTS: converts blast fields into JSON syntax
    @Override
    public JSONObject toJson() {
        JSONObject blastAttribute = new JSONObject();
        blastAttribute.put("x", posX);
        blastAttribute.put("y", posY);
        blastAttribute.put("direction", lastDirection);
        return blastAttribute;
    }
}
