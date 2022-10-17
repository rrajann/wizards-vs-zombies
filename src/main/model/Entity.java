package model;

import java.awt.*;

public class Entity {

    protected int posX;
    protected int posY;
    protected Entity entity;

    // EFFECT: creates super class describing any moving object in the game
    public Entity(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    // EFFECT: return x position of entity
    public int getPosX() {
        return posX;
    }

    // EFFECT: return y position of entity
    public int getPosY() {
        return posY;
    }

    // EFFECT: returns true if this and argument occupy the same position
    // REQUIRES: if this is wizard, cannot be paired by blast and vice versa
    public boolean hitBy(Entity entity) {
        return (this.posX == entity.getPosX() && this.posY == entity.getPosY());
    }

}
