package model;

import org.json.JSONObject;
import persistence.JsonWriter;
import persistence.Writable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

// Represents a single zombie that approaches the wizard
public class Zombie extends Entity implements Writable {

    public static final int ZOMBIE_SPEED = 2;
    public static final int ZOMBIE_DAMAGE = 2;
    Random rand;

    public Zombie(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public void move() {}

    // EFFECTS: moves the zombie closer to the specified x and y coordinates of argument
    // MODIFIES: this
    // REQUIRES: x and y must both be within the game boundaries (ie: within 0 and game WIDTH/HEIGHT)
    public void approach(int x, int y) {

        if (getPosX() == x) {
            approachY(y);
        } else if (getPosY() == y) {
            approachX(x);
        } else if (Math.abs(getPosX() - x) > Math.abs(getPosY() - y)) {
            approachY(y);
            approachX(x);
        } else {
            approachX(x);
            approachY(y);
        }
    }

    // EFFECTS: moves the zombie closer to the specified x argument
    // MODIFIES: this
    // REQUIRES: x must both be within the game boundaries (ie: within 0 and game WIDTH/HEIGHT)
    public void approachX(int x) {
        if (posX >= x) {
            lastDirection = Direction.LEFT;
            if (posX - x < ZOMBIE_SPEED) {
                posX = x;
            } else {
                posX -= ZOMBIE_SPEED;
            }
        } else {
            lastDirection = Direction.RIGHT;
            if (x - posX < ZOMBIE_SPEED) {
                posX = x;
            } else {
                posX += ZOMBIE_SPEED;
            }
        }
        hitbox.setLocation(posX, posY);
    }

    // EFFECTS: moves the zombie closer to the specified y argument
    // MODIFIES: this
    // REQUIRES: y must both be within the game boundaries (ie: within 0 and game WIDTH/HEIGHT)
    public void approachY(int y) {
        if (posY >= y) {
            lastDirection = Direction.UP;
            if (posY - y < ZOMBIE_SPEED) {
                posY = y;
            } else {
                posY -= ZOMBIE_SPEED;
            }
        } else {
            lastDirection = Direction.DOWN;
            if (y - posY < ZOMBIE_SPEED) {
                posY = y;
            } else {
                posY += ZOMBIE_SPEED;
            }
        }
        hitbox.setLocation(posX, posY);
    }

    // EFFECTS: converts zombie fields into JSON syntax
    @Override
    public JSONObject toJson() {
        JSONObject zombieAttributes = new JSONObject();
        zombieAttributes.put("x", posX);
        zombieAttributes.put("y", posY);

        return zombieAttributes;
    }
}

