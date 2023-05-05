package model;

import ui.WizardsVsZombies;

import java.awt.*;

// interface for any dynamic (moving) class in the game
public abstract class Entity {

    public static final int LIVING_X = 10;
    public static final int LIVING_Y = 25;
    public static final int BLAST_X_SIZE = 10;
    public static final int BLAST_Y_SIZE = 5;

    protected int posX;
    protected int posY;
    protected Entity entity;
    protected Rectangle hitbox;
    protected Direction lastDirection;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    // REQUIRES: argument must be one of the Direction constants
    // EFFECTS: converts string to Direction
    public static Direction toDirection(String direction) {
        switch (direction) {
            case "UP":
                return Direction.UP;
            case "DOWN":
                return Direction.DOWN;
            case "LEFT":
                return Direction.LEFT;
            case "RIGHT":
                return Direction.RIGHT;
        }
        return null;
    }

    // EFFECT: creates super class describing any moving object in the game
    public Entity(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        lastDirection = null;
        hitbox = new Rectangle(posX - LIVING_X / 2, posY - LIVING_Y / 2, LIVING_X, LIVING_Y);
    }

    public abstract void move();

    // MODIFIES: this
    // EFFECTS: sets the x position
    public void setPosX(int x) {
        posX = x;
        hitbox.setLocation(x, posY);
    }

    // MODIFIES: this
    // EFFECTS: sets the x position
    public void setPosY(int y) {
        posY = y;
        hitbox.setLocation(posX, y);
    }

    // MODIFIES: this
    // EFFECTS: sets the direction of the entity
    public void setLastDirection(Direction direction) {
        lastDirection = direction;
    }

    // EFFECT: return x position of entity
    public int getPosX() {
        return posX;
    }

    // EFFECT: return y position of entity
    public int getPosY() {
        return posY;
    }

    // EFFECT: return the hitbox of the entity
    public Rectangle getHitbox() {
        return hitbox;
    }

    // EFFECT: return the last direction of the entity
    public Direction getLastDirection() {
        return lastDirection;
    }

    // REQUIRES: if this is the wizard, cannot be paired by blast and vice versa
    // EFFECT: returns true if two entities' hit-box intersect
    public boolean collision(Entity entity) {
        return this.hitbox.intersects(entity.getHitbox());
    }

    // EFFECTS: keeps entity within the boundary of the game
    // MODIFIES: this
    public void handleBoundary() {
        if (posX < 0) {
            posX = 0;
        } else if (posX > WizardsVsZombies.WIDTH) {
            posX = WizardsVsZombies.WIDTH;
        } else if (posY > WizardsVsZombies.HEIGHT) {
            posY = WizardsVsZombies.HEIGHT;
        } else if (posY < 0) {
            posY = 0;
        }
    }
}
