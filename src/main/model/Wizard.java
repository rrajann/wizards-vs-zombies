package model;

import org.json.JSONObject;
import persistence.Writable;
import ui.WizardsVsZombies;

import java.awt.*;

// Represents a wizard that the player controls through key inputs
public class Wizard extends Entity implements Writable {

    public static final int SPEED = 4;
    private static Wizard wizard = new Wizard(WizardsVsZombies.WIDTH / 2, WizardsVsZombies.HEIGHT / 2);

    private int health;
    private int dy;
    private int dx;
    private int mana;
    private int time;

    // REQUIRES: one of dx and dy has to be 0
    // EFFECTS: Constructs a wizard with 100 health, facing right and not moving
    private Wizard(int posX, int posY) {
        super(posX, posY);
        lastDirection = Direction.RIGHT;
        dy = 0;
        dx = 0;
        health = 100;
        time = 10;
        mana = 5;
        hitbox = new Rectangle(posX, posY, 35, 75);
    }

    // EFFECTS: accesses the singleton wizard
    public static Wizard getInstance() {
        return wizard;
    }

    // FIELD METHODS:
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // EFFECTS: returns the dx of the Wizard
    public int getDX() {
        return this.dx;
    }

    // EFFECTS: returns the dy of the Wizard
    public int getDY() {
        return this.dy;
    }

    // EFFECTS: returns the health of the Wizard
    public int getHealth() {
        return this.health;
    }

    // EFFECTS: returns the time until the Wizard can use its super attack
    public int getMana() {
        return this.mana;
    }

    // MODIFIES: this
    // EFFECTS: changes dx to given argument
    public void setDx(int dx) {
        this.dx = dx;
    }

    // MODIFIES: this
    // EFFECTS: changes dy to given argument
    public void setDy(int dy) {
        this.dy = dy;
    }

    // MODIFICATION METHODS:
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MODIFIES: this
    // EFFECTS: Moves the wizard based on the dy dx value
    public void move() {
        posX += dx;
        posY += dy;
        hitbox.setLocation(posX, posY);
        updateLastDirection();
        this.handleBoundary();
    }

    // MODIFIES: this
    // EFFECTS: changes last direction according to wizard dx dy value
    public void updateLastDirection() {
        if (dx > 0) {
            lastDirection = Direction.RIGHT;
        }
        if (dx < 0) {
            lastDirection = Direction.LEFT;
        }
        if (dy > 0) {
            lastDirection = Direction.DOWN;
        }
        if (dy < 0) {
            lastDirection = Direction.UP;
        }
    }

    // EFFECTS: Moves the wizard right by its speed
    // MODIFIES: this
    public void moveRight() {
        this.setDirection(1, 0);
        this.posX += dx;
        hitbox.setLocation(posX, posY);
        this.handleBoundary();
        lastDirection = Direction.RIGHT;
    }

    // EFFECTS: Moves the wizard right by its speed
    // MODIFIES: this
    public void moveLeft() {
        this.setDirection(-1, 0);
        this.posX += dx;
        hitbox.setLocation(posX, posY);
        this.handleBoundary();
        lastDirection = Direction.LEFT;
    }

    // EFFECTS: Moves the wizard up by its speed
    // MODIFIES: this
    public void moveUp() {
        this.setDirection(0, -1);
        this.posY += dy;
        hitbox.setLocation(posX, posY);
        this.handleBoundary();
        lastDirection = Direction.UP;
    }

    // EFFECTS: Moves the wizard down by its speed
    // MODIFIES: this
    public void moveDown() {
        this.setDirection(0, 1);
        this.posY += dy;
        hitbox.setLocation(posX, posY);
        this.handleBoundary();
        lastDirection = Direction.DOWN;
    }

    // EFFECTS: sets the x and y directions of wizard
    // REQUIRES: magnitude of parameters must be -1, 0, or 1
    // MODIFIES: this
    public void setDirection(int x, int y) {
        this.dx = SPEED * x;
        this.dy = SPEED * y;
    }

    // EFFECTS: changes the health of the wizard to the specified argument
    // MODIFIES: this
    // REQUIRES: health cannot be negative
    public void setHealth(int h) {
        this.health = h;
    }

    // EFFECTS: changes the time of the wizard to the specified argument
    // MODIFIES: this
    public void setMana(int t) {
        this.mana = t;
    }


    // EFFECTS: converts wizard fields into JSON syntax
    @Override
    public JSONObject toJson() {
        JSONObject wizardAttributes = new JSONObject();
        wizardAttributes.put("x", posX);
        wizardAttributes.put("y", posY);
        wizardAttributes.put("dx", dx);
        wizardAttributes.put("dy", dy);
        wizardAttributes.put("health", health);
        wizardAttributes.put("mana", mana);
        return wizardAttributes;
    }
}
