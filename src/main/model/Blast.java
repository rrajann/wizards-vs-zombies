package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

// Represents a single blast that can be thrown by the wizard
public class Blast extends Entity implements Writable {

    public static final int BLAST_SPEED = 20;

    private boolean horizontal;
    private boolean direction;
    private static final int X_SIZE = Entity.BLAST_X_SIZE;
    private static final int Y_SIZE = Entity.BLAST_Y_SIZE;

    // EFFECT: creates a blast with given position and moving right
    public Blast(int posX, int posY, boolean h, boolean d) {
        super(posX, posY);
        horizontal = h; // true means going horizontally (left/right)
        direction = d; // true means going in the positive direction (right/down)
        hitbox = new Rectangle(posX - X_SIZE / 2, posY - Y_SIZE / 2, X_SIZE, Y_SIZE);
    }

    // EFFECT: return weather or not the blast is moving horizontally
    public boolean isHorizontal() {
        return horizontal;
    }

    // EFFECT: return weather or not the blast is moving in the positive direction
    public boolean isDirection() {
        return direction;
    }

    // EFFECTS: moves the blast by its speed
    // MODIFIES: this
    public void move() {
        if (horizontal) {
            if (direction) {
                posX += BLAST_SPEED;
                lastDirection = Direction.RIGHT;
            } else {
                posX -= BLAST_SPEED;
                lastDirection = Direction.LEFT;
            }
        } else {
            if (direction) {
                posY += BLAST_SPEED;
                lastDirection = Direction.DOWN;
            } else {
                posY -= BLAST_SPEED;
                lastDirection = Direction.UP;
            }
        }
        hitbox.setLocation(posX, posY);
    }

    // EFFECTS: converts blast fields into JSON syntax
    @Override
    public JSONObject toJson() {
        JSONObject blastAttribute = new JSONObject();
        blastAttribute.put("x", posX);
        blastAttribute.put("y", posY);
        blastAttribute.put("horizontal", horizontal);
        blastAttribute.put("direction", direction);
        return blastAttribute;
    }
}
