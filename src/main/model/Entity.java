package model;

import java.awt.*;

// interface for any dynamic (moving) class in the game
public class Entity {

    public static final int LIVING_X = 7;
    public static final int LIVING_Y = 20;
    public static final int BLAST_X_SIZE = 5;
    public static final int BLAST_Y_SIZE = 5;

    protected int posX;
    protected int posY;
    protected Entity entity;
    protected Rectangle hitbox;

    // EFFECT: creates super class describing any moving object in the game
    public Entity(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        hitbox = new Rectangle(posX - LIVING_X / 2, posY - LIVING_Y / 2, LIVING_X, LIVING_Y);
    }

    // EFFECT: return x position of entity
    public int getPosX() {
        return posX;
    }

    // EFFECT: return y position of entity
    public int getPosY() {
        return posY;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    // EFFECT: returns true if this and argument occupy the same position
    // REQUIRES: if this is wizard, cannot be paired by blast and vice versa
    public boolean hitBy(Entity entity) {
        return (this.posX == entity.getPosX() && this.posY == entity.getPosY());
    }

    // REQUIRES: if this is the wizard, cannot be paired by blast and vice versa
    // EFFECT: returns true if two entities' hit-box intersect
    public boolean collision(Entity entity) {
        return this.hitbox.intersects(entity.getHitbox());
    }
}
